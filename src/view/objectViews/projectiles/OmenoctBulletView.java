package view.objectViews.projectiles;

import data.Constants;
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
                (int) (position.x - Constants.OMENOCT_RADIOS) + Constants.SCREEN_SIZE.width,
                (int) (position.y - Constants.OMENOCT_RADIOS) + Constants.SCREEN_SIZE.height,
                (int) Constants.OMENOCT_BULLET_RADIOUS * 2,
                (int) Constants.OMENOCT_BULLET_RADIOUS * 2
        );
    }
}
