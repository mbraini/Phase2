package model.objectModel.projectiles;


import controller.Controller;
import data.Constants;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;
import model.logics.Impact;
import utils.Math;
import utils.Vector;

public class EpsilonBulletModel extends BulletModel implements IsCircle {

    public EpsilonBulletModel(Vector position , Vector direction , String id){
        this.position = position;
        this.velocity = Math.VectorWithSize(direction , Constants.EPSILON_BULLET_VELOCITY);
        this.acceleration = new Vector(0 ,0);
        damage = Constants.EPSILON_DAMAGE;
        setSolid(true);
        this.id = id;
        this.HP = 1;
    }

    @Override
    public double getRadios() {
        return Constants.EPSILON_BULLET_DIAMETER / 2;
    }

    @Override
    public Vector getCenter() {
        return position;
    }


}