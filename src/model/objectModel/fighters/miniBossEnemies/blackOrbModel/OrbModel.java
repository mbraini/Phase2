package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import data.Constants;
import model.interfaces.IsCircle;
import model.objectModel.fighters.miniBossEnemies.MiniBossModel;
import model.objectModel.projectiles.BlackOrbLaserModel;
import utils.Vector;

import java.util.ArrayList;

public class OrbModel extends MiniBossModel implements IsCircle {

    private ArrayList<BlackOrbLaserModel> connectedLasers = new ArrayList<>();

    public OrbModel(Vector position ,String id){
        this.position = position;
        this.id = id;
    }


    @Override
    public void die() {

    }


    @Override
    public double getRadios() {
        return Constants.ORB_DIMENSION.width / 2d;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public void addLaser(BlackOrbLaserModel laserModel){
        connectedLasers.add(laserModel);
    }

}
