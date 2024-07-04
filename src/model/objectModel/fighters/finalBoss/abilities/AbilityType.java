package model.objectModel.fighters.finalBoss.abilities;

public enum AbilityType {

    squeeze(0),
    projectile(1),
    vomit(2),
    powerPunch(3),
    rapidFire(4),
    slap(5);

    final int number;
    AbilityType(int number){
        this.number = number;
    }


}
