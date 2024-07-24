package view.objectViews;

import constants.SizeConstants;
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
                (int) (position.x - SizeConstants.CERBERUS_RADIOS) + SizeConstants.SCREEN_SIZE.width,
                (int) (position.y - SizeConstants.CERBERUS_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.CERBERUS_RADIOS * 2,
                (int) SizeConstants.CERBERUS_RADIOS * 2
        );
    }
}
