package model.skillTreeAbilities;

import controller.Controller;
import controller.enums.SkillTreeAbilityType;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class Proteus extends SkillTreeAbility{

    private EpsilonModel epsilonModel;

    public Proteus(){
        unlockXpCost = 1000;
        type = SkillTreeAbilityType.proteus;
        initTimer();
        initEpsilon();
    }

    private void initEpsilon() {
        epsilonModel = Controller.getController(Controller.getIP()).getModelData().getEpsilon();
    }


    @Override
    protected void cast() {
        canCast = false;
        epsilonModel.addVertex();
        coolDownTimer.start();
    }

    @Override
    public void setUp() {
        super.setUp();
        epsilonModel = Controller.getController(Controller.getIP()).getModelData().getEpsilon();
    }
}
