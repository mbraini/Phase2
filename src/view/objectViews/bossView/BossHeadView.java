package view.objectViews.bossView;

import data.Constants;
import utils.Vector;
import view.objectViews.EnemyView;

import java.awt.*;

public class BossHeadView extends EnemyView {

    public BossHeadView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.x - Constants.HEAD_DIMENSION.width + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.HEAD_DIMENSION.height  + Constants.SCREEN_SIZE.height,
                Constants.HEAD_DIMENSION.width ,Constants.HEAD_DIMENSION.height ,
                null
        );
    }
}
