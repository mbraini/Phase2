package model.skillTreeAbilities;

import controller.configs.Configs;
import controller.enums.SkillTreeAbilityType;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;

public class Ares extends SkillTreeAbility{

    public Ares(){
        isBought = Configs.SkillTreeConfigs.aresBought;
        unlockXpCost = 750;
        type = SkillTreeAbilityType.ares;
        initTimer();
    }


    @Override
    protected void cast() {
        canCast = false;
        EpsilonModel epsilon = ModelData.getEpsilon();
        epsilon.setEpsilonBulletDamage(epsilon.getEpsilonBulletDamage() + 2);
        epsilon.setMeleeAttack(epsilon.getMeleeAttack() + 2);
        coolDownTimer.start();
    }

}
