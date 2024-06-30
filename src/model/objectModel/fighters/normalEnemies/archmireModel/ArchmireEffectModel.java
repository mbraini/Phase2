package model.objectModel.fighters.normalEnemies.archmireModel;

import controller.Controller;
import data.Constants;
import model.interfaces.Fader;
import model.interfaces.IsPolygon;
import model.objectModel.EffectModel;
import utils.area.Polygon;

import java.awt.*;
import java.util.ArrayList;

public class ArchmireEffectModel extends EffectModel implements Fader {
    private ArchmireModel archmireModel;
    private double time;
    public ArchmireEffectModel(ArchmireModel archmireModel , String id){
        this.archmireModel = archmireModel;
        this.color = Color.RED;
        this.id = id;
        this.position = archmireModel.getPosition();
        initArea();
    }

    private void initArea() {
        IsPolygon polygon = ((IsPolygon)(archmireModel));
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0 ;i < polygon.getVertices().size();i++){
            x.add((int) polygon.getVertices().get(i).x);
            y.add((int) polygon.getVertices().get(i).y);
        }
        this.area = new Polygon(x ,y ,polygon.getVertices().size());
    }

    @Override
    public void die() {
        Controller.removeEffect(this);
    }

    @Override
    public void addTime(double time) {
        this.time += time;
        int R = (int)
                (255 - (255d/ Constants.ARCHMIRE_AOE_TIME_LIMIT) * this.time
                );
        color = new Color(R ,0 ,0);
    }

    @Override
    public void fadeIf() {
        if (Constants.ARCHMIRE_AOE_TIME_LIMIT <= time) {
            archmireModel.getAOE().removeShape(this);
            die();
        }
    }
}
