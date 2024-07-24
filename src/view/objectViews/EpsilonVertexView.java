package view.objectViews;

import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class EpsilonVertexView extends ObjectView{

    public EpsilonVertexView(Vector position ,String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
                (int) position.x - SizeConstants.EPSILON_VERTICES_RADIOS + SizeConstants.SCREEN_SIZE.width ,
                (int) position.y - SizeConstants.EPSILON_VERTICES_RADIOS + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.EPSILON_VERTICES_RADIOS * 2 ,
                SizeConstants.EPSILON_VERTICES_RADIOS * 2
        );
    }
}
