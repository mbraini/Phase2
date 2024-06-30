package view.objectViews.projectiles;

import data.Constants;
import utils.Vector;

import java.awt.*;

public class NecropickBulletView extends BulletView{
    public NecropickBulletView(Vector position , String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval(
                (int) position.x + Constants.SCREEN_SIZE.width,
                (int) position.y + Constants.SCREEN_SIZE.height,
                (int) Constants.NECROPCIK_BULLET_RADIOS * 2,
                (int) Constants.NECROPCIK_BULLET_RADIOS * 2
        );
    }
}
