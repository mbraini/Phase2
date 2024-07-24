package view.objectViews.normalEnemyView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class WyrmView extends NormalEnemyView {
    public WyrmView(Vector position, String id) {
        this.position = position;
        this.id = id;
        this.image = ImageConstants.wyrm;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta,
                position.getX() + SizeConstants.SCREEN_SIZE.width,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                image,
                (int) position.getX() - SizeConstants.WYRM_DIMENSION.width / 2 + SizeConstants.SCREEN_SIZE.width,
                (int) position.getY() - SizeConstants.WYRM_DIMENSION.height / 2 + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.WYRM_DIMENSION.width,
                SizeConstants.WYRM_DIMENSION.height,
                null
        );
        g2d.rotate(
                theta,
                position.getX() + SizeConstants.SCREEN_SIZE.width,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
    }
}