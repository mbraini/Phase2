package model.objectModel.miniBossEnemies.blackOrbModel;

import controller.manager.Spawner;
import data.Constants;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Helper;
import utils.Math;
import utils.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameSpawner implements ActionListener {
    private ArrayList<FrameModel> frameModels;
    private BlackOrbModel blackOrbModel;
    private int counter;
    public FrameSpawner(BlackOrbModel blackOrbModel){
        this.blackOrbModel = blackOrbModel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        double theta = counter * java.lang.Math.PI * 2 / 5;
        Vector initialPosition = Math.VectorAdd(
                blackOrbModel.getCenter(),
                Math.VectorWithSize(
                        new Vector(0 ,1),
                        Constants.BLACK_ORB_DIAGONAL_SIZE
                )
        );

        Vector frameCenter = Math.RotateByTheta(
                initialPosition,
                blackOrbModel.getCenter(),
                theta
        );

        Vector framePosition = Math.VectorAdd(
                frameCenter,
                new Vector(
                        -Constants.BLACK_ORB_FRAME_DIMENSION.width / 2d,
                        -Constants.BLACK_ORB_FRAME_DIMENSION.height / 2d
                )
        );

        FrameModelBuilder builder = new FrameModelBuilder(
                framePosition,
                Constants.BLACK_ORB_FRAME_DIMENSION,
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );

        builder.setIsometric(true);
        builder.setSolid(false);
        FrameModel frameModel = builder.create();
        blackOrbModel.addFrame(frameModel);
        Spawner.addFrame(frameModel);

        counter++;
        if (counter == 5){
            blackOrbModel.setAllFramesSpawned(true);
            blackOrbModel.getOrbSpawner().start();
            blackOrbModel.getFrameSpawner().stop();
        }
    }

}
