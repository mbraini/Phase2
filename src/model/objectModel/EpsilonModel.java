package model.objectModel;


import controller.configs.Configs;
import data.Constants;
import model.interfaces.*;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;

public class EpsilonModel extends ObjectModel implements MoveAble, IsCircle, HasVertices , ImpactAble {
    private static ArrayList<EpsilonVertexModel> vertices = new ArrayList<>();
    private boolean isImpacted = false;
    public EpsilonModel(Vector position , String id){
        this.position = position;
        this.velocity = new Vector();
        this.acceleration = new Vector(0 ,0);
        this.id =  id;
        this.HP = 100;

        this.isSolid = true;
        vertices = new ArrayList<>();
    }

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);
        ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,omega);
        checkMaxSpeed();
    }

    void checkMaxSpeed(){
        double currentSpeed = java.lang.Math.sqrt(java.lang.Math.pow(velocity.x ,2)
                + java.lang.Math.pow(velocity.y ,2));
        assert currentSpeed != 0;
        if (currentSpeed > Configs.EPSILON_MAX_SPEED){
            setVelocity(
                    getVelocity().x * Configs.EPSILON_MAX_SPEED / currentSpeed ,
                    getVelocity().y * Configs.EPSILON_MAX_SPEED / currentSpeed
            );
        }
    }

    @Override
    public double getRadios() {
        return Constants.EPSILON_DIMENSION.height / 2d;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public void addVertex(){
        theta = 0;
        vertices = new ArrayList<>();
        double theta = 2 * java.lang.Math.PI / Configs.VERTICES;
        for (int i = 0; i < Configs.VERTICES ;i++){
            vertices.add(new EpsilonVertexModel(this ,theta * i));
        }
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.get(i).setTheta(vertices.get(i).getTheta() + theta);
            vertices.get(i).Update();
        }
    }

    public static ArrayList<EpsilonVertexModel> getVertices(){
        return vertices;
    }
    public void Rotate(double theta){
        UpdateVertices(0 ,0 ,theta - this.theta);
        this.theta = theta;
    }

    @Override
    public boolean isImpacted() {
        return isImpacted;
    }

    @Override
    public void setImpacted(boolean impact) {
        isImpacted = impact;
    }

    public static void setVertices(ArrayList<EpsilonVertexModel> vertices) {
        EpsilonModel.vertices = vertices;
    }

    @Override
    public void die() {
        //////////////// endGame
        //////todo
    }
}
