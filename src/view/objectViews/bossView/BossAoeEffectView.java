package view.objectViews.bossView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Math;
import utils.Vector;
import utils.area.Area;
import utils.area.Circle;
import view.objectViews.effectView.EffectView;

import java.awt.*;

public class BossAoeEffectView extends EffectView {
    private int radios;

    public BossAoeEffectView(Area area ,String id){
        this.area = area;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                ImageConstants.bossAoe,
                (int) position.x,
                (int) position.y,
                radios * 2,
                radios * 2,
                null
        );
    }

    @Override
    public void setEffect() {
        radios = (int)((Circle) area).getR();
        Vector center = ((Circle) area).getCenter();
        position = Math.VectorAdd(
                center,
                new Vector(
                        -radios + SizeConstants.SCREEN_SIZE.width ,
                        -radios + SizeConstants.SCREEN_SIZE.height
                )
        );
    }
}
