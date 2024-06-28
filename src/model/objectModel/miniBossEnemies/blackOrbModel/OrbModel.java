package model.objectModel.miniBossEnemies.blackOrbModel;

import data.Constants;
import model.interfaces.IsCircle;
import model.objectModel.EpsilonModel;
import model.objectModel.miniBossEnemies.MiniBossModel;
import utils.Vector;

public class OrbModel extends MiniBossModel implements IsCircle {


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
}
