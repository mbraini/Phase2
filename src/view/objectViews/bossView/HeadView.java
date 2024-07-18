package view.objectViews.bossView;

import constants.Constants;
import utils.Vector;
import view.objectViews.EnemyView;

import java.awt.*;

public class HeadView extends EnemyView {

    public HeadView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.x - Constants.HEAD_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.HEAD_DIMENSION.height / 2  + Constants.SCREEN_SIZE.height,
                Constants.HEAD_DIMENSION.width ,Constants.HEAD_DIMENSION.height ,
                null
        );
    }
}
