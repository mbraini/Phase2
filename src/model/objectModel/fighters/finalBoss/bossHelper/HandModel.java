package model.objectModel.fighters.finalBoss.bossHelper;

import data.Constants;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class HandModel extends BossHelper{

    public HandModel(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = Constants.hand;
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
                                -Constants.HAND_DIMENSION.width / 2d,
                                -Constants.HAND_DIMENSION.height / 2d
                        )
                ),
                new Dimension(Constants.HAND_DIMENSION),
                id
        );
        frame = builder.create();
    }
}
