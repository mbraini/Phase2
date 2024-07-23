package model.objectModel.fighters.finalBoss.bossHelper;

import constants.Constants;
import controller.enums.ModelType;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class PunchModel extends BossHelperModel {

    public PunchModel(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = Constants.punch;
        this.velocity = new Vector();
        size = new Dimension(
                Constants.PUNCH_DIMENSION.width,
                Constants.PUNCH_DIMENSION.height
        );
        type = ModelType.punch;
        HP = 100000;
        this.acceleration = new Vector();
        initFrame();
    }


    @Override
    protected void initFrame() {
        FrameModelBuilder builder = new FrameModelBuilder(
                Math.VectorAdd(
                        position,
                        new Vector(
                                -Constants.PUNCH_DIMENSION.width / 2d,
                                -Constants.PUNCH_DIMENSION.height / 2d
                        )
                ),
                new Dimension(Constants.PUNCH_DIMENSION),
                id
        );
        builder.setIsometric(true);
        frame = builder.create();
    }


}
