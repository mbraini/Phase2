package view.objectViews;

import data.Constants;
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
                (int) position.x - Constants.EPSILON_VERTICES_RADIOS + Constants.SCREEN_SIZE.width ,
                (int) position.y - Constants.EPSILON_VERTICES_RADIOS + Constants.SCREEN_SIZE.height,
                Constants.EPSILON_VERTICES_RADIOS * 2 ,
                Constants.EPSILON_VERTICES_RADIOS * 2
        );
    }
}
