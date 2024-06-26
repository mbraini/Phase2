package model.objectModel.projectiles;

import data.Constants;
import model.interfaces.IsCircle;
import model.logics.Impact;
import utils.Math;
import utils.Vector;

public class OmenoctBulletModel extends BulletModel implements IsCircle {

    public OmenoctBulletModel(Vector position , Vector direction , String id){
        this.position = position;
        this.velocity = Math.VectorWithSize(direction , Constants.OMENOCT_BULLET_VELOCITY);
        this.acceleration = new Vector(0 ,0);

        setSolid(false);

        this.id = id;
        this.HP = 1;
    }

    @Override
    public double getRadios() {
        return Constants.OMENOCT_BULLET_RADIOUS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    @Override
    public void die() {
        new Impact(position).MakeImpact();
    }
}
