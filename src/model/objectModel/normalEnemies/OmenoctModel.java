package model.objectModel.normalEnemies;

import controller.enums.ObjectType;
import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.collision.Collision;
import model.interfaces.*;
import model.objectModel.EpsilonModel;
import model.objectModel.frameModel.FrameLocations;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.normalEnemies.helper.OmenoctNavigater;
import model.objectModel.projectiles.EpsilonBulletModel;
import utils.Helper;
import utils.Math;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OmenoctModel extends NormalEnemyModel implements Ability , MoveAble, FrameAttacher {
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

        shooter = new Timer(Constants.OMENOCT_FIRING_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EpsilonModel epsilon = (EpsilonModel) ModelData.getModels().getFirst();
                Vector direction = Math.VectorAdd(
                        Math.ScalarInVector(-1 ,position),
                        epsilon.getPosition()
                );
                String id = Helper.RandomStringGenerator(Constants.ID_SIZE);
                Vector bulletPosition = Math.VectorAdd(
                        position,
                        Math.VectorWithSize(
                                direction ,
                                Constants.OMENOCT_BULLET_RADIOUS + Constants.OMENOCT_RADIOS
                        )
                );
                Spawner.addProjectile(bulletPosition ,direction , ObjectType.omenoctBullet);
            }
        });
    }


    @Override
    protected void meleeAttack(EpsilonModel epsilon) {

    }

    @Override
    public void ability() {
        OmenoctNavigater navigater = new OmenoctNavigater(position ,velocity);
        navigater.navigateFrame();
        velocity = navigater.getVelocity();
        destination = navigater.getDestination();
        willAttachTo = navigater.getWillAttachTo();

        checkAttached();

        if (frameLocation != null){
            shooter.start();
        }
        else {
            shooter.stop();
        }
    }

    private void checkAttached() {
        if (Math.VectorSize(Math.VectorAdd(
                Math.ScalarInVector(-1 ,position),
                destination
        )) <= Constants.OMENOCT_NAVIGATE_VELOCITY){
            position = destination.clone();
        }
        if (destination.Equals(position)){
            frameLocation = willAttachTo;
        }
        else {
            frameLocation = null;
        }
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
}
