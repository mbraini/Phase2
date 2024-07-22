package view.objectViews.bossView;

import constants.Constants;
import utils.Vector;
import view.objectViews.EnemyView;

import java.awt.*;

public class PunchView extends BossHelperView {

    public PunchView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.punch;
        this.size = new Dimension(
                Constants.PUNCH_DIMENSION.width,
                Constants.PUNCH_DIMENSION.height
        );
    }

}
