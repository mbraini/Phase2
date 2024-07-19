package model.objectModel.fighters.finalBoss;

import controller.Controller;
import controller.manager.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.abilities.AbilityCaster;
import model.objectModel.fighters.finalBoss.abilities.AbilityType;
import model.objectModel.frameModel.FrameModel;

public class BossThread extends Thread {

    private EpsilonModel epsilon;
    private FrameModel epsilonFrame;
    private Boss boss;
    private final AbilityCaster abilityCaster;
    private AbilityType abilityType;
    private final BossAI bossAI;
    private int ability;

    public BossThread(Boss boss){
        synchronized (Controller.getController(Controller.getIP()).getModelData().getModels()) {
            epsilon = (EpsilonModel) Controller.getController(Controller.getIP()).getModelData().getModels().getFirst();
            epsilonFrame = Controller.getController(Controller.getIP()).getModelData().getFrames().getFirst();
        }
        this.boss = boss;
        bossAI = new BossAI(boss ,epsilon);
        abilityCaster = new AbilityCaster(boss ,epsilonFrame ,epsilon);
    }


    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isOver()) {
            if (GameState.isPause()){
                lastTime = System.nanoTime();
                continue;
            }
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= 1000) {
                updateAbilities();
                deltaModel = 0;
            }
        }

    }

    private void updateAbilities() {
        defineAbility();
        if (abilityType != null) {
            abilityCaster.setAbilityType(abilityType);
            if (abilityCaster.canCast()) {
                try {
                    System.out.println("SLEEPING");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                abilityCaster.cast();
                System.out.println("CASTING!");
            }
        }
    }

    private void defineAbility() {
        abilityType = AbilityType.quake;
//        if (bossAI.isInSqueezePosition()){
//            abilityType = AbilityType.squeeze;
//            return;
//        }
//        if (bossAI.isInProjectileRange()){
//            abilityType = AbilityType.projectile;
//            return;
//        }
//        if (ability >= 6)
//            ability = ability - 6;
//        if (ability == 0 || ability == 1) {
//            abilityType = null;
//            ability++;
//            return;
//        }
//        abilityType = AbilityType.values()[ability];
//        ability++;
    }

}
