package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

import java.awt.*;

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

    @Override
    public void setUp() {
        super.setUp();
        initEpsilon();
    }
}
