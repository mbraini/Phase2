package model.objectModel.fighters.finalBoss.bossHelper;

import constants.Constants;
import controller.manager.loading.SkippedByJson;
import model.animations.BossDeathAnimation;
import model.interfaces.CollisionDetector;
import model.interfaces.IsCircle;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class HeadModel extends BossHelperModel implements IsCircle , CollisionDetector {
    private boolean isInPositiveDirection;
    @SkippedByJson
    private Boss boss;

    public HeadModel(Vector position , Boss boss, String id){
        this.position = position;
        this.id = id;
        this.image = Constants.smiley;
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.boss = boss;
        size = new Dimension(
                Constants.HEAD_DIMENSION.width,
                Constants.HEAD_DIMENSION.height
        );
        HP = 300;
        setHovering(true);
        initFrame();
    }

    public boolean isInPositiveDirection() {
        return isInPositiveDirection;
    }

    public void setInPositiveDirection(boolean inPositiveDirection) {
        isInPositiveDirection = inPositiveDirection;
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
    public void die() {
        new BossDeathAnimation(boss).StartAnimation();
    }

    @Override
    public double getRadios() {
        return Constants.HEAD_DIMENSION.width / 2d;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    @Override
    public void detect() {
        isInPositiveDirection = !isInPositiveDirection;
    }
}
