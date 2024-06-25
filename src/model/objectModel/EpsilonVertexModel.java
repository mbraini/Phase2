package model.objectModel;


import data.Constants;
import model.interfaces.IsCircle;
import utils.Math;
import utils.Vector;


public class EpsilonVertexModel extends ObjectModel implements IsCircle {
    EpsilonModel epsilon;
    public EpsilonVertexModel(EpsilonModel epsilon , double theta){
        this.epsilon = epsilon;
        this.theta = theta;
        this.position = new Vector();
        this.HP = 1;
    }
    @Override
    public double getRadios() {
        return Constants.EPSILON_VERTICES_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public void Update() {
        Vector origin = new Vector(
                epsilon.getPosition().x ,
                epsilon.getPosition().y - epsilon.getRadios() - getRadios()
        );
        position = Math.RotateByTheta(origin ,epsilon.getPosition() ,theta);
    }

}
