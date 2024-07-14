package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import model.ModelData;
import model.skillTreeAbilities.Cerberus.Cerberus;

import java.util.ArrayList;

public class SkillTreeAbilityHandler {

    public synchronized static void initAbilities(){
        ArrayList<SkillTreeAbility> abilities = ModelData.getSkillTreeAbilities();
        abilities.add(new Ares());
        abilities.add(new Astrape());
        abilities.add(new Cerberus());
        abilities.add(new Aceso());
        ModelData.setSkillTreeAbilities(abilities);
    }

    public static SkillTreeAbility getAbility(SkillTreeAbilityType type) {
        ArrayList<SkillTreeAbility> abilities = ModelData.getSkillTreeAbilities();
        for (SkillTreeAbility ability : abilities){
            if (ability.getType().equals(type))
                return ability;
        }
        return null;
    }

    public static void activateSkillTreeAbility(SkillTreeAbilityType type) {
        SkillTreeAbility skillTreeAbility = getAbility(type);
        skillTreeAbility.cast();
    }
}
