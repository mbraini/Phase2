package view.objectViews.miniBossEnemyView;

import constants.Constants;
import utils.Vector;

import java.awt.*;

public class BarricadosView extends MiniBossEnemyView{

    public BarricadosView(Vector position ,String id){
        this.position = position;
        this.id = id;
        image = Constants.omenoct;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(
                image ,
                (int) position.getX() - Constants.BARRICADOS_DIMENSION.width / 2 + Constants.SCREEN_SIZE.width ,
                (int) position.getY() - Constants.BARRICADOS_DIMENSION.height / 2 + Constants.SCREEN_SIZE.height,
                Constants.BARRICADOS_DIMENSION.width ,
                Constants.BARRICADOS_DIMENSION.height ,
                null
        );
    }
}
