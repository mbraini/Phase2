package view.objectViews.effectView;

import view.objectViews.ObjectView;

import java.awt.*;

public abstract class EffectView extends ObjectView {
    protected Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
