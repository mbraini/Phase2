package model.objectModel.fighters.finalBoss.bossHelper;

import constants.Constants;
import model.interfaces.HasVertices;
import model.interfaces.IsPolygon;
import model.objectModel.frameModel.FrameModelBuilder;
import utils.Math;
import utils.Vector;

import java.awt.*;
import java.util.ArrayList;

public class HandModel extends BossHelperModel implements IsPolygon , HasVertices {

    private ArrayList<Vector> vertices;


    public HandModel(Vector position ,String id){
        this.position = position;
        this.id = id;
        this.image = Constants.hand;
        this.velocity = new Vector();
        this.acceleration = new Vector();
        size = new Dimension(
                Constants.HAND_DIMENSION.width,
                Constants.HAND_DIMENSION.height
        );
        HP = 100;
        setHovering(true);
        initFrame();
        initVertices();
    }

    private void initVertices() {
        vertices = new ArrayList<>();
        vertices.add(getFrame().getPosition().clone());
        vertices.add(Math.VectorAdd(
                getFrame().getPosition(),
                new Vector(Constants.HAND_DIMENSION.width ,0)
        ));
        vertices.add(Math.VectorAdd(
                getFrame().getPosition(),
                new Vector(Constants.HAND_DIMENSION.width ,Constants.HAND_DIMENSION.height)
        ));
        vertices.add(Math.VectorAdd(
                getFrame().getPosition(),
                new Vector(0 ,Constants.HAND_DIMENSION.height)
        ));
    }


    @Override
    protected void initFrame() {
        FrameModelBuilder builder = new FrameModelBuilder(
                Math.VectorAdd(
                        position,
                        new Vector(
                                -Constants.HAND_DIMENSION.width / 2d,
                                -Constants.HAND_DIMENSION.height / 2d
                        )
                ),
                new Dimension(Constants.HAND_DIMENSION),
                id
        );
        builder.setIsometric(true);
        frame = builder.create();
    }

    @Override
    public void UpdateVertices(double xMoved, double yMoved, double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,new Vector(vertices.get(i).getX() + xMoved ,vertices.get(i).getY() + yMoved));
            vertices.set(i , Math.RotateByTheta(vertices.get(i) ,position ,theta));
        }
    }

    @Override
    public ArrayList<Vector> getVertices() {
        return vertices;
    }
}
