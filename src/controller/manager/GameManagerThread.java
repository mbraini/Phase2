package controller.manager;

import controller.manager.saving.GameSaver;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.interfaces.Fader;
import model.objectModel.effects.AoeEffectModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.ObjectModel;
import model.objectModel.fighters.AbstractEnemy;
import model.objectModel.fighters.EnemyModel;
import model.objectModel.frameModel.FrameModel;

import java.util.ArrayList;
import java.util.HashSet;

public class GameManagerThread extends Thread{
    private ArrayList<ObjectModel> models;
    private ArrayList<EffectModel> effects;
    private ArrayList<FrameModel> frames;
    private ArrayList<AbstractEnemy> abstractEnemies;
    private double time;
    private final static Object jsonLock = new Object();

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (true) {
            if (GameState.isPause()) {
                lastTime = System.nanoTime();
                continue;
            }
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
            frames = (ArrayList<FrameModel>) ModelData.getFrames().clone();
            abstractEnemies = (ArrayList<AbstractEnemy>) ModelData.getAbstractEnemies().clone();
        }
        interfaces();
        killObjects();
        checkAoeDamage();
        if (time % 100 == 0) {
            synchronized (jsonLock) {
                new GameSaver(models, effects, frames, abstractEnemies).save();
            }
        }
        GameState.update(models ,time);
    }

    private void checkAoeDamage() {

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

    public static Object getJsonLock() {
        return jsonLock;
    }

}
