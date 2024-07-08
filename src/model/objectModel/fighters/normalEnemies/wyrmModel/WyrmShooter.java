package model.objectModel.fighters.normalEnemies.wyrmModel;

import controller.enums.ModelType;
import controller.manager.Spawner;
import data.Constants;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WyrmShooter {

    private WyrmModel wyrmModel;
    WyrmShooter(WyrmModel wyrmModel){
        this.wyrmModel = wyrmModel;
    }
    public void shoot() {
        Vector position = wyrmModel.getPosition();
        EpsilonModel epsilon = (EpsilonModel) ModelData.getModels().getFirst();

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
