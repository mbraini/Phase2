package view.objectViews.miniBossEnemyView;

import constants.SizeConstants;
import utils.area.Area;
import utils.area.Polygon;
import view.objectViews.effectView.EffectView;

import java.awt.*;

public class BlackOrbLaserEffectView extends EffectView {

    private int[] x;
    private int[] y;
    private int n;

    public BlackOrbLaserEffectView(Area area ,String id){
        this.id = id;
        this.area = area;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillPolygon(x ,y ,n);
    }

    @Override
    public void setEffect() {
        utils.area.Polygon polygon = (Polygon) area;
        int[] xp = polygon.getX().stream().mapToInt(i -> i + SizeConstants.SCREEN_SIZE.width).toArray();
        int[] yp = polygon.getY().stream().mapToInt(i -> i + SizeConstants.SCREEN_SIZE.height).toArray();
        int np = polygon.getN();

        x = xp;
        y = yp;
        n = np;
    }
}
