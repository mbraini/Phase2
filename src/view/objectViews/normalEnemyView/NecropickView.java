package view.objectViews.normalEnemyView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class NecropickView extends NormalEnemyView{

    public NecropickView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.necropick;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (!hovering) {
            g2d.rotate(
                    -theta,
                    position.getX() + SizeConstants.SCREEN_SIZE.width,
                    position.getY() + SizeConstants.SCREEN_SIZE.height
            );
            g2d.drawImage(
                    image,
                    (int) position.getX() - SizeConstants.NECROPICK_DIMENSION.width / 2 + SizeConstants.SCREEN_SIZE.width,
                    (int) position.getY() - SizeConstants.NECROPICK_DIMENSION.height / 2 + SizeConstants.SCREEN_SIZE.height,
                    SizeConstants.NECROPICK_DIMENSION.width,
                    SizeConstants.NECROPICK_DIMENSION.height,
                    null
            );
            g2d.rotate(
                    theta,
                    position.getX() + SizeConstants.SCREEN_SIZE.width,
                    position.getY() + SizeConstants.SCREEN_SIZE.height
            );
        }
        else {
            return;
        }
    }

}
