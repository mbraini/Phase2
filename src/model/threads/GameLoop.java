package model.threads;


import controller.Controller;
import constants.Constants;
import controller.manager.GameState;
import model.ModelData;
import model.ModelRequests;
import model.collision.Collision;
import model.interfaces.*;
import model.objectModel.ObjectModel;
import model.objectModel.fighters.EnemyModel;
import utils.Vector;

import java.util.ArrayList;

public class GameLoop extends Thread {

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isOver()) {
            if (GameState.isPause()){
                lastTime = System.nanoTime();
                continue;
            }
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.UPS) {
                UpdateModel();
                deltaModel = 0;
            }
        }
    }

    public void UpdateModel() {
        ///////concurrent
        synchronized (ModelData.getModels()) {
            ModelRequests.checkRequests();
            Controller.sendViewUpdates();
        }
        ArrayList<ObjectModel> models = (ArrayList<ObjectModel>) ModelData.getModels().clone();
        System.out.println(models.size());

        for (int i = 0 ;i < models.size() ;i++){
            if (models.get(i).getId() == null){
                System.out.println("NULL :(");
            }
        }
        interfaceObjects(models);
        Collision.resetModelPairs();
        Collision.checkModelCollisions(models);
        checkGarbage(models);

    }

    private void checkGarbage(ArrayList<ObjectModel> models) {
        for (ObjectModel model : models){
            Vector position = model.getPosition();
            if (position.x <= -Constants.SCREEN_SIZE.width || position.x >= 2 * Constants.SCREEN_SIZE.width){
                ModelRequests.removeObjectModel(model.getId());
                continue;
            }
            if (position.y <= -Constants.SCREEN_SIZE.height || position.y >= 2 * Constants.SCREEN_SIZE.height){
                ModelRequests.removeObjectModel(model.getId());
            }
        }
    }

    private void interfaceObjects(ArrayList<ObjectModel> models) {
        for (ObjectModel model : models){
            if (model instanceof Ability){
                if (((Ability) model).hasAbility()) {
                    if (model instanceof EnemyModel && GameState.isDizzy())
                        continue;
                    ((Ability) model).ability();
                }
            }
            if (model instanceof MoveAble) {
                if (model instanceof EnemyModel && GameState.isDizzy())
                    continue;
                ((MoveAble) model).move();
            }
            if (model instanceof Navigator){
                if (!((Navigator) model).hasArrived()) {
                    if (model instanceof EnemyModel && GameState.isDizzy())
                        continue;
                    ((Navigator) model).navigate();
                }
            }
            if (model instanceof FrameSticker)
                ((FrameSticker) model).setStuckFramePosition();
        }
    }


}