package model.objectModel.miniBossEnemies.blackOrbModel;

import data.Constants;
import model.interfaces.IsCircle;
import model.objectModel.EpsilonModel;
import model.objectModel.miniBossEnemies.MiniBossModel;
import model.objectModel.projectiles.BlackOrbLaserModel;
import utils.Vector;

import java.util.ArrayList;

public class OrbModel extends MiniBossModel implements IsCircle {

    private ArrayList<BlackOrbLaserModel> connectedLasers = new ArrayList<>();
    private ArrayList<Integer> connectedOrbIndexes = new ArrayList<>();

    public OrbModel(Vector position ,String id){
        this.position = position;
        this.id = id;
    }

    @Override
    protected void meleeAttack(EpsilonModel epsilon) {

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

    public void addLaser(BlackOrbLaserModel laserModel ,int index){
        connectedLasers.add(laserModel);
        connectedOrbIndexes.add(index);
    }

}
