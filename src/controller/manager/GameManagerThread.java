package controller.manager;

import data.Constants;
import model.GameState;
import model.ModelData;
import model.interfaces.Fader;
import model.objectModel.EffectModel;
import model.objectModel.ObjectModel;

import java.util.ArrayList;

public class GameManagerThread extends Thread{
    private ArrayList<ObjectModel> models;
    private ArrayList<EffectModel> effects;
    private double time;

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
            if (deltaModel >= Constants.MANAGER_THREAD_REFRESH_TIME) {
                manage();
                deltaModel = 0;
                time += Constants.MANAGER_THREAD_REFRESH_TIME;
            }
        }
    }

    private void manage() {

        synchronized (ModelData.getModels()){
            models = (ArrayList<ObjectModel>) ModelData.getModels().clone();
            effects = (ArrayList<EffectModel>) ModelData.getEffectModels().clone();
        }
        interfaces();
        killObjects();
        GameState.update(models ,time);
    }

    private void interfaces() {
        for (ObjectModel model : models){
            if (model instanceof Fader){
                ((Fader) model).addTime(Constants.MANAGER_THREAD_REFRESH_TIME);
                ((Fader) model).fadeIf();
            }
        }
        for (EffectModel effect : effects){
            if (effect instanceof Fader){
                ((Fader) effect).addTime(Constants.MANAGER_THREAD_REFRESH_TIME);
                ((Fader) effect).fadeIf();
            }
        }
    }

    private void killObjects() {
        for (ObjectModel model : models){
            if (model.getHP() <= 0)
                model.die();
        }
    }

}
