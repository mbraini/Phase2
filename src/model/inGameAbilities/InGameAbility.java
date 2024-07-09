package model.inGameAbilities;

public abstract class InGameAbility {

    protected InGameAbilityType type;
    protected boolean isAvailable;
    public abstract void performAbility();

}
