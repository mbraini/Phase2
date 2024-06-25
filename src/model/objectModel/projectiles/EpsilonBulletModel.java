package model.objectModel.projectiles;


import data.Constants;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;
import utils.Math;
import utils.Vector;

public class EpsilonBulletModel extends BulletModel implements IsCircle, MoveAble {

    public EpsilonBulletModel(Vector position , Vector direction , String id){
        this.position = position;
        this.velocity = Math.VectorWithSize(direction , Constants.BULLET_VELOCITY);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 1;
    }

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,acceleration);
        position = Math.VectorAdd(position ,velocity);
    }

    @Override
    public double getRadios() {
        return Constants.BULLET_DIAMETER / 2;
    }

    @Override
    public Vector getCenter() {
        return position;
    }
}