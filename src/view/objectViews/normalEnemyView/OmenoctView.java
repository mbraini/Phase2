package view.objectViews.normalEnemyView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class OmenoctView extends NormalEnemyView{

    public OmenoctView(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.omenoct;
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
                (int) position.getX() - SizeConstants.OMENOCT_DIMENTION.width / 2 + SizeConstants.SCREEN_SIZE.width ,
                (int) position.getY() - SizeConstants.OMENOCT_DIMENTION.height / 2 + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.OMENOCT_DIMENTION.width ,
                SizeConstants.OMENOCT_DIMENTION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
    }
}
