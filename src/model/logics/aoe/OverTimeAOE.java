package model.logics.aoe;

import controller.Controller;
import model.ModelRequests;
import model.objectModel.ObjectModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OverTimeAOE extends AOE{
    private ArrayList<ObjectModel> points;
    private ArrayList<Double> times;

    public OverTimeAOE(){
        points = new ArrayList<>();
        times = new ArrayList<>();
    }

    @Override
    public void dealDamage(ArrayList<ObjectModel> models) {

    }

    public void removePointOverTime(double time){
        for (int i = 0; i < points.size() ;i++){
            if (times.get(i) >= time){
                Controller.removeObject(points.get(i));
                times.remove(i);
                points.remove(i);
                i--;
            }
        }
    }

    public void addPoint(ObjectModel point){
        points.add(point);
        times.add(0d);
    }

    public int indexOfPoint(ObjectModel point){
        for (int i = 0; i < points.size() ;i++){
            if (points.get(i).getPosition().x == point.getPosition().x
                    && points.get(i).getPosition().y == point.getPosition().y)
                return i;
        }
        return -1;
    }

    public boolean containsPoint(ObjectModel point){
        if (indexOfPoint(point) != -1)
            return true;
        return false;
    }

    public ArrayList<ObjectModel> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<ObjectModel> points) {
        this.points = points;
    }

    public ArrayList<Double> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<Double> times) {
        this.times = times;
    }
}
