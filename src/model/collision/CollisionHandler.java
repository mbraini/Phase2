package model.collision;


import controller.configs.Configs;
import data.Constants;
import model.GameState;
import model.interfaces.HasVertices;
import model.interfaces.IsPolygon;
import model.logics.Impact;
import model.objectModel.CollectiveModel;
import model.objectModel.fighters.EnemyModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.ObjectModel;
import model.objectModel.fighters.basicEnemies.SquarantineModel;
import model.objectModel.fighters.basicEnemies.TrigorathModel;
import model.objectModel.projectiles.BulletModel;
import utils.Math;
import utils.Vector;

public class CollisionHandler {
    ObjectModel model1;
    ObjectModel model2;
    Vector collisionPoint;
    public CollisionHandler(ObjectModel model1 ,ObjectModel model2){
        this.model1 = model1;
        this.model2 = model2;
        this.collisionPoint = Collision.FindCollisionPoint(model1 ,model2);
    }

    public void handle() {
        if (model1 instanceof EpsilonModel || model2 instanceof EpsilonModel){
            if (model1 instanceof EpsilonModel){
                epsilonHandler((EpsilonModel) model1 ,model2);
            }
            else {
                epsilonHandler((EpsilonModel) model2 ,model1);
            }
        }
    }

    private void epsilonHandler(EpsilonModel epsilon ,ObjectModel object) {
        if (object.isHovering()){
            epsilon.meleeAttack((EnemyModel) object);
            return;
        }
        if (object instanceof EnemyModel){
            ((EnemyModel) object).meleeAttack(epsilon);
            epsilon.meleeAttack((EnemyModel) object);
            pullOut(epsilon ,object);
            new Impact(collisionPoint).MakeImpact();
        }
    }


//    public void CollisionResponse(ObjectModel a, ObjectModel b) {
//        if (collisionPoint == null)
//            return;
//
//        /////epsilon enemy
//        if (a instanceof EpsilonModel && b instanceof EnemyModel){
//            collisionHandler.EpsilonEnemy((EpsilonModel)a ,(EnemyModel) b);
//        }
//        else if (b instanceof EpsilonModel && a instanceof EnemyModel){
//            collisionHandler.EpsilonEnemy((EpsilonModel)b ,(EnemyModel) a);
//        }
//
//        /////enemy enemy
//        else if (a instanceof EnemyModel && b instanceof EnemyModel){
//            collisionHandler.EnemyEnemy((EnemyModel) a ,(EnemyModel) b);
//        }
//
//        /////enemy bullet
//        else if (a instanceof EnemyModel && b instanceof BulletModel){
//            collisionHandler.EnemyBullet((EnemyModel)a ,(BulletModel)b);
//        }
//        else if (b instanceof EnemyModel && a instanceof BulletModel){
//            collisionHandler.EnemyBullet((EnemyModel)b ,(BulletModel)a);
//        }
//        /////Collectives
//        else if (a instanceof EpsilonModel && b instanceof CollectiveModel){
//            collisionHandler.EpsilonCollective((EpsilonModel) a ,(CollectiveModel) b);
//        }
//        else if (b instanceof EpsilonModel && a instanceof CollectiveModel){
//            collisionHandler.EpsilonCollective((EpsilonModel) b ,(CollectiveModel) a);
//        }
//    }



    public void EpsilonEnemy(EpsilonModel epsilon , EnemyModel enemy){
        for (int i = 0 ;i < ((IsPolygon)enemy).getVertices().size() ;i++){
            if (Collision.IsInCircle(epsilon ,((IsPolygon)enemy).getVertices().get(i))){
                if (enemy instanceof TrigorathModel)
                    GameState.setHp(GameState.getHp() - Constants.TRIGORATH_DAMAGE);
                else if (enemy instanceof SquarantineModel)
                    GameState.setHp(GameState.getHp() - Constants.SQURANTINE_DAMAGE);
                break;
            }
        }
        for (int i = 0; i < EpsilonModel.getVertices().size() ; i++){
            if (Collision.IsColliding(EpsilonModel.getVertices().get(i) ,enemy)){
                enemy.setHP(enemy.getHP() - Constants.MELEI_ATTACK - Configs.EXTRA_DAMAGE);
                break;
            }
        }
        pullOut(epsilon ,enemy);
        new Impact(collisionPoint).MakeImpact();
    }

    public void EnemyEnemy(EnemyModel a, EnemyModel b) {
        ObjectModel attacker = a, defender = b;
        for (int i = 0; i < ((IsPolygon) b).getVertices().size(); i++) {
            if (collisionPoint.Equals(((IsPolygon) b).getVertices().get(i))) {
                attacker = b;
                defender = a;
            }
        }
        pullOut(attacker, defender);
        new Impact(collisionPoint).MakeImpact();
    }

    public void EnemyBullet(EnemyModel enemy, BulletModel bullet) {
        bullet.setHP(-1);
        enemy.setHP(enemy.getHP() - Constants.EPSILON_DAMAGE - Configs.EXTRA_DAMAGE);
    }


    private void pullOut(ObjectModel attacker, ObjectModel defender) {
        Vector attackerP = Math.VectorAdd(Math.ScalarInVector(-1, collisionPoint), attacker.getPosition());
        attackerP = Math.VectorWithSize(attackerP, 1);
        while (Collision.IsColliding(attacker, defender)) {
            attacker.setPosition(Math.VectorAdd(attackerP, attacker.getPosition()));
//            collisionPoint = Math.VectorAdd(collisionPoint ,attackerP);
            if (attacker instanceof HasVertices){
                ((HasVertices) attacker).UpdateVertices(attackerP.x ,attackerP.y ,0);
            }
        }

    }

    public void EpsilonCollective(EpsilonModel epsilon, CollectiveModel collective) {
        collective.setHP(-1);
    }

}
