package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Proteus extends SkillTreeAbility{

    private EpsilonModel epsilonModel;

    public Proteus(){
        unlockXpCost = 1000;
        type = SkillTreeAbilityType.proteus;
        initTimer();
        initEpsilon();
    }

    private void initEpsilon() {
        epsilonModel = ModelData.getEpsilon();
    }


    @Override
    protected void cast() {
        canCast = false;
        epsilonModel.addVertex();
        coolDownTimer.start();
    }

    @Override
    protected void setUp() {
        super.setUp();
        epsilonModel = ModelData.getEpsilon();
    }
}
