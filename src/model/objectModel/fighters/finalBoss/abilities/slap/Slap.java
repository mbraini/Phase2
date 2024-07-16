package model.objectModel.fighters.finalBoss.abilities.slap;

import data.Constants;
import model.animations.DashAnimation;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
import model.objectModel.fighters.finalBoss.bossHelper.BossHelper;
import utils.Math;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Slap extends Ability {

    private Boss boss;
    private BossHelper helper;
    private EpsilonModel epsilonModel;
    private Timer timer;
    private Timer unsetUptimer;

    public Slap(Boss boss ,EpsilonModel epsilonModel){
        this.boss = boss;
        this.epsilonModel = epsilonModel;
        chooseHelper();
    }

    @Override
    protected void setUp() {
        ownHelper(helper);
        helper.setHovering(false);
        helper.setHasMeleeAttack(true);
        helper.setMeleeAttack(10);
    }

    @Override
    protected void unsetUp() {
        disownHelper(helper);
        helper.setHovering(false);
        helper.setHasMeleeAttack(false);
        helper.setMeleeAttack(0);
    }

    @Override
    public void activate() {
        super.activate();
        slapAnimation();
        timer = new Timer(Constants.ABILITY_SETUP_DELAY * 3 / 2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endAbility();
                timer.stop();
            }
        });
        timer.start();
    }

    @Override
    protected void endAbility() {
        helper.setAcceleration(0 ,0);
        helper.setVelocity(0 ,0);
        endAnimation();
        unsetUpTimer();
    }

    private void unsetUpTimer() {
        unsetUptimer = new Timer(Constants.ABILITY_UNSETUP_DELAY * 3 / 4, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unsetUp();
                unsetUptimer.stop();
            }
        });
        unsetUptimer.start();
    }

    private void endAnimation() {
        helper.setHovering(true);
        Vector direction;
        Vector destination;
        if (helper == boss.getLeftHand()) {
            destination = new Vector(
                    Constants.HAND_DIMENSION.width / 2d,
                    Constants.SCREEN_SIZE.height / 2d
            );
        }
        else if (helper == boss.getRightHand()) {
            destination = new Vector(
                    Constants.SCREEN_SIZE.width - Constants.HAND_DIMENSION.width / 2d ,
                    Constants.SCREEN_SIZE.height / 2d
            );
        }
        else {
            destination = new Vector(
                    Constants.SCREEN_SIZE.width / 2d,
                    Constants.SCREEN_SIZE.height - Constants.HAND_DIMENSION.height / 2d
            );
        }
        direction = Math.VectorAdd(
                destination,
                Math.ScalarInVector(-1 ,helper.getPosition())
        );
        HelperAnimation(helper ,direction);
    }

    private void HelperAnimation(BossHelper helper, Vector direction) {
        new DashAnimation(
                helper,
                direction,
                Constants.ABILITY_UNSETUP_DELAY,
                Math.VectorSize(direction),
                0,
                false
        ).StartAnimation();
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
                Constants.ABILITY_SETUP_DELAY,
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
}
