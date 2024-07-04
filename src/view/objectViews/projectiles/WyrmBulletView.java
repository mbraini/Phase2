package view.objectViews.projectiles;

import data.Constants;
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
                (int) (position.x - Constants.WYRM_BULLET_RADIOUS) + Constants.SCREEN_SIZE.width,
                (int) (position.y - Constants.WYRM_BULLET_RADIOUS) + Constants.SCREEN_SIZE.height,
                (int) Constants.WYRM_BULLET_RADIOUS * 2,
                (int) Constants.WYRM_BULLET_RADIOUS * 2
        );
    }
}
