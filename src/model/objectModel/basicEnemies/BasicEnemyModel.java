package model.objectModel.basicEnemies;

import data.Constants;
import model.ModelData;
import model.interfaces.HasVertices;
import model.interfaces.MoveAble;
import model.objectModel.EnemyModel;
import model.objectModel.EpsilonModel;
import utils.Math;
import utils.Vector;

public abstract class BasicEnemyModel extends EnemyModel implements MoveAble {
    @Override
    public void move() {

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
