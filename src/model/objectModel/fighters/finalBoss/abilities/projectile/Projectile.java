package model.objectModel.fighters.finalBoss.abilities.projectile;

import constants.Constants;
import model.ModelData;
import model.animations.DashAnimation;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
import model.objectModel.fighters.finalBoss.bossHelper.BossHelper;
import model.objectModel.fighters.finalBoss.bossHelper.HeadModel;
import utils.Math;
import utils.Vector;

public class Projectile extends Ability {

    private Boss boss;
    private EpsilonModel epsilon;
    private boolean arrived;
    private ProjectileThread thread;
    private final ProjectileNavigator navigator;

    public Projectile(Boss boss){
        this.boss = boss;
        synchronized (ModelData.getModels()) {
            this.epsilon = (EpsilonModel) ModelData.getModels().getFirst();
        }
        navigator = new ProjectileNavigator(epsilon ,boss.getHead());
        thread = new ProjectileThread(this ,epsilon);
    }


    @Override
    protected void setUp() {
        ownHelper(boss.getHead());
        ownHelper(boss.getLeftHand());
        ownHelper(boss.getRightHand());
        boss.getHead().setHovering(true);
        boss.getLeftHand().setHovering(true);
        boss.getRightHand().setHovering(true);
    }

    @Override
    protected void unsetUp() {
        disownHelper(boss.getHead());
        disownHelper(boss.getLeftHand());
        disownHelper(boss.getRightHand());
        boss.getHead().setHovering(false);
        boss.getLeftHand().setHovering(false);
        boss.getRightHand().setHovering(false);

    }

    @Override
    public void activate() {
        super.activate();
        bossHandsAnimations();
        bossHeadAnimation();
        thread.setOrigin(epsilon.getPosition().clone());
        thread.start();
    }
    @Override
    protected void endAbility() {
        endAnimations();
        try {
            Thread.sleep(Constants.ABILITY_UNSETUP_DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt();
        unsetUp();
    }

    private void bossHeadAnimation() {
        HeadModel headModel = boss.getHead();
        Vector destination = new Vector(
                epsilon.getPosition().x,
                epsilon.getPosition().y - Constants.PROJECTILE_NAVIGATE_RADIOS
        );
        Vector distance = Math.VectorAdd(
                destination,
                Math.ScalarInVector(-1 ,headModel.getPosition())
        );
        new DashAnimation(
                headModel,
                distance,
                Constants.ABILITY_SETUP_DELAY,
                Math.VectorSize(distance),
                0,
                false
        ).StartAnimation();
    }

    private void bossHandsAnimations() {
        Vector destination = new Vector(
                epsilon.getPosition().x,
                epsilon.getPosition().y - Constants.PROJECTILE_NAVIGATE_RADIOS
        );
        Vector leftDestination = Math.RotateByTheta(
                destination ,
                epsilon.getPosition() ,
                java.lang.Math.PI / 2
        );
        Vector leftDistance = Math.VectorAdd(
                leftDestination,
                Math.ScalarInVector(-1 ,boss.getLeftHand().getPosition())
        );
        new DashAnimation(
                boss.getLeftHand(),
                leftDistance,
                Constants.ABILITY_SETUP_DELAY,
                Math.VectorSize(leftDistance),
                0,
                false
        ).StartAnimation();
        Vector rightDestination = Math.RotateByTheta(
                destination ,
                epsilon.getPosition() ,
                -java.lang.Math.PI / 2
        );
        Vector rightDistance = Math.VectorAdd(
                rightDestination,
                Math.ScalarInVector(-1 ,boss.getRightHand().getPosition())
        );
        new DashAnimation(
                boss.getRightHand(),
                rightDistance,
                Constants.ABILITY_SETUP_DELAY,
                Math.VectorSize(rightDistance),
                0,
                false
        ).StartAnimation();
    }


    private void endAnimations() {
        Vector headDirection = Math.VectorAdd(
                new Vector(Constants.SCREEN_SIZE.width / 2d ,0),
                Math.ScalarInVector(-1 ,boss.getHead().getPosition())
        );
        Vector leftHandDirection = Math.VectorAdd(
                new Vector(0,Constants.SCREEN_SIZE.height / 2d),
                Math.ScalarInVector(-1 ,boss.getLeftHand().getPosition())
        );
        Vector rightHandDirection = Math.VectorAdd(
                new Vector(Constants.SCREEN_SIZE.width - Constants.HAND_DIMENSION.width ,Constants.SCREEN_SIZE.height / 2d),
                Math.ScalarInVector(-1 ,boss.getRightHand().getPosition())
        );
        helperAnimation(boss.getHead() ,headDirection);
        helperAnimation(boss.getLeftHand() ,leftHandDirection);
        helperAnimation(boss.getRightHand() ,rightHandDirection);
    }

    private void helperAnimation(BossHelper helper, Vector direction) {
        new DashAnimation(
                helper,
                direction,
                1000,
                Math.VectorSize(direction),
                0,
                false
        ).StartAnimation();
    }


    public Boss getBoss() {
        return boss;
    }

}
