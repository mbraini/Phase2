package model.threads;


import controller.Controller;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.ModelRequests;
import model.collision.Collision;
import model.interfaces.Ability;
import model.interfaces.FrameSticker;
import model.interfaces.MoveAble;
import model.interfaces.Navigator;
import model.objectModel.ObjectModel;

import java.util.ArrayList;

public class GameLoop extends Thread {

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isPause() && !GameState.isOver()) {
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.UPS) {
                UpdateModel();
                deltaModel = 0;
            }
        }
    }

    private void UpdateModel() {
        ///////concurrent
        synchronized (ModelData.getModels()) {
            ModelRequests.checkRequests();
            Controller.sendViewUpdates();
        }
        ArrayList<ObjectModel> models = (ArrayList<ObjectModel>) ModelData.getModels().clone();

        for (int i = 0 ;i < models.size() ;i++){
            if (models.get(i).getId() == null){
                System.out.println("NULL :(");
            }
        }

            interfaceObjects(models);
//        Collision.resetPairs();
//        Collision.checkCollisions(models);

    }

    private void interfaceObjects(ArrayList<ObjectModel> models) {
        for (ObjectModel model : models){
            if (model instanceof Ability){
                if (((Ability) model).hasAbility())
                    ((Ability) model).ability();
            }
            if (model instanceof MoveAble)
                ((MoveAble) model).move();
            if (model instanceof Navigator){
                if (!((Navigator) model).hasArrived())
                    ((Navigator) model).navigate();
            }
            if (model instanceof FrameSticker)
                ((FrameSticker) model).setStuckFramePosition();
        }
    }


}