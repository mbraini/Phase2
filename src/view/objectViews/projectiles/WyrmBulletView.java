package view.objectViews.projectiles;

import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class WyrmBulletView extends BulletView{

    public WyrmBulletView(Vector position ,String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.PINK);
        g2d.fillOval(
                (int) (position.x - SizeConstants.WYRM_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.width,
                (int) (position.y - SizeConstants.WYRM_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.WYRM_BULLET_RADIOS * 2,
                (int) SizeConstants.WYRM_BULLET_RADIOS * 2
        );
    }
}
