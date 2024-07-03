package model.objectModel.fighters.finalBoss.abilities;

import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.projectile.Projectile;
import model.objectModel.fighters.finalBoss.abilities.squeeze.Squeeze;

public class AbilityCaster {

    private AbilityType abilityType;
    private Boss boss;

    public AbilityCaster(Boss boss ,AbilityType abilityType){
        this.abilityType = abilityType;
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

}
