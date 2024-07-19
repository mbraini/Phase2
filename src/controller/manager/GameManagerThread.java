package controller.manager;

import controller.Controller;
import controller.manager.saving.GameSaver;
import constants.Constants;
import model.ModelData;
import model.inGameAbilities.InGameAbility;
import model.interfaces.Fader;
import model.objectModel.effects.EffectModel;
import model.objectModel.ObjectModel;
import model.objectModel.fighters.AbstractEnemy;
import model.objectModel.fighters.EnemyModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import model.skillTreeAbilities.SkillTreeAbility;

import java.util.ArrayList;

public class GameManagerThread extends Thread{
    private ArrayList<ObjectModel> models;
    private ArrayList<EffectModel> effects;
    private ArrayList<FrameModel> frames;
    private ArrayList<AbstractEnemy> abstractEnemies;
    private ArrayList<InGameAbility> abilities;
    private ArrayList<SkillTreeAbility> skillTreeAbilities;
    private double time;
    private final static Object jsonLock = new Object();

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isOver()) {
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

        synchronized (Controller.getController(Controller.getIP()).getModelData().getModels()){
            models = (ArrayList<ObjectModel>) Controller.getController(Controller.getIP()).getModelData().getModels().clone();
            effects = (ArrayList<EffectModel>) Controller.getController(Controller.getIP()).getModelData().getEffectModels().clone();
            frames = (ArrayList<FrameModel>) Controller.getController(Controller.getIP()).getModelData().getFrames().clone();
            abstractEnemies = (ArrayList<AbstractEnemy>) Controller.getController(Controller.getIP()).getModelData().getAbstractEnemies().clone();
            abilities = (ArrayList<InGameAbility>) Controller.getController(Controller.getIP()).getModelData().getInGameAbilities().clone();
            skillTreeAbilities =(ArrayList<SkillTreeAbility>) Controller.getController(Controller.getIP()).getModelData().getSkillTreeAbilities().clone();
        }
        interfaces();
        killObjects();
        checkAoeDamage();
        if (time % 1000 == 0) {
            synchronized (jsonLock) {
                new GameSaver(models, effects, frames, abstractEnemies ,abilities ,skillTreeAbilities).save();
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
            if (model.getHP() <= 0) {
                model.die();
                if (model instanceof EpsilonModel && !GameState.isOver()) {
                    Controller.getController(Controller.getIP()).endGame();
                }
            }
        }
    }

    public static Object getJsonLock() {
        return jsonLock;
    }

}
