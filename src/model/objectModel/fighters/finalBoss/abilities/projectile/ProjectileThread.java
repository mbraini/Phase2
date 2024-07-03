package model.objectModel.fighters.finalBoss.abilities.projectile;

import data.Constants;
import model.GameState;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

public class ProjectileThread extends Thread{

    private Projectile projectile;
    private Vector origin;
    private EpsilonModel epsilon;
    private final double thetaD = java.lang.Math.PI / (1000 / Constants.PROJECTILE_THREAD_REFRESH_RATE);
    private double time;

    public ProjectileThread(Projectile projectile , EpsilonModel epsilon){
        this.epsilon = epsilon;
        this.projectile = projectile;
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isPause() && !GameState.isOver()) {
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.PROJECTILE_THREAD_REFRESH_RATE) {
                turn();
                deltaModel = 0;
                time += Constants.PROJECTILE_THREAD_REFRESH_RATE;
            }
        }
    }

    private void turn() {
        if (!projectile.hasArrived()) {
            projectile.navigate();
            projectile.checkArrived();
        }
        turnAround();
        fireIf();
    }

    private void fireIf() {

    }

    private void turnAround() {
        if (origin == null)
            return;
        Vector newPosition;
        newPosition = Math.RotateByTheta(projectile.getBoss().getHead().getPosition(), origin, thetaD);
        projectile.getBoss().getHead().setTheta(projectile.getBoss().getHead().getTheta() + thetaD);
        Vector previousPosition = projectile.getBoss().getHead().getPosition().clone();
        projectile.getBoss().getHead().setPosition(newPosition);
        Vector moved = Math.VectorAdd(
                newPosition,
                Math.ScalarInVector(-1 ,previousPosition)
        );
    }

    public Vector getOrigin() {
        return origin;
    }

    public void setOrigin(Vector origin) {
        this.origin = origin;
    }
}
