package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aceso extends SkillTreeAbility{

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

    private void initTimer() {
        coolDownTimer = new Timer(Constants.SKILL_TREE_ABILITY_TIMER_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                coolDownTimePassed += Constants.SKILL_TREE_ABILITY_TIMER_REFRESH_RATE;
                if (coolDownTimePassed >= inGameCoolDownTime){
                    canCast = true;
                    coolDownTimePassed = 0;
                    coolDownTimer.stop();
                }
            }
        });
    }
    @Override
    protected void cast() {
        canCast = false;
        healAmount++;
        coolDownTimer.start();
    }
}
