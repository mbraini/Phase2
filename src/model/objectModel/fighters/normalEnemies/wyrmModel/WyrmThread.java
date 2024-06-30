package model.objectModel.fighters.normalEnemies.wyrmModel;

import data.Constants;
import model.GameState;
import utils.Math;
import utils.Vector;

public class WyrmThread extends Thread{
    private final static double thetaD = java.lang.Math.PI / Constants.WYRM_THETA_UPDATE_COUNT;
    private WyrmModel wyrmModel;
    private Vector origin;
    private boolean isInPositiveDirection;
    public WyrmThread(WyrmModel wyrmModel , Vector origin ,boolean isInPositiveDirection){
        this.wyrmModel = wyrmModel;
        this.origin = origin.clone();
        this.isInPositiveDirection = isInPositiveDirection;
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
            if (deltaModel >= Constants.WYRM_THREAD_REFRESH_RATE) {
                rotateModel();
                deltaModel = 0;
            }
        }
    }

    private void rotateModel() {
        Vector newPosition;
        if (isInPositiveDirection) {
            newPosition = Math.RotateByTheta(wyrmModel.getPosition(), origin, thetaD);
            wyrmModel.setTheta(wyrmModel.getTheta() + thetaD);
        }
        else {
            newPosition = Math.RotateByTheta(wyrmModel.getPosition() ,origin ,-thetaD);
            wyrmModel.setTheta(wyrmModel.getTheta() - thetaD);
        }
        wyrmModel.setPosition(newPosition);

    }
}
