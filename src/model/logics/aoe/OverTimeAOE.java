package model.logics.aoe;

import model.objectModel.ObjectModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OverTimeAOE extends AOE{
    private ArrayList<Point> points;
    private ArrayList<Double> times;
    @Override
    void dealDamage(ArrayList<ObjectModel> models) {

    }

    public void removePointOverTime(double time){
        for (int i = 0; i < points.size() ;i++){
            if (times.get(i) >= time){
                times.remove(i);
                points.remove(i);
                i--;
            }
        }
    }

    public void addPoint(Point point){
        points.add(point);
        times.add(0d);
    }

}
