package controller;

import controller.enums.InGameAbilityType;
import model.GameState;
import model.ModelData;
import model.inGameAbilities.InGameAbility;

public class ViewInGameAbilityRequests {


    public static void abilityRequest(InGameAbilityType type){

        if (canUse(type)) {
            ModelData.activateInGameAbility(type);
            GameState.setXp(GameState.getXp() - ModelData.getInGameAbility(type).getXpCost());
        }

    }

    private static boolean canUse(InGameAbilityType type) {
        InGameAbility inGameAbility = ModelData.getInGameAbility(type);
        if (GameState.getXp() >= inGameAbility.getXpCost()  && inGameAbility.isAvailable() && !inGameAbility.isActive())
            return true;
        return false;
    }


}
