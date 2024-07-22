package view.objectViews.bossView;

import constants.Constants;
import controller.interfaces.SizeChanger;
import view.objectViews.EnemyView;

import java.awt.*;

public abstract class BossHelperView extends EnemyView implements SizeChanger {
    protected Dimension size;

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.x - size.width / 2 + Constants.SCREEN_SIZE.width,
                (int) position.y - size.height / 2  + Constants.SCREEN_SIZE.height,
                size.width ,size.height ,
                null
        );
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public Dimension getSize() {
        return size;
    }
}
