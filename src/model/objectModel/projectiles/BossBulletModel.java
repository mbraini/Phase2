package model.objectModel.projectiles;

import constants.Constants;
import model.interfaces.IsCircle;
import utils.Math;
import utils.Vector;

public class BossBulletModel extends BulletModel implements IsCircle {

    public BossBulletModel(Vector position , Vector direction , String id){
        this.position = position;
        this.velocity = Math.VectorWithSize(direction , Constants.OMENOCT_BULLET_VELOCITY);
        this.acceleration = new Vector(0 ,0);
        damage = Constants.OMENOCT_BULLET_DAMAGE;

        this.id = id;
        this.HP = 1;
    }

    @Override
    public double getRadios() {
        return Constants.BOSS_BULLET_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

}
