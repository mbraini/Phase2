package model.objectModel.projectiles;

import data.Constants;
import utils.Math;
import utils.Vector;

public class WyrmBulletModel extends BulletModel{

    public WyrmBulletModel(Vector position , Vector direction , String id){
        this.position = position;
        this.velocity = Math.VectorWithSize(direction , Constants.WYRM_BULLET_VELOCITY);
        this.acceleration = new Vector(0 ,0);
        setSolid(false);
        this.id = id;
        this.HP = 1;
    }

    @Override
    public void die() {

    }
}
