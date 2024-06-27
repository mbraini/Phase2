package model.objectModel.normalEnemies.archmireModel;

import controller.Controller;
import controller.enums.ObjectType;
import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.collision.Collision;
import model.interfaces.IsPolygon;
import model.objectModel.ObjectModel;
import utils.Helper;
import utils.Vector;

import java.util.ArrayList;

public class ArchmireThread extends Thread{
    private double time;
    private ArrayList<ObjectModel> models;
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
        }
        addNewPoints();
        addTimeToPoints();
        checkTimeLimit();
        checkForDamage();
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
        archmire.getAOE().removePointOverTime(Constants.ARCHMIRE_AOE_TIME_LIMIT);
    }

    private void addNewPoints() {
        ArrayList<ObjectModel> pointsInArchmire = getPointsInArchmire();
        for (ObjectModel point : pointsInArchmire){
            if (archmire.getAOE().containsPoint(point)){
                int index = archmire.getAOE().indexOfPoint(point);
                archmire.getAOE().getTimes().set(index ,0d);
            }
            else {
                archmire.getAOE().addPoint(point);
                Spawner.addObject(point.getPosition() ,point.getId() , ObjectType.archmirePoint);
            }
        }
    }

    private ArrayList<ObjectModel> getPointsInArchmire() {
        ArrayList<ObjectModel> newPoints = new ArrayList<>();
        double xS = archmire.getPosition().x - Constants.ARCHMIRE_DIMENSION.width / 2d;
        double xE = archmire.getPosition().x + Constants.ARCHMIRE_DIMENSION.width / 2d;
        double yS = archmire.getPosition().y - Constants.ARCHMIRE_DIMENSION.height / 2d;
        double yE = archmire.getPosition().y + Constants.ARCHMIRE_DIMENSION.height / 2d;
        for (int i = (int) xS ;i < (int) xE ;i++){
            for (int j = (int) yS ;j < (int) yE ;j++){
                if (Collision.IsInPolygon((IsPolygon) archmire ,new Vector(i ,j))){
                    newPoints.add(new ArchmirePointModel(
                            new Vector(i ,j),
                            Helper.RandomStringGenerator(Constants.ID_SIZE))
                    );
                }
                j+=(int) Constants.ARCHMIRE_POINT_RADIOS;
            }
            i+=(int) Constants.ARCHMIRE_POINT_RADIOS;
        }
        return newPoints;
    }
}
