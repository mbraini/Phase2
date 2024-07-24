package view.objectViews.projectiles;

import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class OmenoctBulletView extends BulletView{

    public OmenoctBulletView(Vector position , String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval(
                (int) (position.x - SizeConstants.OMENOCT_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.width,
                (int) (position.y - SizeConstants.OMENOCT_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.OMENOCT_BULLET_RADIOS * 2,
                (int) SizeConstants.OMENOCT_BULLET_RADIOS * 2
        );
    }
}
