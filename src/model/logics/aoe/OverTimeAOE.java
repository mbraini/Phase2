package model.logics.aoe;

import controller.Controller;
import model.objectModel.ObjectModel;
import model.objectModel.EffectModel;
import model.objectModel.fighters.normalEnemies.archmireModel.ArchmireEffectModel;

import java.util.ArrayList;

public class OverTimeAOE extends AOE{
    private ArrayList<EffectModel> shapes;

    public OverTimeAOE(){
        shapes = new ArrayList<>();
    }

    @Override
    public void dealDamage(ArrayList<ObjectModel> models) {

    }

    public void addShape(EffectModel point){
        shapes.add(point);
    }

    public int indexOfPoint(ObjectModel point){
        for (int i = 0; i < shapes.size() ; i++){
            if (shapes.get(i).getPosition().x == point.getPosition().x
                    && shapes.get(i).getPosition().y == point.getPosition().y)
                return i;
        }
        return -1;
    }

    public boolean containsPoint(ObjectModel point){
        if (indexOfPoint(point) != -1)
            return true;
        return false;
    }

    public ArrayList<EffectModel> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<EffectModel> shapes) {
        this.shapes = shapes;
    }

    public void removeShape(EffectModel effectModel) {
        shapes.remove(effectModel);
    }
}
