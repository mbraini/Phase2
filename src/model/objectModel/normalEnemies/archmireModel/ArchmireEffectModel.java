package model.objectModel.normalEnemies.archmireModel;

import model.interfaces.IsPolygon;
import model.objectModel.EffectModel;
import utils.area.Polygon;

import java.awt.*;
import java.util.ArrayList;

public class ArchmireEffectModel extends EffectModel {
    private ArchmireModel archmireModel;
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

    }
}
