package view.objectViews;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class EpsilonProtectorView extends ObjectView{

    public EpsilonProtectorView(Vector position, String id){
        this.position = position;
        this.id = id;
        image = ImageConstants.epsilonImage;
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font(null,Font.BOLD ,20));
        g2d.setColor(Color.BLUE);
        g2d.drawOval(
                (int) position.x - SizeConstants.DISMAY_RADIOS + SizeConstants.SCREEN_SIZE.width,
                (int) position.y - SizeConstants.DISMAY_RADIOS + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.DISMAY_RADIOS * 2,
                SizeConstants.DISMAY_RADIOS * 2
        );
    }


}
