package model.objectModel.normalEnemies.archmireModel;

import model.objectModel.effectModel.EffectModel;
import utils.Vector;

import java.awt.*;

public class ArchmirePointModel extends EffectModel {
    public ArchmirePointModel(Vector position , String id){
        this.color = Color.RED;
        this.id = id;
        this.position = position;
    }

    @Override
    public void die() {

    }
}
