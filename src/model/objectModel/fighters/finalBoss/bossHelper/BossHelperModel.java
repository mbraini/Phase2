package model.objectModel.fighters.finalBoss.bossHelper;

import controller.Controller;
import controller.interfaces.SizeChanger;
import controller.manager.loading.SkippedByJson;
import constants.Constants;
import model.interfaces.FrameSticker;
import model.interfaces.HasVertices;
import model.interfaces.ImageChanger;
import model.interfaces.MoveAble;
import model.objectModel.fighters.EnemyModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import utils.Math;
import utils.Vector;

import java.awt.*;

public abstract class BossHelperModel extends EnemyModel implements ImageChanger , MoveAble , FrameSticker , SizeChanger {
    protected FrameModel frame;
    @SkippedByJson
    protected Image image;
    protected Dimension size;
    protected boolean isInUse = true;
    protected boolean isDead;
    protected abstract void initFrame();

    @Override
    public void meleeAttack(EpsilonModel epsilon) {
        epsilon.setHP(epsilon.getHP() - meleeAttack);
    }

    public FrameModel getFrame() {
        return frame;
    }

    public void setFrame(FrameModel frame) {
        this.frame = frame;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);


        omega += alpha * Constants.UPS;
        double thetaMoved = ((2 * omega - alpha * Constants.UPS) / 2) * Constants.UPS;
        theta = theta + thetaMoved;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,thetaMoved);
    }

    @Override
    public void setStuckFramePosition() {
        frame.setSize(size);
        frame.transfer(Math.VectorAdd(
                position,
                new Vector(
                        -size.width / 2d,
                        -size.height / 2d
                )
        ));
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public Dimension getSize() {
        return size;
    }

    @Override
    public void die() {
        Controller.removeObject(this);
        Controller.removeFrame(frame);
        setDead(true);
    }

}
