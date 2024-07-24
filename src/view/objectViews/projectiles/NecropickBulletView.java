package view.objectViews.projectiles;

import constants.SizeConstants;
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
                (int) (position.x - SizeConstants.NECROPICIK_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.width,
                (int) (position.y - SizeConstants.NECROPICIK_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.NECROPICIK_BULLET_RADIOS * 2,
                (int) SizeConstants.NECROPICIK_BULLET_RADIOS * 2
        );
    }
}
