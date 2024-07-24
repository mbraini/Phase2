package view.objectViews.miniBossEnemyView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class OrbView extends MiniBossEnemyView{

    public OrbView(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.orb;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                image ,
                (int) position.getX() - SizeConstants.ORB_DIMENSION.width / 2 + SizeConstants.SCREEN_SIZE.width ,
                (int) position.getY() - SizeConstants.ORB_DIMENSION.height / 2 + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.ORB_DIMENSION.width ,
                SizeConstants.ORB_DIMENSION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
    }

}
