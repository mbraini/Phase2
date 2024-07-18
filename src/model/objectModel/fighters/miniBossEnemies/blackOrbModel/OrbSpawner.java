package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import controller.manager.Spawner;
import constants.Constants;
import utils.Math;
import utils.Vector;

public class OrbSpawner {

    private BlackOrbModel blackOrbModel;
    private int counter;
    public OrbSpawner(BlackOrbModel blackOrbModel ,int count){
        this.blackOrbModel = blackOrbModel;
        this.counter = count;
    }

    public void spawn() {
        OrbModel orbModel = new OrbModel(
                Math.VectorAdd(
                        blackOrbModel.getFrameModels().get(blackOrbModel.getOrbCount()).getPosition(),
                        new Vector(
                                Constants.BLACK_ORB_FRAME_DIMENSION.width / 2d,
                                Constants.BLACK_ORB_FRAME_DIMENSION.height / 2d
                        )
                ),
                blackOrbModel,
                blackOrbModel.getOrbCount(),
                blackOrbModel.getFrameModels().get(blackOrbModel.getOrbCount()).getId()
        );
        Spawner.spawnOrb(
                orbModel.getPosition(),
                blackOrbModel,
                blackOrbModel.getOrbCount(),
                orbModel.getId()
        );
        blackOrbModel.addOrb(orbModel);
        blackOrbModel.getBlackOrbThread().connectLasers(blackOrbModel.getOrbModels().size() - 1);
        blackOrbModel.setOrbCount(blackOrbModel.getOrbCount() + 1);
    }

}
