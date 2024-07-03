package model.objectModel.fighters.finalBoss.abilities.projectile;

import data.Constants;
import model.GameState;
import model.objectModel.fighters.EpsilonModel;
import utils.Vector;

public class ProjectileThread extends Thread{

    private Projectile projectile;
    private Vector origin;
    private EpsilonModel epsilon;

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
            if (deltaModel >= Constants.UPS) {
                turn();
                deltaModel = 0;
            }
        }
    }

    private void turn() {

        if (!projectile.hasArrived()) {
            projectile.navigate();
            projectile.checkArrived();
        }

    }
}
