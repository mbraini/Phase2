package model.objectModel.fighters.finalBoss.abilities;

import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.projectile.Projectile;
import model.objectModel.fighters.finalBoss.abilities.squeeze.Squeeze;

public class AbilityCaster {

    private AbilityType abilityType;
    private Boss boss;

    public AbilityCaster(Boss boss){
        this.boss = boss;
    }

    public void cast(){
        switch (abilityType){
            case squeeze :
                new Squeeze(boss).activate();
                break;
            case projectile:
                new Projectile(boss).activate();
                break;
        }
    }

    public boolean canCast(){
        switch (abilityType){
            case squeeze :
                if (!boss.getLeftHand().isInUse() && !boss.getRightHand().isInUse())
                    return true;
            case projectile:
                if (!boss.getHead().isInUse() && !boss.getLeftHand().isInUse() && !boss.getRightHand().isInUse())
                    return true;
        }
        return false;
    }


    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }
}
