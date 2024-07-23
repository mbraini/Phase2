package view.objectViews;

import constants.Constants;
import utils.Vector;

import java.awt.*;

public class PortalView extends ObjectView{

    public PortalView(Vector position ,String id) {
        this.position = position;
        this.id = id;
        image = Constants.portal;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) (position.getX() - Constants.PORTAL_RADIOS) + Constants.SCREEN_SIZE.width ,
                (int) (position.getY() - Constants.PORTAL_RADIOS) + Constants.SCREEN_SIZE.height,
                (int) Constants.PORTAL_RADIOS * 2 ,
                (int) Constants.PORTAL_RADIOS * 2 ,
                null
        );
    }
}
