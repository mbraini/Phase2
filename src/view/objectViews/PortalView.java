package view.objectViews;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class PortalView extends ObjectView{

    public PortalView(Vector position ,String id) {
        this.position = position;
        this.id = id;
        image = ImageConstants.portal;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) (position.getX() - SizeConstants.PORTAL_RADIOS) + SizeConstants.SCREEN_SIZE.width ,
                (int) (position.getY() - SizeConstants.PORTAL_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.PORTAL_RADIOS * 2 ,
                (int) SizeConstants.PORTAL_RADIOS * 2 ,
                null
        );
    }
}
