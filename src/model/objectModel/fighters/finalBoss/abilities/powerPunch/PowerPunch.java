package model.objectModel.fighters.finalBoss.abilities.powerPunch;

import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
import model.objectModel.frameModel.FrameModel;

public class PowerPunch extends Ability {
    private Boss boss;
    private FrameModel epsilonFrame;
    public PowerPunch(Boss boss ,FrameModel epsilonFrame){
        this.boss = boss;
        this.epsilonFrame = epsilonFrame;
    }


    @Override
    protected void ownHelpers() {
        boss.getPunch().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();

    }

    @Override
    protected void endAbility() {

    }
}
