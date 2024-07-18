package model.objectModel.projectiles;

import controller.Controller;
import constants.Constants;
import model.interfaces.MoveAble;
import utils.Math;

public abstract class BulletModel extends ProjectileModel implements MoveAble {
    protected double hp = 1;

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);
    }

    @Override
    public void die() {
        Controller.removeObject(this);
    }

}
