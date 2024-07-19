package model.skillTreeAbilities;

import controller.Controller;
import controller.enums.SkillTreeAbilityType;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class Ares extends SkillTreeAbility{

    public Ares(){
        unlockXpCost = 750;
        type = SkillTreeAbilityType.ares;
        initTimer();
    }


    @Override
    protected void cast() {
        canCast = false;
        EpsilonModel epsilon = (EpsilonModel) Controller.getController(Controller.getIP()).getModelData().getModels().getFirst();
        epsilon.setEpsilonBulletDamage(epsilon.getEpsilonBulletDamage() + 2);
        epsilon.setMeleeAttack(epsilon.getMeleeAttack() + 2);
        coolDownTimer.start();
    }

}
