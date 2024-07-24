package view.objectViews.normalEnemyView.archmireView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;
import view.objectViews.normalEnemyView.NormalEnemyView;

import java.awt.*;

public class ArchmireView extends NormalEnemyView {

    public ArchmireView(Vector position , String id){
        this.position = position;
        this.id = id;
        this.image = ImageConstants.archmire;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.rotate(
                -theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g2d.drawImage(
                image ,
                (int) position.getX() - SizeConstants.ARCHMIRE_DIMENSION.width / 2 + SizeConstants.SCREEN_SIZE.width ,
                (int) position.getY() - SizeConstants.ARCHMIRE_DIMENSION.height / 2 + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.ARCHMIRE_DIMENSION.width ,
                SizeConstants.ARCHMIRE_DIMENSION.height ,
                null
        );
        g2d.rotate(
                theta ,
                position.getX() + SizeConstants.SCREEN_SIZE.width ,
                position.getY() + SizeConstants.SCREEN_SIZE.height
        );
    }
}
