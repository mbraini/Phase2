package model.objectModel;

import model.interfaces.IsPolygon;

public abstract class FighterModel extends ObjectModel{
    protected boolean hasMeleeAttack;

    public void meleeAttack(EnemyModel epsilon){
        if (this instanceof IsPolygon){
            ///////////meleeAttack
        }
    }

    public boolean isHasMeleeAttack() {
        return hasMeleeAttack;
    }

    public void setHasMeleeAttack(boolean hasMeleeAttack) {
        this.hasMeleeAttack = hasMeleeAttack;
    }
}
