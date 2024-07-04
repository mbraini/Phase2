package model.objectModel.fighters.finalBoss.abilities.projectile;

import data.Constants;
import model.ModelData;
import model.animations.DashAnimation;
import model.interfaces.Navigator;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
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
    protected void ownHelpers() {
        boss.getHead().setInUse(true);
        boss.getLeftHand().setInUse(true);
        boss.getRightHand().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();
        bossHandsAnimations();
        bossHeadAnimation();
        thread.setOrigin(epsilon.getPosition().clone());
        thread.start();
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
                2000,
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
                2000,
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
                2000,
                Math.VectorSize(rightDistance),
                0,
                false
        ).StartAnimation();
    }

    @Override
    protected void endAbility() {
        thread.interrupt();
        boss.getLeftHand().setInUse(false);
        boss.getRightHand().setInUse(false);
        boss.getHead().setInUse(false);
        backAnimation();
    }

    private void backAnimation() {
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
                0,
                false
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
                0,
                false
        ).StartAnimation();
    }


    public Boss getBoss() {
        return boss;
    }

}
