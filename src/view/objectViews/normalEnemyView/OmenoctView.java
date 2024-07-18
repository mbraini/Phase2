package view.objectViews.normalEnemyView;

import constants.Constants;
import utils.Vector;

import java.awt.*;

public class OmenoctView extends NormalEnemyView{

    public OmenoctView(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                image ,
                (int) position.getX() - Constants.OMENOCT_DIMENTION.width / 2 + Constants.SCREEN_SIZE.width ,
                (int) position.getY() - Constants.OMENOCT_DIMENTION.height / 2 + Constants.SCREEN_SIZE.height,
                Constants.OMENOCT_DIMENTION.width ,
                Constants.OMENOCT_DIMENTION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
    }
}
