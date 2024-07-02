package model.objectModel.fighters.finalBoss;

import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class BossThread extends Thread {

    private EpsilonModel epsilon;

    public BossThread(){
        synchronized (ModelData.getModels()) {
            epsilon = (EpsilonModel) ModelData.getModels().getFirst();
        }
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
            if (deltaModel >= Constants.BOSS_THREAD_REFRESH_RATE) {
                updateAbilities();
                deltaModel = 0;
            }
        }

    }

    private void updateAbilities() {



    }
}
