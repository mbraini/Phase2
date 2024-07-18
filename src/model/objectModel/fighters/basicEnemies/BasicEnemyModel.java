package model.objectModel.fighters.basicEnemies;

import constants.Constants;
import controller.manager.GameState;
import model.interfaces.HasVertices;
import model.interfaces.MoveAble;
import model.objectModel.fighters.EnemyModel;
import utils.Math;

public abstract class BasicEnemyModel extends EnemyModel implements MoveAble {
    @Override
    public void move() {
        if (GameState.isDizzy())
            return;

        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);


        omega += alpha * Constants.UPS;
        double thetaMoved = ((2 * omega - alpha * Constants.UPS) / 2) * Constants.UPS;
        theta = theta + thetaMoved;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,thetaMoved);
    }


}
