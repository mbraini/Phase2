package model.objectModel.fighters.normalEnemies.necropickModel;

import controller.enums.ObjectType;
import controller.manager.Spawner;
import data.Constants;
import utils.Math;
import utils.Vector;

public class NecropickShooter {

    private Vector position;

    public NecropickShooter(Vector position) {
        this.position = position;
    }

    public void shoot() {
        double theta = 0;
        Vector direction;
        Vector spawnPosition;
        for (int i = 0; i < 8 ;i++){
            theta += java.lang.Math.PI / 4d;
            direction = new Vector(java.lang.Math.cos(theta) , java.lang.Math.sin(theta));
            spawnPosition = Math.VectorAdd(
                    Math.VectorWithSize(direction , Constants.NECROPICK_DIMENSION.width / 2d),
                    position
            );
            Spawner.addProjectile(
                    spawnPosition,
                    direction,
                    ObjectType.necropickBullet
            );
        }
    }
}
