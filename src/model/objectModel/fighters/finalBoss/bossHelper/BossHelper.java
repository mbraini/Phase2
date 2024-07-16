package model.objectModel.fighters.finalBoss.bossHelper;

import controller.manager.loading.SkippedByJson;
import data.Constants;
import model.interfaces.FrameSticker;
import model.interfaces.HasVertices;
import model.interfaces.ImageChanger;
import model.interfaces.MoveAble;
import model.objectModel.fighters.EnemyModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import utils.Math;

import java.awt.*;

public abstract class BossHelper extends EnemyModel implements ImageChanger , MoveAble , FrameSticker {
    protected FrameModel frame;
    @SkippedByJson
    protected Image image;
    protected boolean isInUse;
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

}
