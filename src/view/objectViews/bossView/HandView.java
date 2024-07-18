package view.objectViews.bossView;

import constants.Constants;
import utils.Vector;
import view.objectViews.EnemyView;

import java.awt.*;

public class HandView extends EnemyView {

    public HandView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.x - Constants.HAND_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.HAND_DIMENSION.height / 2  + Constants.SCREEN_SIZE.height,
                Constants.HAND_DIMENSION.width ,Constants.HAND_DIMENSION.height ,
                null
        );
    }
}
