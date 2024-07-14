package model.viewRequests;

import controller.enums.SkillTreeAbilityType;
import model.GameState;
import model.skillTreeAbilities.SkillTreeAbility;
import model.skillTreeAbilities.SkillTreeAbilityHandler;

public class SkillTreeAbilityRequests {

    public static void abilityRequest(SkillTreeAbilityType type) {
        if (canUse(type)) {
            GameState.setXp(GameState.getXp() - SkillTreeAbilityHandler.getAbility(type).getInGameXpCost());
            SkillTreeAbilityHandler.activateSkillTreeAbility(type);
        }
    }

    private static boolean canUse(SkillTreeAbilityType type) {
        SkillTreeAbility ability = SkillTreeAbilityHandler.getAbility(type);
        if (ability.isBought() && ability.CanCast() && GameState.getXp() >= ability.getInGameXpCost()){
            return true;
        }
        return false;
    }

}
