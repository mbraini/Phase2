package model.inGameAbilities;

import constants.Constants;
import controller.enums.InGameAbilityType;
import model.ModelData;
import model.logics.Impact;
import model.objectModel.fighters.EpsilonModel;
import utils.Vector;

public class Banish extends InGameAbility{

    private EpsilonModel epsilon;

    public Banish(EpsilonModel epsilon){
        type = InGameAbilityType.banish;
        xpCost = 100;
        this.epsilon = epsilon;
    }

    @Override
    public void performAbility() {
        new Impact(epsilon.getPosition() , Constants.BANISH_IMPACT_RANGE ,2000 ,true).MakeImpact();
        isActive = false;
    }

    @Override
    public void setUp() {
        epsilon = ModelData.getEpsilon();
    }
}
