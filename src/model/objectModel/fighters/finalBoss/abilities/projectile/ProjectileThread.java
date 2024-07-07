package model.objectModel.fighters.finalBoss.abilities.projectile;

import controller.enums.ModelType;
import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.animations.DashAnimation;
import model.interfaces.HasVertices;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.bossHelper.BossHelper;
import model.objectModel.fighters.finalBoss.bossHelper.HandModel;
import utils.Math;
import utils.Vector;

public class ProjectileThread extends Thread{

    private Projectile projectile;
    private Vector origin;
    private EpsilonModel epsilon;
    private final double thetaD = (java.lang.Math.PI / 2) / (1000 / Constants.PROJECTILE_THREAD_REFRESH_RATE);
    private double time;

    public ProjectileThread(Projectile projectile , EpsilonModel epsilon){
        this.epsilon = epsilon;
        this.projectile = projectile;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        setHelpers();

        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isPause() && !GameState.isOver() && !isInterrupted()) {
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.PROJECTILE_THREAD_REFRESH_RATE) {
                time += Constants.PROJECTILE_THREAD_REFRESH_RATE;
                update();
                deltaModel = 0;
            }
        }
    }

    private void setHelpers() {
        projectile.getBoss().getLeftHand().setHovering(true);
        projectile.getBoss().getRightHand().setHovering(true);
        projectile.getBoss().getHead().setHovering(true);
    }

    private void update() {
        turnAround();
        if (time % 2000 == 0)
            moveHands();
        fireIf();
        if (time >= Constants.PROJECTILE_DURATION)
            projectile.endAbility();
    }

    private void moveHands() {
        double y = epsilon.getPosition().y;
        HandModel leftHand = projectile.getBoss().getLeftHand();
        HandModel rightHand = projectile.getBoss().getRightHand();

        new DashAnimation(
                leftHand,
                new Vector(0 ,y - leftHand.getPosition().y),
                1000,
                java.lang.Math.abs(y - leftHand.getPosition().y),
                0,
                false
        ).StartAnimation();

        new DashAnimation(
                rightHand,
                new Vector(0 ,y - rightHand.getPosition().y),
                1000,
                java.lang.Math.abs(y - rightHand.getPosition().y),
                0,
                false
        ).StartAnimation();
    }

    private void fireIf() {
        if (time % 1000 != 0){
            return;
        }
        fire(projectile.getBoss().getLeftHand());
        fire(projectile.getBoss().getRightHand());
    }

    private void fire(HandModel hand) {
        Vector position = hand.getPosition();
        Vector direction = Math.VectorAdd(
                Math.ScalarInVector(-1 ,position),
                epsilon.getPosition()
        );

        Vector bulletPosition = Math.VectorAdd(
                position,
                Math.VectorWithSize(
                        direction ,
                        Constants.BOSS_BULLET_RADIOS + Constants.HAND_DIMENSION.width / 2d
                )
        );

        Spawner.addProjectile(bulletPosition ,direction , ModelType.bossBullet);
    }

    private void turnAround() {
        if (origin == null)
            return;
        turnAroundObject(projectile.getBoss().getHead());
        turnAroundObject(projectile.getBoss().getLeftHand());
        turnAroundObject(projectile.getBoss().getRightHand());
    }

    private void turnAroundObject(BossHelper bossHelper) {
        Vector newPosition;
        newPosition = Math.RotateByTheta(bossHelper.getPosition(), origin, thetaD);
        Vector previousPosition = bossHelper.getPosition().clone();
        bossHelper.setPosition(newPosition);
        Vector moved = Math.VectorAdd(
                newPosition,
                Math.ScalarInVector(-1 ,previousPosition)
        );
        if (bossHelper instanceof HasVertices)
            ((HasVertices) bossHelper).UpdateVertices(moved.x ,moved.y ,0);
    }

    public Vector getOrigin() {
        return origin;
    }

    public void setOrigin(Vector origin) {
        this.origin = origin;
    }
}
