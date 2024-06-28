package model.objectModel.normalEnemies.omenoctModel;

import controller.enums.ObjectType;
import controller.manager.Spawner;
import data.Constants;
import model.ModelData;
import model.interfaces.*;
import model.objectModel.EpsilonModel;
import model.objectModel.frameModel.FrameLocations;
import model.objectModel.normalEnemies.NormalEnemyModel;
import utils.Helper;
import utils.Math;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OmenoctModel extends NormalEnemyModel implements Ability , MoveAble, FrameAttacher {

    /////////////////////// Fix the Ability interface with Navigator interface


    private ArrayList<Vector> vertices = new ArrayList<>();
    private FrameLocations frameLocation;
    private FrameLocations willAttachTo;
    private Vector destination;
    private Timer shooter;

    public OmenoctModel(Vector position ,String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 20;
        omega = Constants.ENEMY_ROTATION_SPEED;
    }


    @Override
    protected void meleeAttack(EpsilonModel epsilon) {

    }

    @Override
    public void ability() {
        OmenoctNavigater navigater = new OmenoctNavigater(position);

        navigater.navigateFrame();
        destination = navigater.getDestination();
        willAttachTo = navigater.getWillAttachTo();
        setNavigationVelocity();
        checkAttached();
    }

    private void setNavigationVelocity() {
        assert destination!= null;
        velocity = Math.VectorWithSize(
                Math.VectorAdd(
                        Math.ScalarInVector(-1 ,position),
                        destination
                )
                ,Constants.OMENOCT_NAVIGATE_VELOCITY
        );
    }

    private void checkAttached() {
        if (willAttachTo == null)
            return;
        if (Math.VectorSize(Math.VectorAdd(
                Math.ScalarInVector(-1 ,position),
                destination
        )) <= Constants.OMENOCT_NAVIGATE_VELOCITY * Constants.UPS){
            position = destination.clone();
            velocity = new Vector();
        }
        if (destination.Equals(position)){
            frameLocation = willAttachTo;
            setUpShooter();
        }
        else {
            frameLocation = null;
            if (shooter != null){
                shooter.stop();
            }
        }
    }

    private void setUpShooter() {
        if (shooter != null){
            shooter.stop();
        }
        shooter = new Timer(Constants.OMENOCT_FIRING_TIME ,new OmenoctShooter(position));
        shooter.start();
    }


    @Override
    public boolean hasAbility() {
        return true;
    }

//    @Override
//    public ArrayList<Vector> getVertices() {
//        return vertices;
//    }

    @Override
    public FrameLocations getAttachedLocation() {
        return frameLocation;
    }

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);


        omega += alpha * Constants.UPS;
        double thetaMoved = ((2 * omega - alpha * Constants.UPS) / 2) * Constants.UPS;
        theta = theta + thetaMoved;
        if (this instanceof HasVertices)
            ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,thetaMoved);
    }

    @Override
    public void die() {
        //////////////collective
    }
}
