package view.objectViews.bossView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class PunchView extends BossHelperView {

    public PunchView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.punch;
        this.size = new Dimension(
                SizeConstants.PUNCH_DIMENSION.width,
                SizeConstants.PUNCH_DIMENSION.height
        );
    }

}
