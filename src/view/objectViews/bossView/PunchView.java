package view.objectViews.bossView;

import constants.Constants;
import utils.Vector;
import view.objectViews.EnemyView;

import java.awt.*;

public class PunchView extends EnemyView {

    public PunchView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.punch;
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.x - Constants.PUNCH_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.PUNCH_DIMENSION.height / 2  + Constants.SCREEN_SIZE.height,
                Constants.PUNCH_DIMENSION.width ,Constants.PUNCH_DIMENSION.height ,
                null
        );
    }
}
