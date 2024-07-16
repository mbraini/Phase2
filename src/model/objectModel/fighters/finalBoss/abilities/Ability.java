package model.objectModel.fighters.finalBoss.abilities;

import model.objectModel.fighters.finalBoss.bossHelper.BossHelper;

public abstract class Ability {

    protected void ownHelper(BossHelper helper) {
        helper.setInUse(true);
    }
    protected void disownHelper(BossHelper helper) {
        helper.setInUse(false);
    }
    protected abstract void setUp();
    protected abstract void unsetUp();
    public void activate() {
        setUp();
    }
    protected void endAbility() {
        unsetUp();
    }

}
