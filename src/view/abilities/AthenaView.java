package view.abilities;

import constants.Constants;
import utils.Vector;
import view.ViewData;

import java.awt.*;

public class AthenaView extends AbilityView {
    public AthenaView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = Constants.athena;
    }

    @Override
    public void setUp() {
        super.setUp();
        frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() + ((6.5) / 11) * (frame.getWidth() - Constants.barD.width) + Constants.SCREEN_SIZE.width,
                frame.getY() + frame.getHeight() - Constants.ABILITY_VIEW_DIMENSION.height / 2d +
                        Constants.SCREEN_SIZE.height - Constants.barD.height * 1.43
        );
    }
}
