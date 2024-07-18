package view.objectViews;

import constants.Constants;
import utils.Vector;

import java.awt.*;

public class EpsilonProtectorView extends ObjectView{

    public EpsilonProtectorView(Vector position, String id){
        this.position = position;
        this.id = id;
        image = Constants.epsilonImage;
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.setFont(new Font(null,Font.BOLD ,20));
        g2d.setColor(Color.BLUE);
        g2d.drawOval(
                (int) position.x - Constants.DISMAY_RADIOS + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.DISMAY_RADIOS + Constants.SCREEN_SIZE.height,
                Constants.DISMAY_RADIOS * 2,
                Constants.DISMAY_RADIOS * 2
        );
    }


}
