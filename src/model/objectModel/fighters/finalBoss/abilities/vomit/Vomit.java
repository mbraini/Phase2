package model.objectModel.fighters.finalBoss.abilities.vomit;

import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
import model.objectModel.frameModel.FrameModel;

public class Vomit extends Ability {

    private Boss boss;
    private VomitThread thread;

    public Vomit(Boss boss , FrameModel epsilonFrame){
        this.boss = boss;
        thread = new VomitThread(this ,epsilonFrame);
    }

    @Override
    protected void ownHelpers() {
        boss.getHead().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();
        thread.start();
    }

    @Override
    protected void endAbility() {
        boss.getHead().setInUse(false);
        thread.interrupt();
    }
}
