package model.objectModel.fighters.normalEnemies.necropickModel;

import controller.Controller;
import controller.enums.ModelType;
import controller.manager.Spawner;
import data.Constants;
import model.ModelData;
import model.interfaces.Ability;
import model.interfaces.HasVertices;
import model.interfaces.IsPolygon;
import model.interfaces.MoveAble;
import model.objectModel.fighters.normalEnemies.NormalEnemyModel;
import utils.Math;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NecropickModel extends NormalEnemyModel implements MoveAble ,Ability , IsPolygon ,HasVertices {
    private Timer timer;
    private Timer abilityTimer;
    private boolean hasAbility;
    private ArrayList<Vector> vertices;
    private boolean isArrived;

    public NecropickModel(Vector position ,String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 10;
        type = ModelType.necropick;
        vulnerableToEpsilonBullet = true;
        vulnerableToEpsilonMelee = true;
        initVertices();
        timer = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHovering(true);
                hasAbility = true;
                abilityTimer.start();
                timer.stop();
            }
        });
        abilityTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NecropickShooter(position).shoot();
                setHovering(false);
                hasAbility = false;
                velocity = new Vector();
                timer.start();
                abilityTimer.stop();
            }
        });
        timer.start();
    }


    @Override
    public void die() {
        Controller.removeObject(this);
        Spawner.addCollectives(position ,4 ,2);
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
    public void ability() {
        NecropickNavigator navigator = new NecropickNavigator(
                position,
                ModelData.getModels().getFirst().getPosition()
        );
        navigator.navigate();
        setVelocity(navigator.getVelocity());
    }

    @Override
    public boolean hasAbility() {
        return hasAbility;
    }

    @Override
    public ArrayList<Vector> getVertices() {
        return vertices;
    }

    void initVertices(){
        vertices = new ArrayList<>();
        vertices.add(new Vector(
                position.x ,
                position.y - (java.lang.Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 3d))
        );
        vertices.add(new Vector(
                position.x - Constants.TRIGORATH_DIMENTION.width / 2d ,
                position.y + (java.lang.Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 6d))
        );
        vertices.add(new Vector(
                position.x + Constants.TRIGORATH_DIMENTION.width / 2d ,
                position.y + (java.lang.Math.sqrt(3) * Constants.TRIGORATH_DIMENTION.width / 6d))
        );
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.set(i ,new Vector(vertices.get(i).getX() + xMoved ,vertices.get(i).getY() + yMoved));
            vertices.set(i , Math.RotateByTheta(vertices.get(i) ,position ,theta));
        }
    }
}
