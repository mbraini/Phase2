package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import controller.Controller;
import controller.manager.Spawner;
import data.Constants;
import model.interfaces.IsCircle;
import model.objectModel.fighters.miniBossEnemies.MiniBossModel;
import model.objectModel.frameModel.FrameModel;
import utils.Vector;

public class OrbModel extends MiniBossModel implements IsCircle {

    private BlackOrbModel blackOrbModel;
    private FrameModel frameModel;

    public OrbModel(Vector position ,BlackOrbModel blackOrbModel ,String id){
        this.position = position;
        this.id = id;
        this.blackOrbModel = blackOrbModel;
        this.HP = 30;
        this.frameModel = blackOrbModel.getFrameModels().get(blackOrbModel.getOrbModels().size());
        vulnerableToEpsilonMelee = true;
        vulnerableToEpsilonBullet = true;
    }


    @Override
    public void die() {
        synchronized (blackOrbModel.getEffectModels()) {
            blackOrbModel.getBlackOrbThread().disconnectLasers(this);
            blackOrbModel.removeOrb(id);
            Controller.removeObject(this);
            Controller.removeFrame(frameModel);
            Spawner.addCollectives(position, 1, 30);
        }
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
