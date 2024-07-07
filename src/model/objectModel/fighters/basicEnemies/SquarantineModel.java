package model.objectModel.fighters.basicEnemies;


import controller.Controller;
import controller.enums.ObjectType;
import controller.manager.Spawner;
import data.Constants;
import model.ModelData;
import model.animations.DashAnimation;
import model.interfaces.Ability;
import model.interfaces.HasVertices;
import model.interfaces.ImpactAble;
import model.interfaces.IsPolygon;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;

public class SquarantineModel extends BasicEnemyModel implements HasVertices, IsPolygon, Ability , ImpactAble {
    ArrayList<Vector> vertices;
    boolean isImpacted = false;
    public SquarantineModel(Vector position , String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 10;
        this.meleeAttack = Constants.SQURANTINE_DAMAGE;
        this.hasMeleeAttack = true;
        this.vulnerableToEpsilonMelee = true;
        this.vulnerableToEpsilonBullet = true;
        type = ObjectType.squarantine;
        omega = Constants.ENEMY_ROTATION_SPEED;
        initVertices();
    }

    void initVertices(){
        vertices = new ArrayList<>();
        vertices.add(new Vector(
                position.x + (Constants.Squarantine_DIMENTION.width / 2d) ,
                position.y + (Constants.Squarantine_DIMENTION.height / 2d))
        );
        vertices.add(new Vector(
                position.x + (Constants.Squarantine_DIMENTION.width / 2d) ,
                position.y - (Constants.Squarantine_DIMENTION.height / 2d))
        );
        vertices.add(new Vector(
                position.x - (Constants.Squarantine_DIMENTION.width / 2d) ,
                position.y - (Constants.Squarantine_DIMENTION.height / 2d))
        );
        vertices.add(new Vector(
                position.x - (Constants.Squarantine_DIMENTION.width / 2d) ,
                position.y + (Constants.Squarantine_DIMENTION.height / 2d))
        );
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,new Vector(
                    vertices.get(i).getX() + xMoved ,
                    vertices.get(i).getY() + yMoved)
            );
            vertices.set(i , Math.RotateByTheta(vertices.get(i) ,position ,theta));
        }
    }

    @Override
    public ArrayList<Vector> getVertices() {
        return vertices;
    }

    @Override
    public void ability() {
        new DashAnimation(
                this ,
                Math.VectorAdd(
                        Math.ScalarInVector(-1 ,position) ,
                        ModelData.getModels().getFirst().getPosition()
                ) ,
                700 ,
                90 ,
                java.lang.Math.PI,
                false
        ).StartAnimation();
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
        Controller.removeObject(this);
        Spawner.addCollectives(position ,1 ,5);
    }
}
