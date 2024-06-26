package view.objectViews;

import utils.Vector;

import java.awt.*;

public abstract class ObjectView {
    protected String id;
    protected Vector position;
    protected double theta;
    protected boolean hovering;
    abstract public void draw(Graphics2D g2d);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
