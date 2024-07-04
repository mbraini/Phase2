package model.objectModel.fighters.finalBoss.abilities.slap;

import data.Constants;
import model.animations.DashAnimation;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
import model.objectModel.fighters.finalBoss.bossHelper.BossHelper;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;
import java.util.Random;

public class Slap extends Ability {

    private Boss boss;
    private BossHelper helper;
    private EpsilonModel epsilonModel;

    public Slap(Boss boss ,EpsilonModel epsilonModel){
        this.boss = boss;
        this.epsilonModel = epsilonModel;
    }

    @Override
    protected void ownHelpers() {
        helper.setInUse(true);
    }

    @Override
    public void activate() {
        chooseHelper();
        ownHelpers();
        slapAnimation();
    }

    private void slapAnimation() {

        Vector direction = Math.VectorAdd(
                epsilonModel.getPosition(),
                Math.ScalarInVector(-1 ,helper.getPosition())
        );

        double distance = Math.VectorSize(direction) - Constants.HAND_DIMENSION.width / 2d;
        new DashAnimation(
                helper,
                direction,
                2000,
                distance,
                0,
                true
        ).StartAnimation();

    }

    private void chooseHelper() {
        ArrayList<BossHelper> helpers = new ArrayList<>();
        if (!boss.getLeftHand().isInUse())
            helpers.add(boss.getLeftHand());
        if (!boss.getRightHand().isInUse())
            helpers.add(boss.getRightHand());
        if (!boss.getPunch().isInUse())
            helpers.add(boss.getPunch());
        Random random = new Random();
        int index = random.nextInt(0 ,helpers.size());
        helper = helpers.get(index);
    }

    @Override
    protected void endAbility() {
        helper.setInUse(false);
    }
}
