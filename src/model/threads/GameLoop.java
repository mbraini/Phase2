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
        while (!Controller.getController(Controller.getIP()).getGameState().isOver()) {
            if (Controller.getController(Controller.getIP()).getGameState().isPause()){
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
        synchronized (Controller.getController(Controller.getIP()).getModelData().getModels()) {
            Controller.getController(Controller.getIP()).getModelRequests().checkRequests();
            Controller.sendViewUpdates();
        }
        ArrayList<ObjectModel> models = (ArrayList<ObjectModel>) Controller.getController(Controller.getIP()).getModelData().getModels().clone();

        interfaceObjects(models);
        Collision.resetModelPairs();
        Collision.checkModelCollisions(models);
        checkGarbage(models);

    }

    private void checkGarbage(ArrayList<ObjectModel> models) {
        for (ObjectModel model : models){
            Vector position = model.getPosition();
            if (position.x <= -Constants.SCREEN_SIZE.width || position.x >= 2 * Constants.SCREEN_SIZE.width){
                Controller.getController(Controller.getIP()).getModelRequests().removeObjectModel(model.getId());
                continue;
            }
            if (position.y <= -Constants.SCREEN_SIZE.height || position.y >= 2 * Constants.SCREEN_SIZE.height){
                Controller.getController(Controller.getIP()).getModelRequests().removeObjectModel(model.getId());
            }
        }
    }

    private void interfaceObjects(ArrayList<ObjectModel> models) {
        for (ObjectModel model : models){
            if (model instanceof Ability){
                if (((Ability) model).hasAbility()) {
                    if (model instanceof EnemyModel && Controller.getController(Controller.getIP()).getGameState().isDizzy())
                        continue;
                    ((Ability) model).ability();
                }
            }
            if (model instanceof MoveAble) {
                if (model instanceof EnemyModel && Controller.getController(Controller.getIP()).getGameState().isDizzy())
                    continue;
                ((MoveAble) model).move();
            }
            if (model instanceof Navigator){
                if (!((Navigator) model).hasArrived()) {
                    if (model instanceof EnemyModel && Controller.getController(Controller.getIP()).getGameState().isDizzy())
                        continue;
                    ((Navigator) model).navigate();
                }
            }
            if (model instanceof FrameSticker)
                ((FrameSticker) model).setStuckFramePosition();
        }
    }


}