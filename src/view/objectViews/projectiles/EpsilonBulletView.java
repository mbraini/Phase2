package view.objectViews.projectiles;


import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class EpsilonBulletView extends BulletView {

    public EpsilonBulletView(Vector position, String id){
        this.position = position;
        this.theta = theta;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
                (int) (position.x - SizeConstants.EPSILON_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.width,
                (int) (position.y - SizeConstants.EPSILON_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.EPSILON_BULLET_RADIOS * 2,
                (int) SizeConstants.EPSILON_BULLET_RADIOS * 2
        );
    }
}
