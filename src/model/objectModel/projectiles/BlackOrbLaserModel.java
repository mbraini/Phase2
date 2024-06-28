package model.objectModel.projectiles;

import model.logics.aoe.OverTimeAOE;
import model.objectModel.EffectModel;

public class BlackOrbLaserModel extends LaserModel {

    public BlackOrbLaserModel(OverTimeAOE aoe ,String id){
        this.aoe = aoe;
        this.id = id;
    }

    @Override
    public void die() {

    }
}
