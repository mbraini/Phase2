package view.objectViews.projectiles;

import data.Constants;
import utils.Vector;

import java.awt.*;

public class SlaughterBulletView extends EpsilonBulletView {
    public SlaughterBulletView(Vector position, String id) {
        super(position, id);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval(
                (int) (position.x - Constants.SLAUGHTER_BULLET_RADIOS) + Constants.SCREEN_SIZE.width,
                (int) (position.y - Constants.SLAUGHTER_BULLET_RADIOS) + Constants.SCREEN_SIZE.height,
                (int) Constants.SLAUGHTER_BULLET_RADIOS * 2,
                (int) Constants.SLAUGHTER_BULLET_RADIOS * 2
        );
    }

}
