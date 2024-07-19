package model.skillTreeAbilities;

import controller.Controller;
import controller.enums.SkillTreeAbilityType;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class Chiron extends SkillTreeAbility{
    private EpsilonModel epsilonModel;

    public Chiron(){
        unlockXpCost = 900;
        type = SkillTreeAbilityType.chiron;
        initTimer();
        initEpsilon();
    }

    private void initEpsilon() {
        epsilonModel = Controller.getController(Controller.getIP()).getModelData().getEpsilon();
    }


    @Override
    protected void cast() {
        canCast = false;
        epsilonModel.setLifeSteal(epsilonModel.getLifeSteal() + 3);
        coolDownTimer.start();
    }

    @Override
    public void setUp() {
        super.setUp();
        initEpsilon();
    }
}
