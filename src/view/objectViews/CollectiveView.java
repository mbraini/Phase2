package view.objectViews;


import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class CollectiveView extends ObjectView{

    public CollectiveView(Vector position , String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.MAGENTA);
        g2d.fillOval(
                (int) (position.x - SizeConstants.COLLECTIVE_RADIOS + SizeConstants.SCREEN_SIZE.width) ,
                (int) (position.y - SizeConstants.COLLECTIVE_RADIOS + SizeConstants.SCREEN_SIZE.height) ,
                (int) SizeConstants.COLLECTIVE_RADIOS * 2 ,
                (int) SizeConstants.COLLECTIVE_RADIOS * 2
        );
    }
}
