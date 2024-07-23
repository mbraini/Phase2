package view.objectViews.normalEnemyView;

import constants.Constants;
import utils.Vector;

import java.awt.*;

public class WyrmView extends NormalEnemyView {
    public WyrmView(Vector position, String id) {
        this.position = position;
        this.id = id;
        this.image = Constants.wyrm;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta,
                position.getX() + Constants.SCREEN_SIZE.width,
                position.getY() + Constants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                image,
                (int) position.getX() - Constants.WYRM_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width,
                (int) position.getY() - Constants.WYRM_DIMENSION.height / 2 + Constants.SCREEN_SIZE.height,
                Constants.WYRM_DIMENSION.width,
                Constants.WYRM_DIMENSION.height,
                null
        );
        g2d.rotate(
                theta,
                position.getX() + Constants.SCREEN_SIZE.width,
                position.getY() + Constants.SCREEN_SIZE.height
        );
    }
}