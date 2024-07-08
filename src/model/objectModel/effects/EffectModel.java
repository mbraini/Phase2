package model.objectModel.effects;

import controller.enums.EffectType;
import controller.manager.loading.SkippedByJson;
import model.objectModel.ObjectModel;
import utils.area.Area;

import java.awt.*;

public abstract class EffectModel extends ObjectModel {
    protected int R;
    protected int G;
    protected int B;
    protected Area area;
    protected EffectType effectType;


    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }
}
