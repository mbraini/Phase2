package view.abilities;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;
import view.ViewData;

public class AresView extends AbilityView{
    public AresView(int coolDownTime, int timePassed, boolean isAvailable) {
        super(coolDownTime, timePassed, isAvailable);
        image = ImageConstants.ares;
    }

    @Override
    public void setUp() {
        super.setUp();
        frame = ViewData.getEpsilonFrame();
        if (frame == null)
            return;
        position = new Vector(
                frame.getX() - SizeConstants.barD.width + SizeConstants.ABILITY_VIEW_DIMENSION.width / 2d + SizeConstants.SCREEN_SIZE.width,
                frame.getY() + ((2.5) / 11) * (frame.getHeight() - SizeConstants.barD.height) + SizeConstants.SCREEN_SIZE.height
                - SizeConstants.barD.height
        );
    }
}
