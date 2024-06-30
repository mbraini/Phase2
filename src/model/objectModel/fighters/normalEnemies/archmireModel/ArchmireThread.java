package model.objectModel.fighters.normalEnemies.archmireModel;

import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.ObjectModel;
import model.objectModel.EffectModel;
import utils.Helper;

import java.awt.*;
import java.util.ArrayList;

public class ArchmireThread extends Thread{
    private double time;
    private ArrayList<ObjectModel> models;
    private ArrayList<EffectModel> effects;
    private ArchmireModel archmire;
    ArchmireThread(ArchmireModel archmire){
        this.archmire = archmire;
        time = 0;
    }

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
            if (deltaModel >= Constants.ARCHMIRE_THREAD_REFRESH_RATE) {
                updateAOE();
                deltaModel = 0;
                time += Constants.ARCHMIRE_THREAD_REFRESH_RATE;
            }
        }
    }

    private void updateAOE() {
        synchronized (ModelData.getModels()) {
            models = (ArrayList<ObjectModel>) ModelData.getModels().clone();
            effects = (ArrayList<EffectModel>) ModelData.getEffectModels().clone();
        }
        addNewShapes();
        addTimeToPoints();
        checkTimeLimit();
        setColors();
        checkForDamage();
    }

    private void setColors() {
        for (int i = 0; i < archmire.getAOE().getShapes().size() ; i++){
            int R = (int)
                    (255 - (255d/Constants.ARCHMIRE_AOE_TIME_LIMIT) * archmire.getAOE().getTimes().get(i)
                    );
            archmire.getAOE().getShapes().get(i).setColor(new Color(R ,0 ,0));
            setEffectColor(archmire.getAOE().getShapes().get(i));
        }

    }

    private void setEffectColor(EffectModel effectModel) {
        for (EffectModel effect : effects){
            if (effect.getId().equals(effectModel.getId())){
                effect.setColor(effectModel.getColor());
            }
        }
    }

    private void addTimeToPoints() {
        for (int i = 0;i < archmire.getAOE().getTimes().size(); i++){
            archmire.getAOE().getTimes().set(
                    i ,
                    archmire.getAOE().getTimes().get(i) + Constants.ARCHMIRE_THREAD_REFRESH_RATE
            );
        }
    }

    private void checkForDamage() {
        if (time % 1 == 0){
            archmire.getAOE().dealDamage(models);
        }
    }

    private void checkTimeLimit() {
        archmire.getAOE().removeShapeOverTime(Constants.ARCHMIRE_AOE_TIME_LIMIT);
    }

    private void addNewShapes() {
        ArchmireEffectModel archmireEffectModel = new ArchmireEffectModel(archmire ,
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        archmire.getAOE().addShape(archmireEffectModel);
        Spawner.addArchmireEffect(archmireEffectModel);
    }

}
