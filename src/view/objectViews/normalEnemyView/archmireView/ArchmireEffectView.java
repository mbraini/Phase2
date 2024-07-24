package view.objectViews.normalEnemyView.archmireView;

import constants.SizeConstants;
import utils.area.Area;
import utils.area.Polygon;
import view.objectViews.effectView.EffectView;

import java.awt.*;

public class ArchmireEffectView extends EffectView {
    private int[] x;
    private int[] y;
    private int n;

    public ArchmireEffectView(Area area, String id){
        this.area = area;
        this.id = id;
        this.color = Color.GREEN;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillPolygon(x ,y ,n);
    }

    @Override
    public void setEffect() {
        Polygon polygon = (Polygon) area;
        int[] xp = polygon.getX().stream().mapToInt(i -> i + SizeConstants.SCREEN_SIZE.width).toArray();
        int[] yp = polygon.getY().stream().mapToInt(i -> i + SizeConstants.SCREEN_SIZE.height).toArray();
        int np = polygon.getN();

        x = xp;
        y = yp;
        n = np;
    }
}
