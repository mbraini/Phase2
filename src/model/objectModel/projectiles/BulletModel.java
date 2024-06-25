package model.objectModel.projectiles;

import model.interfaces.MoveAble;
import utils.Math;

public abstract class BulletModel extends ProjectileModel implements MoveAble {
    protected double hp = 1;

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,acceleration);
        position = Math.VectorAdd(position ,velocity);
    }
}
