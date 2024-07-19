package model.objectModel.fighters.normalEnemies.wyrmModel;

import controller.Controller;
import controller.enums.ModelType;
import controller.manager.Spawner;
import constants.Constants;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

public class WyrmShooter {

    private WyrmModel wyrmModel;
    WyrmShooter(WyrmModel wyrmModel){
        this.wyrmModel = wyrmModel;
    }
    public void shoot() {
        Vector position = wyrmModel.getPosition();
        EpsilonModel epsilon = (EpsilonModel) Controller.getController(Controller.getIP()).getModelData().getModels().getFirst();

        Vector direction = Math.VectorAdd(
                Math.ScalarInVector(-1 ,position),
                epsilon.getPosition()
        );

        Vector bulletPosition = Math.VectorAdd(
                position,
                Math.VectorWithSize(
                        direction ,
                        Constants.OMENOCT_BULLET_RADIOUS + Constants.OMENOCT_RADIOS
                )
        );

        Spawner.addProjectile(bulletPosition ,direction , ModelType.wyrmBullet);
    }
}
