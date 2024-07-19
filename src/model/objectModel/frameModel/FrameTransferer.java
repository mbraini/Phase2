package model.objectModel.frameModel;

import controller.Controller;
import model.ModelData;
import model.collision.Collision;
import utils.Math;
import utils.Vector;

import java.awt.*;
import java.util.ArrayList;

public class FrameTransferer {

    private FrameModel frameModel;
    private ArrayList<FrameModel> frames;

    public FrameTransferer(FrameModel frameModel){
        this.frameModel = frameModel;
        synchronized (Controller.getController(Controller.getIP()).getModelData().getModels()) {
            frames = (ArrayList<FrameModel>) Controller.getController(Controller.getIP()).getModelData().getFrames().clone();
        }
    }


    public void transfer(Vector newPosition){
        Vector lastPosition = frameModel.getPosition().clone();
        Vector moved = Math.VectorAdd(
                newPosition,
                Math.ScalarInVector(-1 ,frameModel.getPosition())
        );
        frameModel.setPositionInit(newPosition.clone());
        frameModel.setPosition(newPosition.clone());
        frameModel.setLeftRightP(0 ,0);
        frameModel.setUpDownP(0 ,0);
        frameModel.UpdateVertices(moved.x , moved.y ,0);

        if (Collision.IsCollidingWithSolidFrames(frameModel ,frames)){
            frameModel.setPositionInit(lastPosition.clone());
            frameModel.setPosition(lastPosition.clone());
            frameModel.UpdateVertices(-moved.x ,-moved.y ,0);
        }
        else {
            frameModel.setDimensionInit(new Dimension(frameModel.getSize().width ,frameModel.getSize().height));
        }
    }

}
