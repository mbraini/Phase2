package model.objectModel.fighters.finalBoss.bossHelper;

import data.Constants;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class PunchModel extends BossHelper{

    public PunchModel(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = Constants.punch;
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
                                -Constants.PUNCH_DIMENSION.width / 2d,
                                -Constants.PUNCH_DIMENSION.height / 2d
                        )
                ),
                new Dimension(Constants.PUNCH_DIMENSION),
                id
        );
        frame = builder.create();
    }

}
