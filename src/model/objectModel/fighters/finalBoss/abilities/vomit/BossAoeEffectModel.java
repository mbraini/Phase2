package model.objectModel.fighters.finalBoss.abilities.vomit;

import data.Constants;
import model.objectModel.EffectModel;
import utils.Vector;
import utils.area.Circle;

public class BossAoeEffectModel extends EffectModel {

    public BossAoeEffectModel(Vector center, String id){
        this.id = id;
        area = new Circle(Constants.VOMIT_RADIOS ,center);
    }

    @Override
    public void die() {

    }
}
