package view.objectViews.projectiles;


import constants.Constants;
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
                (int) (position.x - Constants.EPSILON_BULLET_RADIOS) + Constants.SCREEN_SIZE.width,
                (int) (position.y - Constants.EPSILON_BULLET_RADIOS) + Constants.SCREEN_SIZE.height,
                (int) Constants.EPSILON_BULLET_RADIOS * 2,
                (int) Constants.EPSILON_BULLET_RADIOS * 2
        );
    }
}
