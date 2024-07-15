package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Astrape extends SkillTreeAbility{

    public Astrape(){
        unlockXpCost = 1000;
        type = SkillTreeAbilityType.astrape;
        initTimer();
    }


    @Override
    protected void cast() {
        canCast = false;
        EpsilonModel epsilon = (EpsilonModel) ModelData.getModels().getFirst();
        epsilon.setEpsilonDamageOnCollision(epsilon.getEpsilonDamageOnCollision() + 2);
        coolDownTimer.start();
    }


}
