package model.objectModel.effects;

import model.objectModel.ObjectModel;
import utils.area.Area;

import java.awt.*;

public abstract class EffectModel extends ObjectModel {

    protected Color color;
    protected Area area;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
