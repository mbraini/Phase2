package view.objectViews.bossView;

import constants.Constants;
import utils.Vector;
import view.objectViews.EnemyView;

import java.awt.*;

public class HeadView extends BossHelperView {

    public HeadView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = Constants.omenoct;
        this.size = new Dimension(
                Constants.HEAD_DIMENSION.width,
                Constants.HEAD_DIMENSION.height
        );
    }

}
