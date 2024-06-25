package model.threads;

import data.Constants;
import model.GameState;
import model.ModelData;
import model.collision.Collision;
import model.logics.Impact;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.ObjectModel;
import utils.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class FrameThread extends Thread{

    private ArrayList<FrameModel> frames;

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
        defineLocalFrames();
        checkSolids();
        resize();
    }

    private void checkSolids() {
        /////todo
        ArrayList<ObjectModel> models = ModelData.getModels();
        for (ObjectModel model : models){
            FrameModel frame = ModelData.getLocalFrames().get(model);
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
        HashMap<ObjectModel ,FrameModel> previousLocals = ModelData.getLocalFrames();
        HashMap<ObjectModel ,FrameModel> newLocals = new HashMap<>();
        for (ObjectModel model : ModelData.getModels()){
            ArrayList<FrameModel> modelFrames = defineFrame(model);
            if (modelFrames.isEmpty()){
                newLocals.put(model ,null);
            }
            else if (modelFrames.size() == 1){
                newLocals.put(model ,modelFrames.getFirst());
            }
            else {
                if (modelFrames.contains(previousLocals.get(model))){
                    newLocals.put(model ,previousLocals.get(model));
                }
                else {
                    newLocals.put(model ,modelFrames.getFirst());
                }
            }
        }
        ModelData.setLocalFrames(newLocals);
    }

    private void resize() {
        ArrayList<FrameModel> frameModels = ModelData.getFrames();
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
