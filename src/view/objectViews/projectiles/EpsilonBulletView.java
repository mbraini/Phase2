package view.objectViews.projectiles;


import data.Constants;
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
                (int) position.x + Constants.SCREEN_SIZE.width,
                (int) position.y + Constants.SCREEN_SIZE.height,
                (int) Constants.EPSILON_BULLET_DIAMETER,
                (int) Constants.EPSILON_BULLET_DIAMETER
        );
    }
}
