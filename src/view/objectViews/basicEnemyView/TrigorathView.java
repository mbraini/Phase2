package view.objectViews.basicEnemyView;


import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class TrigorathView extends BasicEnemyView{
    public TrigorathView(Vector position, String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.trigorathImage;
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
                (int) position.x - SizeConstants.TRIGORATH_DIMENTION.width / 2 + SizeConstants.SCREEN_SIZE.width,
                (int) position.y - (SizeConstants.TRIGORATH_DIMENTION.height * 2) / 3 + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.TRIGORATH_DIMENTION.width , SizeConstants.TRIGORATH_DIMENTION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
    }
}
