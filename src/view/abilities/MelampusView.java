package view.abilities;

import constants.Constants;
import utils.Vector;
import view.ViewData;

import java.awt.*;

public class MelampusView extends AbilityView {
    public MelampusView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = Constants.melampus;
    }

    @Override
    public void setUp() {
        super.setUp();
        frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() + ((2.5) / 11) * (frame.getWidth() - Constants.barD.width) + Constants.SCREEN_SIZE.width,
                frame.getY() + frame.getHeight() - Constants.ABILITY_VIEW_DIMENSION.height / 2d +
                        Constants.SCREEN_SIZE.height - Constants.barD.height * 1.43
        );
    }
}
