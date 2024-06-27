package view.objectViews.normalEnemyView.archmireView;

import data.Constants;
import utils.Vector;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;

import java.awt.*;

public class ArchmirePointView extends EffectView {

    public ArchmirePointView(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.color = Color.RED;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(
                (int) position.x - Constants.ARCHMIRE_POINT_RADIOS + Constants.SCREEN_SIZE.width,
                (int) position.y - Constants.ARCHMIRE_POINT_RADIOS + Constants.SCREEN_SIZE.height,
                Constants.ARCHMIRE_POINT_RADIOS * 2,
                Constants.ARCHMIRE_POINT_RADIOS * 2
        );
    }
}
