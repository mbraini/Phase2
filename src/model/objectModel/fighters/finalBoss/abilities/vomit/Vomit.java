package model.objectModel.fighters.finalBoss.abilities.vomit;

import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;

public class Vomit extends Ability {

    private Boss boss;

    public Vomit(Boss boss){
        this.boss = boss;
    }

    @Override
    protected void ownHelpers() {
        boss.getHead().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();

    }

    @Override
    protected void endAbility() {

    }
}
