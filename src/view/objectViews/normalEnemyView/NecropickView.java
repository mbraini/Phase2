package view.objectViews.normalEnemyView;

import constants.Constants;
import utils.Vector;

import java.awt.*;

public class NecropickView extends NormalEnemyView{

    public NecropickView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (!hovering) {
            g2d.rotate(
                    -theta,
                    position.getX() + Constants.SCREEN_SIZE.width,
                    position.getY() + Constants.SCREEN_SIZE.height
            );
            g2d.drawImage(
                    image,
                    (int) position.getX() - Constants.NECROPICK_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width,
                    (int) position.getY() - Constants.NECROPICK_DIMENSION.height / 2 + Constants.SCREEN_SIZE.height,
                    Constants.NECROPICK_DIMENSION.width,
                    Constants.NECROPICK_DIMENSION.height,
                    null
            );
            g2d.rotate(
                    theta,
                    position.getX() + Constants.SCREEN_SIZE.width,
                    position.getY() + Constants.SCREEN_SIZE.height
            );
        }
        else {
            return;
        }
    }

}
