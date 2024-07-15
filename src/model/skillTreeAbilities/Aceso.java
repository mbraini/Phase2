package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import controller.manager.loading.SkippedByJson;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aceso extends SkillTreeAbility{
    @SkippedByJson
    private Timer healTimer;
    private EpsilonModel epsilon;
    private int healAmount;

    public Aceso(){
        unlockXpCost = 500;
        type = SkillTreeAbilityType.aceso;
        initTimer();
        initEpsilon();
        initHealTimer();
        healTimer.start();
    }

    private void initEpsilon() {
        epsilon = ModelData.getEpsilon();
    }

    private void initHealTimer() {
        healTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                epsilon.setHP(epsilon.getHP() + healAmount);
                if (epsilon.getHP() > 100)
                    epsilon.setHP(100);
            }
        });
    }
    @Override
    protected void cast() {
        canCast = false;
        healAmount++;
        coolDownTimer.start();
    }

    @Override
    protected void stop() {
        super.stop();
        healTimer.stop();
    }

    @Override
    public void setUp() {
        super.setUp();
        initHealTimer();
        if (healAmount != 0) {
            healTimer.start();
        }
    }
}
