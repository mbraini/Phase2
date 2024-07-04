package model.objectModel.fighters.finalBoss.abilities.rapidFire;

import data.Constants;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;

import javax.swing.*;

public class RapidFire extends Ability {

    private Timer shooter;

    private Boss boss;

    public RapidFire(Boss boss){
        this.boss = boss;
    }


    protected void ownHelpers() {
        boss.getHead().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();
        shooter = new Timer(Constants.BOSS_BULLET_DELAY_TIME,new HeadShooter(this));
        shooter.start();
    }

    @Override
    protected void endAbility() {
        boss.getHead().setInUse(false);
    }

    public Timer getShooter() {
        return shooter;
    }

    public Boss getBoss() {
        return boss;
    }
}
