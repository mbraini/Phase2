package model.objectModel.effectModel;

import model.objectModel.ObjectModel;

import java.awt.*;

public abstract class EffectModel extends ObjectModel {

    protected Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
