package model.objectModel.fighters.basicEnemies;

import controller.Controller;
import controller.enums.ModelType;
import controller.manager.Spawner;
import constants.Constants;
import model.ModelData;
import model.interfaces.Ability;
import model.interfaces.HasVertices;
import model.interfaces.ImpactAble;
import model.interfaces.IsPolygon;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;

public class TrigorathModel extends BasicEnemyModel implements HasVertices, IsPolygon, Ability, ImpactAble {
    private ArrayList<Vector> vertices;
    private boolean isImpacted = false;
    public TrigorathModel(Vector position ,String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 15;
        this.meleeAttack = Constants.TRIGORATH_DAMAGE;
        this.hasMeleeAttack = true;
        this.vulnerableToEpsilonMelee = true;
        this.vulnerableToEpsilonBullet = true;
        type = ModelType.trigorath;
        omega = Constants.ENEMY_ROTATION_SPEED;
        initVertices();
    }

    void initVertices(){
        vertices = new ArrayList<>();
        vertices.add(new Vector(
                position.x ,
                position.y - (java.lang.Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 3d))
        );
        vertices.add(new Vector(
                position.x - Constants.TRIGORATH_DIMENTION.width / 2d ,
                position.y + (java.lang.Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 6d))
        );
        vertices.add(new Vector(
                position.x + Constants.TRIGORATH_DIMENTION.width / 2d ,
                position.y + (java.lang.Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 6d))
        );
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,new Vector(vertices.get(i).getX() + xMoved ,vertices.get(i).getY() + yMoved));
            vertices.set(i , Math.RotateByTheta(vertices.get(i) ,position ,theta));
        }
    }


    @Override
    public ArrayList<Vector> getVertices() {
        return vertices;
    }


    @Override
    public void ability() {
        Vector epsilonPosition = Controller.getController(Controller.getIP()).getModelData().getModels().getFirst().getPosition();
        velocity = new Vector(epsilonPosition.x - getPosition().x ,epsilonPosition.y - getPosition().y);
        double distance = Math.VectorSize(
                Math.VectorAdd(
                        Math.ScalarInVector(-1 ,position) ,
                        Controller.getController(Controller.getIP()).getModelData().getModels().getFirst().getPosition()
                )
        );
        /////////////todo
        if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 160){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.6);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.6;
        }
        if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 280){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.4);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.4;
        }
        else if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 240){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.2);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.2;
        }
        else if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 200){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 1.1);
            omega = Constants.ENEMY_ROTATION_SPEED * 1.1;
        }
        else if (distance >= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 160){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED);
            omega = Constants.ENEMY_ROTATION_SPEED;
        }
        else if (distance<=Constants.TRIGORATH_DIMENTION.width * 2 / 3d + Constants.EPSILON_DIMENSION.width / 2d + 40){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.3);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.3;
        }
        else if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 80){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.5);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.5;
        }
        else if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 120){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.8);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.8;
        }
        else if (distance <= Constants.TRIGORATH_DIMENTION.width + Constants.EPSILON_DIMENSION.width + 160){
            velocity = Math.VectorWithSize(velocity ,Constants.ENEMY_LINEAR_SPEED * 0.9);
            omega = Constants.ENEMY_ROTATION_SPEED * 0.9;
        }
        ////////////todo
    }

    @Override
    public boolean hasAbility() {
        return !isImpacted;
    }

    @Override
    public boolean isImpacted() {
        return isImpacted;
    }

    @Override
    public void setImpacted(boolean impact) {
        isImpacted = impact;
    }

    @Override
    public void die() {
        super.die();
        Spawner.addCollectives(position ,2 ,5);
    }
}
