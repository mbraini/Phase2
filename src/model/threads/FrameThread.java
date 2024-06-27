
package model.threads;

import data.Constants;
import model.GameState;
import model.ModelData;
import model.collision.Collision;
import model.logics.FrameHit;
import model.logics.Impact;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.ObjectModel;
import model.objectModel.projectiles.BulletModel;
import model.objectModel.projectiles.EpsilonBulletModel;
import utils.FrameCalculationHelper;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class FrameThread extends Thread{

    private ArrayList<FrameModel> frames;
    private ArrayList<ObjectModel> models;
    private HashMap<ObjectModel ,FrameModel> localFrames;

    public FrameThread(){
        frames = new ArrayList<>();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (!GameState.isPause() && !GameState.isOver()){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= Constants.UPS){
                updateFrames();
                delta = 0;
            }
        }
    }

    private void updateFrames() {
        synchronized (ModelData.getModels()) {
            localFrames = (HashMap<ObjectModel, FrameModel>) ModelData.getLocalFrames().clone();
            models = (ArrayList<ObjectModel>) ModelData.getModels().clone();
            frames = (ArrayList<FrameModel>) ModelData.getFrames().clone();
        }
        defineLocalFrames();
        synchronized (ModelData.getModels()) {
            localFrames = (HashMap<ObjectModel, FrameModel>) ModelData.getLocalFrames().clone();
        }
        checkBullets();
//        checkSolids();
        resize(frames);
    }

    private void checkBullets() {
        for (ObjectModel model : models){
            if (model.isSolid() && model instanceof BulletModel){
                if (FrameCalculationHelper.findClosestDistanceToFrameEdges(
                        model.getPosition(),
                        localFrames.get(model)
                ) <= Math.VectorSize(model.getVelocity()) * Constants.UPS + 1){
                    new FrameHit(localFrames.get(model) ,model).handle();
                    model.die();
                }
            }
        }
    }

    private void checkSolids() {
        /////todo
        for (ObjectModel model : models){
            FrameModel frame = localFrames.get(model);
            if (frame == null)
                continue;
            if (model.isSolid() && !Collision.isFullyInFrame(model ,frame)){
                Vector outerVertex = Collision.findTheOuterVertices(frame ,model).getFirst().clone();
                Collision.getOutOfFrame(model ,frame);
                new Impact(outerVertex).MakeImpact();
            }
        }
    }

    private void defineLocalFrames() {

        ///////concurrent
        HashMap<ObjectModel ,FrameModel> newLocals = new HashMap<>();
        for (ObjectModel model : models){
            ArrayList<FrameModel> modelFrames = defineFrame(model);
            if (modelFrames.isEmpty()){
                newLocals.put(model ,null);
            }
            else if (modelFrames.size() == 1){
                newLocals.put(model ,modelFrames.getFirst());
            }
            else {
                if (modelFrames.contains(localFrames.get(model))){
                    newLocals.put(model ,localFrames.get(model));
                }
                else {
                    newLocals.put(model ,modelFrames.getFirst());
                }
            }
        }
        ModelData.setLocalFrames(newLocals);
    }

    private void resize(ArrayList<FrameModel> frameModels) {
        for (FrameModel frame : frameModels){
            if (!frame.isIsometric()){
                frame.move();
            }
        }
    }
    private void framePressure(FrameModel frame) {
        if (!frame.isResizing()){
            frame.setUpDownV(-Constants.FRAME_SHRINKAGE_VELOCITY,-Constants.FRAME_SHRINKAGE_VELOCITY);
            frame.setLeftRightV(-Constants.FRAME_SHRINKAGE_VELOCITY,-Constants.FRAME_SHRINKAGE_VELOCITY);
        }
    }


    private ArrayList<FrameModel> defineFrame(ObjectModel model){
        ArrayList<FrameModel> frames = new ArrayList<>();
        ArrayList<FrameModel> dataFrames = ModelData.getFrames();
        for (int i = 0 ;i < dataFrames.size() ;i++){
            if (Collision.isInFrame(dataFrames.get(i) ,model.getPosition())){
                frames.add(dataFrames.get(i));
            }
        }
        return frames;
    }

}
