package view.objectViews;

import data.Constants;
import utils.Vector;

import java.awt.*;

public class CerberusView extends ObjectView {

    public CerberusView(Vector position, String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
                (int) (position.x - Constants.CERBERUS_RADIOS) + Constants.SCREEN_SIZE.width,
                (int) (position.y - Constants.CERBERUS_RADIOS) + Constants.SCREEN_SIZE.height,
                (int) Constants.CERBERUS_RADIOS * 2,
                (int) Constants.CERBERUS_RADIOS * 2
        );
    }
}
