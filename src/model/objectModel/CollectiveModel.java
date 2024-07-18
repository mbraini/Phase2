package model.objectModel;


import controller.Controller;
import constants.Constants;
import controller.manager.GameState;
import model.ModelData;
import model.interfaces.*;
import utils.Math;
import utils.Vector;

public class CollectiveModel extends ObjectModel implements IsCircle, Ability, MoveAble , CollisionDetector , Fader {
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
        return Constants.COLLECTIVE_ABILITY_ACTIVATION_RADIOS;
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
        Vector epsilonPosition = ModelData.getModels().getFirst().getPosition();
        Vector distance = Math.VectorAdd(
                epsilonPosition,
                Math.ScalarInVector(-1 ,position)
        );
        velocity = Math.VectorWithSize(
                distance,
                Constants.COLLECTIVE_VELOCITY
        );
        if (Math.VectorSize(distance) <= Constants.EPSILON_DIMENSION.width) {
            GameState.setXp(GameState.getXp() + value);
            die();
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
        Controller.removeObject(this);
    }

    @Override
    public void detect() {
        hasAbility = true;
    }

    @Override
    public void addTime(double time) {
        this.time += time;
    }

    @Override
    public void fadeIf() {
        if (time >= Constants.COLLECTIVE_FADE_TIME){
            die();
        }
    }
}
