package model.objectModel.projectiles;

import constants.Constants;
import model.interfaces.IsCircle;
import utils.Math;
import utils.Vector;

public class WyrmBulletModel extends BulletModel implements IsCircle {

    public WyrmBulletModel(Vector position , Vector direction , String id){
        this.position = position;
        this.velocity = Math.VectorWithSize(direction , Constants.WYRM_BULLET_VELOCITY);
        this.acceleration = new Vector(0 ,0);
        damage = Constants.WYRM_RANGE_DAMAGE;
        setSolid(false);
        this.id = id;
        this.HP = 1;
    }

    @Override
    public double getRadios() {
        return Constants.WYRM_BULLET_RADIOUS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }
}
