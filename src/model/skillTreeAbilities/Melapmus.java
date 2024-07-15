package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Melapmus extends SkillTreeAbility{

    private EpsilonModel epsilonModel;

    public Melapmus(){
        unlockXpCost = 750;
        type = SkillTreeAbilityType.melampus;
        initTimer();
        initEpsilon();
    }

    private void initEpsilon() {
        epsilonModel = ModelData.getEpsilon();
    }


    @Override
    protected void cast() {
        canCast = false;
        epsilonModel.setChanceOfSurvival(epsilonModel.getChanceOfSurvival() + 5);
        coolDownTimer.start();
    }

    @Override
    public void setUp() {
        super.setUp();
        epsilonModel = ModelData.getEpsilon();
    }
}
