package model.objectModel.fighters.finalBoss.abilities.vomit;

import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.objectModel.frameModel.FrameModel;
import utils.Helper;

import java.util.ArrayList;

public class VomitThread extends Thread{
    private ArrayList<BossAoeEffectModel> aoeEffectModels;
    private Vomit vomit;
    private double time;
    private FrameModel epsilonFrame;

    public VomitThread(Vomit vomit ,FrameModel epsilonFrame){
        this.vomit = vomit;
        this.epsilonFrame = epsilonFrame;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isPause() && !GameState.isOver() && !isInterrupted()) {
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.SQUEEZE_THREAD_REFRESH_RATE) {
                update();
                deltaModel = 0;
                time += Constants.SQUEEZE_THREAD_REFRESH_RATE;
            }
        }
    }

    private void update() {
        fireIf();
    }

    private void fireIf() {
        if (time % 1000 == 0){
            BossAoeEffectModel effectModel = new BossAoeEffectModel(
                    Helper.createRandomPosition(epsilonFrame),
                    Helper.RandomStringGenerator(Constants.ID_SIZE)
            );
            Spawner.addBossEffect(effectModel);
        }
        if (time >= Constants.VOMIT_DURATION_TIME){
            vomit.endAbility();
        }
    }
}
