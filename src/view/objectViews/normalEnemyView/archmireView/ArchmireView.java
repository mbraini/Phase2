package view.objectViews.normalEnemyView.archmireView;

import data.Constants;
import utils.Vector;
import view.objectViews.normalEnemyView.NormalEnemyView;

import java.awt.*;

public class ArchmireView extends NormalEnemyView {

    public ArchmireView(Vector position , String id){
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
                (int) position.getX() - Constants.ARCHMIRE_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width ,
                (int) position.getY() - Constants.ARCHMIRE_DIMENSION.height / 2 + Constants.SCREEN_SIZE.height,
                Constants.ARCHMIRE_DIMENSION.width ,
                Constants.ARCHMIRE_DIMENSION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
    }
}
