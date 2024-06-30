package model.objectModel;


import data.Constants;
import model.GameState;
import model.ModelData;
import model.interfaces.Ability;
import model.interfaces.IsCircle;
import model.interfaces.MoveAble;
import utils.Math;
import utils.Vector;

public class CollectiveModel extends ObjectModel implements IsCircle, Ability, MoveAble {
    int value;
    double time;
    boolean hasAbility = false;

    public CollectiveModel(Vector position , String id , int value){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 1;
        this.value = value;
    }

    @Override
    public double getRadios() {
        return Constants.COLLECTIVE_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void ability() {
        if ((GameState.getTime() - time) * 1000 >= Constants.COLLECTIVE_FADE){
//            Controller.removeRequest(this);
        }
    }

    @Override
    public boolean hasAbility() {
        return hasAbility;
    }

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);
    }

    @Override
    public void die() {
        ///////////////addXP
    }
}
