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
        this.velocity = new Vector();
        this.acceleration = new Vector();
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
        builder.setIsometric(true);
        frame = builder.create();
    }

    @Override
    public void setStuckFramePosition() {
        frame.transfer(Math.VectorAdd(
                position,
                new Vector(
                        -Constants.HEAD_DIMENSION.width / 2d,
                        -Constants.HEAD_DIMENSION.height / 2d
                )
        ));
    }

}
