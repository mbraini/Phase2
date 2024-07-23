package view.abilities;

import constants.Constants;
import utils.Vector;
import view.ViewData;

import java.awt.*;

public class SlumberView extends AbilityView {
    public SlumberView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = Constants.slumber;
    }

    @Override
    public void setUp() {
        super.setUp();
        frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() + frame.getWidth() - Constants.barD.width * 2 - Constants.ABILITY_VIEW_DIMENSION.width / 2d + Constants.SCREEN_SIZE.width,
                frame.getY() + ((2.5) / 11) * (frame.getHeight() - Constants.barD.height) + Constants.SCREEN_SIZE.height
                        - Constants.barD.height
        );
    }
}
