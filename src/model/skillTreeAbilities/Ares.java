package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import data.Constants;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ares extends SkillTreeAbility{

    public Ares(){
        unlockXpCost = 750;
        type = SkillTreeAbilityType.ares;
        initTimer();
    }

    private void initTimer() {
        coolDownTimer = new Timer(Constants.SKILL_TREE_ABILITY_TIMER_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coolDownTimePassed += Constants.SKILL_TREE_ABILITY_TIMER_REFRESH_RATE;
                if (coolDownTimePassed >= inGameCoolDownTime){
                    canCast = true;
                    coolDownTimer.stop();
                }
            }
        });
    }

    @Override
    protected void cast() {
        canCast = false;
        EpsilonModel epsilon = (EpsilonModel)ModelData.getModels().getFirst();
        epsilon.setEpsilonBulletDamage(epsilon.getEpsilonBulletDamage() + 2);
        epsilon.setMeleeAttack(epsilon.getMeleeAttack() + 2);
        coolDownTimer.start();
    }

}
