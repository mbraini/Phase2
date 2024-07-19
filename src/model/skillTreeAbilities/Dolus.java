package model.skillTreeAbilities;

import controller.Controller;
import controller.enums.SkillTreeAbilityType;
import model.ModelData;

import java.util.ArrayList;
import java.util.Random;

public class Dolus extends SkillTreeAbility{

    private SkillTreeAbilityType firstAbility;
    private SkillTreeAbilityType secondAbility;

    public Dolus() {
        unlockXpCost = 1500;
        type = SkillTreeAbilityType.dolus;
        initTimer();
    }


    @Override
    protected void cast() {
        canCast = false;
        if (firstAbility == null) {
            defineAbilities();
        }
        SkillTreeAbility firstSkillTreeAbility = SkillTreeAbilityHandler.getAbility(firstAbility);
        SkillTreeAbility secondSkillTreeAbility = SkillTreeAbilityHandler.getAbility(secondAbility);
        if (!firstSkillTreeAbility.canCast) {
            firstSkillTreeAbility.stop();
        }
        SkillTreeAbilityHandler.activateSkillTreeAbility(firstAbility);
        if (!secondSkillTreeAbility.canCast) {
            secondSkillTreeAbility.stop();
        }
        SkillTreeAbilityHandler.activateSkillTreeAbility(secondAbility);

        System.out.println(firstAbility);
        System.out.println(secondAbility);

        coolDownTimer.start();
    }

    private void defineAbilities() {
        ArrayList<SkillTreeAbility> abilities = (ArrayList<SkillTreeAbility>) Controller.getController(Controller.getIP()).getModelData().getSkillTreeAbilities().clone();
        Random random = new Random();
        int firstRandom = random.nextInt(0 ,abilities.size());
        firstAbility = abilities.get(firstRandom).getType();
        abilities.remove(firstRandom);
        int secondRandom = random.nextInt(0 ,abilities.size());
        secondAbility = abilities.get(secondRandom).getType();
    }
}
