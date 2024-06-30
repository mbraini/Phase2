package model.objectModel.fighters.normalEnemies.omenoctModel;

import controller.enums.ObjectType;
import controller.manager.Spawner;
import data.Constants;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Helper;
import utils.Math;
import utils.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OmenoctShooter implements ActionListener {

    private Vector position;

    public OmenoctShooter(Vector position) {
        this.position = position;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EpsilonModel epsilon = (EpsilonModel) ModelData.getModels().getFirst();
        Vector direction = Math.VectorAdd(
                Math.ScalarInVector(-1 ,position),
                epsilon.getPosition()
        );
        String id = Helper.RandomStringGenerator(Constants.ID_SIZE);
        Vector bulletPosition = Math.VectorAdd(
                position,
                Math.VectorWithSize(
                        direction ,
                        Constants.OMENOCT_BULLET_RADIOUS + Constants.OMENOCT_RADIOS
                )
        );
        Spawner.addProjectile(bulletPosition ,direction , ObjectType.omenoctBullet);
    }

}
