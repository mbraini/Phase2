package model.objectModel.projectiles;

import model.logics.aoe.OverTimeAOE;

public abstract class LaserModel extends ProjectileModel{

    protected OverTimeAOE aoe;

    public OverTimeAOE getAoe() {
        return aoe;
    }

    public void setAoe(OverTimeAOE aoe) {
        this.aoe = aoe;
    }
}
