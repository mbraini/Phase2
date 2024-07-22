package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import controller.manager.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class Athena extends SkillTreeAbility{

    public Athena(){
        unlockXpCost = 1200;
        type = SkillTreeAbilityType.athena;
        initTimer();
    }



    @Override
    protected void cast() {
        canCast = false;
        GameState.setShrinkageVelocity(GameState.getShrinkageVelocity() - 0.2 * GameState.getShrinkageVelocity());
        coolDownTimer.start();
    }

    @Override
    public void setUp() {
        super.setUp();
    }
}
