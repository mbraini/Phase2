package model.viewRequests;

import controller.enums.ModelType;
import controller.manager.Spawner;
import data.Constants;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

public class ShootRequest {

    private EpsilonModel epsilon;
    private static int extraAim;

    public ShootRequest(EpsilonModel epsilon){
        this.epsilon = epsilon;
    }

    public static boolean canShoot() {
        return true;
    }

    public void shoot(Vector clickedPoint) {
        Vector direction = Math.VectorAdd(Math.ScalarInVector(-1 ,epsilon.getPosition()) ,clickedPoint);
        Vector position = Math.VectorAdd(
                Math.VectorWithSize(
                        direction ,
                        Constants.EPSILON_BULLET_DIAMETER / 2 + Constants.EPSILON_DIMENSION.width / 2d + 1
                )
                ,epsilon.getPosition()
        );
        int constant = -1;
        Spawner.addProjectile(position, direction , ModelType.epsilonBullet);
        for (int i = 0; i < extraAim ;i++) {
            constant = constant * (-1);
            Vector direction2 = Math.RotateByTheta(
                    direction,
                    new Vector(0 ,0),
                    java.lang.Math.PI / 12 * constant
            );
            Vector spawnPosition = Math.VectorAdd(
                    Math.VectorWithSize(direction2 , Constants.EPSILON_BULLET_DIAMETER / 2d + 1),
                    position
            );
            Spawner.addProjectile(
                    spawnPosition,
                    direction2,
                    ModelType.epsilonBullet
            );
        }
    }
}
