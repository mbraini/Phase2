package model.objectModel.fighters.finalBoss.bossHelper;

import data.Constants;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class HeadModel extends BossHelper{

    public HeadModel(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = Constants.smiley;
        initFrame();
    }

    @Override
    public void die() {

    }

    @Override
    protected void initFrame() {
        FrameModelBuilder builder = new FrameModelBuilder(
                Math.VectorAdd(
                        position,
                        new Vector(
                                -Constants.HEAD_DIMENSION.width / 2d,
                                -Constants.HEAD_DIMENSION.height / 2d
                        )
                ),
                new Dimension(Constants.HEAD_DIMENSION),
                id
        );
        frame = builder.create();
    }
}
