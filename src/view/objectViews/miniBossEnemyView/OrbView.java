package view.objectViews.miniBossEnemyView;

import data.Constants;
import utils.Vector;

import java.awt.*;

public class OrbView extends MiniBossEnemyView{

    public OrbView(Vector position ,String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                Constants.omenoct ,
                (int) position.getX() - Constants.ORB_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width ,
                (int) position.getY() - Constants.ORB_DIMENSION.height / 2 + Constants.SCREEN_SIZE.height,
                Constants.ORB_DIMENSION.width ,
                Constants.ORB_DIMENSION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
    }

}
