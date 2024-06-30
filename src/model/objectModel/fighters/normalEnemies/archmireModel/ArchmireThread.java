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
        while (!GameState.isPause() && !GameState.isOver() && !isInterrupted()) {
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
        checkForDamage();
    }

    private void checkForDamage() {
        if (time % 1 == 0){
            archmire.getAOE().dealDamage(models);
        }
    }


    private void addNewShapes() {
        ArchmireEffectModel archmireEffectModel = new ArchmireEffectModel(archmire ,
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        archmire.getAOE().addShape(archmireEffectModel);
        Spawner.addArchmireEffect(archmireEffectModel);
    }

}
