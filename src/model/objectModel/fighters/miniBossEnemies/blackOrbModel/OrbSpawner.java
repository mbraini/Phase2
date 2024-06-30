package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import controller.enums.ObjectType;
import controller.manager.Spawner;
import data.Constants;
import utils.Math;
import utils.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrbSpawner implements ActionListener {

    private BlackOrbModel blackOrbModel;
    private int counter;
    public OrbSpawner(BlackOrbModel blackOrbModel){
        this.blackOrbModel = blackOrbModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Spawner.addObjectWithId(
                Math.VectorAdd(
                        blackOrbModel.getFrameModels().get(counter).getPosition(),
                        new Vector(
                                Constants.BLACK_ORB_FRAME_DIMENSION.width / 2d,
                                Constants.BLACK_ORB_FRAME_DIMENSION.height / 2d
                        )
                ),
                ObjectType.orb,
                blackOrbModel.getFrameModels().get(counter).getId()
        );
        blackOrbModel.addOrb(new OrbModel(
                Math.VectorAdd(
                        blackOrbModel.getFrameModels().get(counter).getPosition(),
                        new Vector(
                                Constants.BLACK_ORB_FRAME_DIMENSION.width / 2d,
                                Constants.BLACK_ORB_FRAME_DIMENSION.height / 2d
                        )
                ),
                blackOrbModel.getFrameModels().get(counter).getId()
        ));
        counter++;
        blackOrbModel.getBlackOrbThread().connectLasers(counter);
        if (counter == 5){
            blackOrbModel.getBlackOrbThread().start();
            blackOrbModel.getOrbSpawner().stop();
        }
    }

}
