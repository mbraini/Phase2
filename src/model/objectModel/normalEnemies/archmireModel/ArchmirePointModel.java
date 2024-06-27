package model.objectModel.normalEnemies.archmireModel;

import model.objectModel.ObjectModel;
import utils.Vector;

public class ArchmirePointModel extends ObjectModel {
    public ArchmirePointModel(Vector position , String id){
        this.id = id;
        this.position = position;
    }

    @Override
    public void die() {

    }
}
