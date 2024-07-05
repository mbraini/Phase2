package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import data.Constants;
import model.GameState;
import model.objectModel.frameModel.FrameModel;

import java.util.ArrayList;

public class BlackOrbThread extends Thread{
    private BlackOrbModel blackOrbModel;
    private double time;

    public BlackOrbThread(BlackOrbModel blackOrbModel){
        this.blackOrbModel = blackOrbModel;
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
            if (deltaModel >= Constants.BLACK_ORB_THEAD_REFRESH_RATE) {
                updateBlackOrb();
                deltaModel = 0;
                time += Constants.BLACK_ORB_THEAD_REFRESH_RATE;
            }
        }

    }

    private void updateBlackOrb() {
        ArrayList<FrameModel> frames = blackOrbModel.getFrameModels();
        ArrayList<OrbModel> orbs = blackOrbModel.getOrbModels();


    }

    public void connectLasers(int index) {

    }
}
