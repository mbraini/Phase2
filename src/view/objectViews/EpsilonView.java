package view.objectViews;


import data.Constants;
import utils.Vector;

import java.awt.*;

public class EpsilonView extends ObjectView {
    public EpsilonView(Vector position , String id) {
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                Constants.epsilonImage ,
                (int) position.getX() - Constants.EPSILON_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width ,
                (int) position.getY() - Constants.EPSILON_DIMENSION.height / 2 + Constants.SCREEN_SIZE.height,
                Constants.EPSILON_DIMENSION.width ,
                Constants.EPSILON_DIMENSION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
    }
}
