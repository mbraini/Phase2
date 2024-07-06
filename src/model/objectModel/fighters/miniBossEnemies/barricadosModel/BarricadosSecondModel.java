package model.objectModel.fighters.miniBossEnemies.barricadosModel;

import data.Constants;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;

public class BarricadosSecondModel extends BarricadosModel{

    public BarricadosSecondModel(Vector position ,String id){
        this.position = position;
        this.HP = 10000000;
        this.id = id;
        this.vulnerableToEpsilonBullet = false;
        this.vulnerableToEpsilonMelee = false;
        initVertices();
        initFrame();
    }

    private void initFrame() {
        FrameModelBuilder builder = new FrameModelBuilder(
                Math.VectorAdd(
                        position,
                        new Vector(
                                -Constants.BARRICADOS_DIMENSION.width / 2d,
                                -Constants.BARRICADOS_DIMENSION.height / 2d
                        )
                ),
                Constants.BARRICADOS_DIMENSION,
                id
        );
        builder.setSolid(true);
        builder.setIsometric(true);
        frameModel = builder.create();
    }

    private FrameModel frameModel;

    @Override
    public void die() {

    }

    public FrameModel getFrameModel() {
        return frameModel;
    }

    public void setFrameModel(FrameModel frameModel) {
        this.frameModel = frameModel;
    }
}
