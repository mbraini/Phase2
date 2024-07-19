package model.skillTreeAbilities;

import controller.Controller;
import controller.enums.SkillTreeAbilityType;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class Astrape extends SkillTreeAbility{

    public Astrape(){
        unlockXpCost = 1000;
        type = SkillTreeAbilityType.astrape;
        initTimer();
    }


    @Override
    protected void cast() {
        canCast = false;
        EpsilonModel epsilon = (EpsilonModel) Controller.getController(Controller.getIP()).getModelData().getModels().getFirst();
        epsilon.setEpsilonDamageOnCollision(epsilon.getEpsilonDamageOnCollision() + 2);
        coolDownTimer.start();
    }


}
