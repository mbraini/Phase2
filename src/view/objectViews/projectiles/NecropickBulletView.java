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
                (int) (position.x - Constants.NECROPICIK_BULLET_RADIOS) + Constants.SCREEN_SIZE.width,
                (int) (position.y - Constants.NECROPICIK_BULLET_RADIOS) + Constants.SCREEN_SIZE.height,
                (int) Constants.NECROPICIK_BULLET_RADIOS * 2,
                (int) Constants.NECROPICIK_BULLET_RADIOS * 2
        );
    }
}
