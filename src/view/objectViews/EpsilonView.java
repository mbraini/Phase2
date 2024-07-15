package view.objectViews;


import controller.interfaces.SizeChanger;
import data.Constants;
import utils.Vector;

import java.awt.*;

public class EpsilonView extends ObjectView implements SizeChanger {
    private Dimension size;
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
                (int) position.getX() - size.width / 2 + Constants.SCREEN_SIZE.width ,
                (int) position.getY() - size.height / 2 + Constants.SCREEN_SIZE.height,
                size.width ,
                size.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + Constants.SCREEN_SIZE.width ,
                position.getY() + Constants.SCREEN_SIZE.height
        );
    }

    @Override
    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public Dimension getSize() {
        return size;
    }
}
