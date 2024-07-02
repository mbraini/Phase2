package model.objectModel.fighters.finalBoss.abilities.squeeze;

import data.Constants;
import model.animations.DashAnimation;
import model.interfaces.Navigator;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
import model.objectModel.frameModel.FrameModel;
import utils.Math;
import utils.Vector;

public class Squeeze extends Ability implements Navigator {

    private SqueezeThread thread;
    private FrameModel epsilonFrame;
    private Boss boss;
    private boolean arrived;
    private final SqueezeNavigator navigator;

    public Squeeze(Boss boss, FrameModel epsilonFrame){
        this.epsilonFrame = epsilonFrame;
        this.boss = boss;
        thread = new SqueezeThread(this);
        navigator = new SqueezeNavigator(epsilonFrame ,boss.getLeftHand() ,boss.getRightHand());
    }


    @Override
    protected void ownHelpers() {
        boss.getLeftHand().setInUse(true);
        boss.getRightHand().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();
        thread.start();
    }

    @Override
    protected void endAbility() {
        boss.getRightHand().setInUse(false);
        boss.getLeftHand().setInUse(false);
        boss.getLeftHand().getFrame().setSolid(false);
        boss.getRightHand().getFrame().setSolid(false);
        doAnimation();
        thread.interrupt();
    }

    private void doAnimation() {
        Vector leftD = Math.VectorAdd(
                new Vector(
                        Constants.HAND_DIMENSION.width / 2d,
                        Constants.SCREEN_SIZE.height / 2d
                ),
                Math.ScalarInVector(-1, boss.getLeftHand().getPosition())
        );
        new DashAnimation(
                boss.getLeftHand(),
                leftD,
                1000,
                Math.VectorSize(leftD),
                0
        ).StartAnimation();
        Vector rightD = Math.VectorAdd(
                new Vector(
                        Constants.SCREEN_SIZE.width - Constants.HAND_DIMENSION.width / 2d,
                        Constants.SCREEN_SIZE.height / 2d
                ),
                Math.ScalarInVector(-1, boss.getRightHand().getPosition())
        );
        new DashAnimation(
                boss.getRightHand(),
                rightD,
                1000,
                Math.VectorSize(rightD),
                0
        ).StartAnimation();
    }

    @Override
    public boolean hasArrived() {
        return arrived;
    }

    @Override
    public void navigate() {
        new SqueezeNavigator(epsilonFrame ,boss.getLeftHand() ,boss.getRightHand()).navigate();
    }


    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public void checkArrived() {
        arrived = navigator.hasArrived();
        if (arrived){
            setUpArrived();
        }
    }

    private void setUpArrived() {
        boss.getLeftHand().setVelocity(new Vector());
        boss.getRightHand().setVelocity(new Vector());
        boss.getLeftHand().getFrame().setSolid(true);
        boss.getRightHand().getFrame().setSolid(true);
    }
}
