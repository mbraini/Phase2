package view.abilities;

import constants.SizeConstants;
import utils.Vector;
import view.objectViews.FrameView;

import java.awt.*;

public class AbilityView {

    protected int coolDownTime;
    protected int timePassed;
    protected boolean isAvailable;
    protected int theta;
    protected Image image;
    protected Vector position;
    protected FrameView frame;
    protected Color color;

    public AbilityView(int coolDownTime ,int timePassed ,boolean isAvailable) {
        this.coolDownTime = coolDownTime;
        this.timePassed = timePassed;
        this.isAvailable = isAvailable;
        position = new Vector();
        color = Color.RED;
    }

    public void draw(Graphics2D g2d) {
        if (position == null)
            return;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawArc(
                (int) position.x,
                (int) position.y,
                SizeConstants.ABILITY_VIEW_DIMENSION.width,
                SizeConstants.ABILITY_VIEW_DIMENSION.height,
                0,
                theta
        );
        g2d.drawImage(
                image,
                (int) position.x,
                (int) position.y,
                SizeConstants.ABILITY_VIEW_DIMENSION.width,
                SizeConstants.ABILITY_VIEW_DIMENSION.height,
                null
        );
    }

    public int getCoolDownTime() {
        return coolDownTime;
    }

    public void setCoolDownTime(int coolDownTime) {
        this.coolDownTime = coolDownTime;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public void setTimePassed(int timePassed) {
        this.timePassed = timePassed;
    }

    public void setFrame(FrameView frame) {
        this.frame = frame;
    }

    public void setUp() {
        theta =(int) Math.toDegrees((timePassed * (1d) / coolDownTime) * Math.PI * 2);
        if (isAvailable) {
            color = Color.GREEN;
            theta = 360;
        }
        else {
            color = Color.RED;
        }
    }
}
