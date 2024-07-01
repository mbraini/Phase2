package model.objectModel.fighters.finalBoss.bossHelper;

import model.objectModel.fighters.EnemyModel;
import model.objectModel.frameModel.FrameModel;

import java.awt.*;

public abstract class BossHelper extends EnemyModel {
    protected FrameModel frame;
    protected Image image;
    protected abstract void initFrame();

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
}
