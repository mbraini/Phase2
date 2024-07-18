package model.objectModel.fighters.normalEnemies.necropickModel;

import constants.Constants;
import utils.Math;
import utils.Vector;

public class NecropickNavigator {
    private Vector position;
    private Vector epsilonPosition;
    private Vector velocity;

    public NecropickNavigator(Vector position ,Vector epsilonPosition){
        this.position = position;
        this.epsilonPosition = epsilonPosition;
    }

    public void navigate() {
        ///////////// prevents from jiggling :)
        if (java.lang.Math.abs(Math.VectorSize(
                Math.VectorAdd(position ,Math.ScalarInVector(-1 ,epsilonPosition))
        ) - Constants.NECROPICK_SPAWN_RADIOS) <= Constants.NECROPCIK_NAVIGATION_VELOCITY * Constants.UPS){
            velocity = new Vector();
            return;
        }
        ////////////
        Vector direction = Math.VectorAdd(
                Math.ScalarInVector(-1 ,position),
                epsilonPosition
        );
        if (Math.VectorSize(direction) <= Constants.NECROPICK_SPAWN_RADIOS){
            direction = Math.ScalarInVector(
                    -1,
                    direction
            );
            velocity = Math.VectorAdd(
                    position,
                    Math.ScalarInVector(-1 ,direction)
            );
        }
        velocity = Math.VectorWithSize(direction ,Constants.NECROPCIK_NAVIGATION_VELOCITY);
    }

    public Vector getVelocity(){
        return velocity;
    }

}
