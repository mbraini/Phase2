package view.objectViews.bossView;

import data.Constants;
import utils.Vector;
import view.objectViews.EnemyView;

import java.awt.*;

public class BossHandView extends EnemyView {

    public BossHandView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.x - Constants.HAND_DIMENSION.width + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.HAND_DIMENSION.height  + Constants.SCREEN_SIZE.height,
                Constants.HAND_DIMENSION.width ,Constants.HAND_DIMENSION.height ,
                null
        );
    }
}
