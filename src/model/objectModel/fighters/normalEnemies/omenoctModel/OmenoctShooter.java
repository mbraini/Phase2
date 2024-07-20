package model.objectModel.fighters.normalEnemies.omenoctModel;

import controller.enums.ModelType;
import controller.manager.GameState;
import controller.manager.Spawner;
import constants.Constants;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Helper;
import utils.Math;
import utils.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OmenoctShooter implements ActionListener {

    private Vector position;
    private OmenoctModel omenoctModel;

    public OmenoctShooter(Vector position ,OmenoctModel omenoctModel) {
        this.position = position;
        this.omenoctModel = omenoctModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (GameState.isDizzy() || GameState.isPause())
            return;
        if (GameState.isOver()) {
            omenoctModel.getShooter().stop();
            return;
        }
        EpsilonModel epsilon = ModelData.getEpsilon();
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
        Spawner.addProjectile(bulletPosition ,direction , ModelType.omenoctBullet);
    }

    public void setPosition(Vector position) {
        this.position = position;
    }
}
