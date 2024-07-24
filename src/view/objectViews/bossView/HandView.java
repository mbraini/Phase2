package view.objectViews.bossView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class HandView extends BossHelperView {

    public HandView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.omenoct;
        this.size = new Dimension(
                SizeConstants.HAND_DIMENSION.width,
                SizeConstants.HAND_DIMENSION.height
        );
    }

}
