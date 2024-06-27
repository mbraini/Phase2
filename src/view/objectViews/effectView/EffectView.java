package view.objectViews.effectView;

import utils.area.Area;
import view.objectViews.ObjectView;

import java.awt.*;

public abstract class EffectView extends ObjectView {
    protected Color color;
    protected Area area;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public abstract void setEffect();

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
