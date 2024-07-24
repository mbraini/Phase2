package view.objectViews.miniBossEnemyView;

import constants.ImageConstants;
import constants.SizeConstants;
import utils.Vector;

import java.awt.*;

public class BarricadosView extends MiniBossEnemyView{

    public BarricadosView(Vector position ,String id){
        this.position = position;
        this.id = id;
        image = ImageConstants.barricados;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.getX() - SizeConstants.BARRICADOS_DIMENSION.width / 2 + SizeConstants.SCREEN_SIZE.width ,
                (int) position.getY() - SizeConstants.BARRICADOS_DIMENSION.height / 2 + SizeConstants.SCREEN_SIZE.height,
                SizeConstants.BARRICADOS_DIMENSION.width ,
                SizeConstants.BARRICADOS_DIMENSION.height ,
                null
        );
    }
}
