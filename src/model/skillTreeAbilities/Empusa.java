package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Empusa extends SkillTreeAbility{
    private EpsilonModel epsilonModel;

    public Empusa() {
        unlockXpCost = 750;
        type = SkillTreeAbilityType.empusa;
        initTimer();
        initEpsilon();
    }

    private void initEpsilon() {
        this.epsilonModel = ModelData.getEpsilon();
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
        Dimension newSize = new Dimension(
                epsilonModel.getSize().width * 9 / 10,
                epsilonModel.getSize().height * 9 / 10
        );
        epsilonModel.setSize(newSize);
        coolDownTimer.start();
    }

}
