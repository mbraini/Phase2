package model.objectModel.fighters.finalBoss;

import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.abilities.AbilityCaster;
import model.objectModel.fighters.finalBoss.abilities.AbilityType;
import model.objectModel.fighters.finalBoss.abilities.projectile.Projectile;
import model.objectModel.fighters.finalBoss.abilities.squeeze.Squeeze;
import model.objectModel.frameModel.FrameModel;

public class BossThread extends Thread {

    private EpsilonModel epsilon;
    private FrameModel epsilonFrame;
    private Boss boss;

    public BossThread(Boss boss){
        synchronized (ModelData.getModels()) {
            epsilon = (EpsilonModel) ModelData.getModels().getFirst();
            epsilonFrame = ModelData.getFrames().get(0);
        }
        this.boss = boss;
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
            if (deltaModel >= 10000) {
                updateAbilities();
                deltaModel = 0;
            }
        }

    }

    private void updateAbilities() {
        new AbilityCaster(boss , AbilityType.projectile).cast();
        new AbilityCaster(boss ,AbilityType.squeeze).cast();
    }
}
