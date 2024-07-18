package model.objectModel.fighters;


import controller.enums.ModelType;
import constants.Constants;
import model.interfaces.IsCircle;
import model.objectModel.ObjectModel;
import utils.Math;
import utils.Vector;


public class EpsilonVertexModel extends ObjectModel implements IsCircle {

    private Vector epsilonPosition;

    public EpsilonVertexModel(Vector position ,Vector epsilonPosition ,double theta ,String id){
        this.position = position;
        this.id = id;
        this.theta = theta;
        this.epsilonPosition = epsilonPosition.clone();
        type = ModelType.epsilonVertex;
        this.HP = 1000000;
    }
    @Override
    public double getRadios() {
        return Constants.EPSILON_VERTICES_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }
    @Override
    public void die() {
        ////////////doNothing
    }

    public void rotateTo(double theta) {
        Vector direction = new Vector(
                Constants.EPSILON_VERTICES_RADIOS + Constants.EPSILON_DIMENSION.width / 2d,
                0
        );
        position = Math.RotateByTheta(Math.VectorAdd(epsilonPosition ,direction) ,epsilonPosition ,theta);
        this.theta = theta;
    }

    public void rotateBy(double theta) {
        this.theta += theta;
    }
}
