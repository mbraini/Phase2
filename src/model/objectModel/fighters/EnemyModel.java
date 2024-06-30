package model.objectModel.fighters;


import model.collision.Collision;
import model.interfaces.IsCircle;
import model.interfaces.IsPolygon;
import model.objectModel.FighterModel;
import utils.Vector;

public abstract class EnemyModel extends FighterModel {

    protected boolean vulnerableToEpsilonMelee;
    protected boolean vulnerableToEpsilonBullet;

    public void meleeAttack(EpsilonModel epsilon){
        if (!hasMeleeAttack)
            return;
        if (this instanceof IsPolygon){
            for (Vector vertex : ((IsPolygon) this).getVertices()) {
                if (Collision.IsInCircle((IsCircle) epsilon, vertex)){
                    epsilon.setHP(epsilon.getHP() - meleeAttack);
                    return;
                }
            }
        }
    }

    public boolean isVulnerableToEpsilonMelee() {
        return vulnerableToEpsilonMelee;
    }

    public void setVulnerableToEpsilonMelee(boolean vulnerableToEpsilonMelee) {
        this.vulnerableToEpsilonMelee = vulnerableToEpsilonMelee;
    }

    public boolean isVulnerableToEpsilonBullet() {
        return vulnerableToEpsilonBullet;
    }

    public void setVulnerableToEpsilonBullet(boolean vulnerableToEpsilonBullet) {
        this.vulnerableToEpsilonBullet = vulnerableToEpsilonBullet;
    }
}
