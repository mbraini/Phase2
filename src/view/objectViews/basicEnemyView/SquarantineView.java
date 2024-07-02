package view.objectViews.basicEnemyView;


import data.Constants;
import utils.Vector;

import java.awt.*;

public class SquarantineView extends BasicEnemyView{
    public SquarantineView(Vector position, String id){
        this.position = position;
        this.id = id;
        this.image = Constants.squarantineImage;
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                Constants.squarantineImage ,
                (int) position.x - Constants.Squarantine_DIMENTION.width / 2 + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.Squarantine_DIMENTION.height / 2 + Constants.SCREEN_SIZE.height,
                Constants.Squarantine_DIMENTION.width ,Constants.Squarantine_DIMENTION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
    }
}
