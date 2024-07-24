package model.objectModel.fighters.finalBoss.bossHelper;

import constants.ImageConstants;
import constants.SizeConstants;
import controller.enums.ModelType;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class PunchModel extends BossHelperModel {

    public PunchModel(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.punch;
        this.velocity = new Vector();
        size = new Dimension(
                SizeConstants.PUNCH_DIMENSION.width,
                SizeConstants.PUNCH_DIMENSION.height
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
                                -SizeConstants.PUNCH_DIMENSION.width / 2d,
                                -SizeConstants.PUNCH_DIMENSION.height / 2d
                        )
                ),
                new Dimension(SizeConstants.PUNCH_DIMENSION),
                id
        );
        builder.setIsometric(true);
        frame = builder.create();
    }


}
