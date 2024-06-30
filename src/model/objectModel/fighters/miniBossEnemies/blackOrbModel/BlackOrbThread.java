package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.logics.aoe.OverTimeAOE;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.projectiles.BlackOrbLaserModel;
import utils.Helper;

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
        ArrayList<FrameModel> frames = blackOrbModel.getFrameModels();
        ArrayList<OrbModel> orbs = blackOrbModel.getOrbModels();
        for (int i = 0 ;i < orbs.size() ;i++){
            if (i == index - 1)
                continue;
            OrbModel orbModelNumberi = orbs.get(i);
            OrbModel orbModelNumberIndex = orbs.get(index - 1);

            OverTimeAOE aoe = new OverTimeAOE();
            BlackOrbLaserModel laserModel = new BlackOrbLaserModel(
                    aoe,
                    Helper.RandomStringGenerator(Constants.ID_SIZE)
            );
            BlackOrbLaserEffectModel effectModel = new BlackOrbLaserEffectModel(
                    orbModelNumberi,
                    orbModelNumberIndex ,
                    laserModel.getId()
            );
            laserModel.getAoe().addShape(effectModel);

            blackOrbModel.addLaser(laserModel);
            orbModelNumberi.addLaser(laserModel);
            orbModelNumberIndex.addLaser(laserModel);
            Spawner.addBlackOrbEffectModel(effectModel);
        }
    }
}
