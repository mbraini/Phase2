package view.objectViews.bossView;

import data.Constants;
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
                Constants.bossAoe,
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
                        -radios + Constants.SCREEN_SIZE.width ,
                        -radios + Constants.SCREEN_SIZE.height
                )
        );
    }
}
