package view.abilities;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;
import view.ViewData;

public class ProteusView extends AbilityView {
    public ProteusView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = ImageConstants.proteus;
    }

    @Override
    public void setUp() {
        super.setUp();
        frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() + ((8.5) / 11) * (frame.getWidth() - SizeConstants.barD.width) + SizeConstants.SCREEN_SIZE.width,
                frame.getY() + frame.getHeight() - SizeConstants.ABILITY_VIEW_DIMENSION.height / 2d +
                        SizeConstants.SCREEN_SIZE.height - SizeConstants.barD.height * 1.43
        );
    }
}
