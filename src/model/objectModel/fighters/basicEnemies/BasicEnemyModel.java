package model.objectModel.fighters.basicEnemies;

import constants.RefreshRateConstants;
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

        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(RefreshRateConstants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * RefreshRateConstants.UPS) / 2) * RefreshRateConstants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * RefreshRateConstants.UPS) / 2) * RefreshRateConstants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);


        omega += alpha * RefreshRateConstants.UPS;
        double thetaMoved = ((2 * omega - alpha * RefreshRateConstants.UPS) / 2) * RefreshRateConstants.UPS;
        theta = theta + thetaMoved;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,thetaMoved);
    }


}
