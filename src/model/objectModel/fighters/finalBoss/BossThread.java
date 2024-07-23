package model.objectModel.fighters.finalBoss;

import controller.manager.GameState;
import model.ModelData;
import model.animations.BossPhase2Animation;
import model.objectModel.ObjectModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.abilities.AbilityCaster;
import model.objectModel.fighters.finalBoss.abilities.AbilityType;
import model.objectModel.fighters.finalBoss.bossAI.BossAI;
import model.objectModel.frameModel.FrameModel;

import java.util.ArrayList;
import java.util.Random;

public class BossThread extends Thread {

    private EpsilonModel epsilon;
    private FrameModel epsilonFrame;
    private ArrayList<ObjectModel> models;
    private Boss boss;
    private final AbilityCaster abilityCaster;
    private AbilityType abilityType;
    private final BossAI bossAI;
    private int ability;

    public BossThread(Boss boss){
        synchronized (ModelData.getModels()) {
            epsilon = ModelData.getEpsilon();
            epsilonFrame = ModelData.getEpsilonFrame();
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
        if (boss.getHead().getHP() < 0)
            return;
        synchronized (ModelData.getModels()) {
            models = (ArrayList<ObjectModel>) ModelData.getModels().clone();
        }
        bossAI.setModels(models);
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
        bossAI.dash(boss.getHead());
        bossAI.dash(boss.getLeftHand());
        bossAI.dash(boss.getRightHand());
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
        if (bossAI.isInSqueezePosition()){
            abilityType = AbilityType.squeeze;
            return;
        }
        if (bossAI.isInProjectileRange()){
            if (boss.getAttackPhase() == 1) {
                abilityType = AbilityType.projectile;
                return;
            }
            else {
                Random random = new Random();
                int randomNum = random.nextInt(0 ,3);
                if (randomNum == 0) {
                    abilityType = AbilityType.projectile;
                    return;
                }
                else if (randomNum == 1) {
                    abilityType = AbilityType.rapidFire;
                    return;
                }
                else {
                    abilityType = AbilityType.vomit;
                    return;
                }
            }
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
