package view.objectViews.projectiles;

import constants.SizeConstants;
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
                (int) (position.x - SizeConstants.SLAUGHTER_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.width,
                (int) (position.y - SizeConstants.SLAUGHTER_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.SLAUGHTER_BULLET_RADIOS * 2,
                (int) SizeConstants.SLAUGHTER_BULLET_RADIOS * 2
        );
    }

}
