package model.objectModel.fighters.finalBoss;

import controller.manager.GameState;
import model.ModelData;
import model.animations.BossPhase2Animation;
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
        synchronized (ModelData.getModels()) {
            epsilon = (EpsilonModel) ModelData.getModels().getFirst();
            epsilonFrame = ModelData.getFrames().get(0);
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
            long now = System.nanoTime();
            if (GameState.isPause() || GameState.isInAnimation()){
                lastTime = now;
                continue;
            }
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= 100) {
                updateAbilities();
                deltaModel = 0;
            }
        }

    }

    private void updateAbilities() {
        System.out.println("HEAD HP : " + boss.getHead().getHP());
        System.out.println("LEFT HAND HP : " + boss.getLeftHand().getHP());
        System.out.println("RIGHT HAND HP : " + boss.getRightHand().getHP());
        if (boss.getAttackPhase() != 2) {
            if (phase2())
                return;
        }
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

    private boolean phase2() {
        if (boss.getHead().getHP() <= 200) {
            new BossPhase2Animation(boss).StartAnimation();
            boss.setPhaseAttack(2);
            return true;
        }
        return false;
    }

    private void defineAbility() {
        abilityType = AbilityType.squeeze;
        if (bossAI.isInSqueezePosition()){
            abilityType = AbilityType.squeeze;
            return;
        }
        if (bossAI.isInProjectileRange()){
            abilityType = AbilityType.projectile;
            return;
        }
        if (boss.getAttackPhase() == 1) {
            if (ability >= 2)
                ability = ability - 2;
            abilityType = AbilityType.values()[ability];
            ability++;
            return;
        }
        if (ability >= 7)
            ability = ability - 7;
        if (ability == 0 || ability == 1) {
            abilityType = null;
            ability++;
            return;
        }
        abilityType = AbilityType.values()[ability];
        ability++;
    }

}
