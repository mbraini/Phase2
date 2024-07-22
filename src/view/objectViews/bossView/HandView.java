package view.objectViews.bossView;

import constants.Constants;
import utils.Vector;

import java.awt.*;

public class HandView extends BossHelperView {

    public HandView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
        this.size = new Dimension(
                Constants.HAND_DIMENSION.width,
                Constants.HAND_DIMENSION.height
        );
    }

}
