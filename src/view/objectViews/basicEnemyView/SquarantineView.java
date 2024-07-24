package view.objectViews.basicEnemyView;


import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class SquarantineView extends BasicEnemyView{
    public SquarantineView(Vector position, String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.squarantineImage;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                ImageConstants.squarantineImage ,
                (int) position.x - SizeConstants.Squarantine_DIMENTION.width / 2 + SizeConstants.SCREEN_SIZE.width,
                (int) position.y - SizeConstants.Squarantine_DIMENTION.height / 2 + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.Squarantine_DIMENTION.width , SizeConstants.Squarantine_DIMENTION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
    }
}
