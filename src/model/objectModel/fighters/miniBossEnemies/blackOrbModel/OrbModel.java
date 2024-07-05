package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import controller.Controller;
import controller.manager.Spawner;
import data.Constants;
import model.interfaces.IsCircle;
import model.objectModel.fighters.miniBossEnemies.MiniBossModel;
import utils.Vector;

import java.util.ArrayList;

public class OrbModel extends MiniBossModel implements IsCircle {


    public OrbModel(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.HP = 30;
        vulnerableToEpsilonMelee = true;
        vulnerableToEpsilonBullet = true;
    }


    @Override
    public void die() {
        Controller.removeObject(this);
        Spawner.addCollectives(position ,1 ,30);
    }


    @Override
    public double getRadios() {
        return Constants.ORB_DIMENSION.width / 2d;
    }

    @Override
    public Vector getCenter() {
        return position;
    }


}
