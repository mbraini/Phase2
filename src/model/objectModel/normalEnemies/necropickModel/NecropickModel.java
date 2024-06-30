package model.objectModel.normalEnemies.necropickModel;

import data.Constants;
import model.ModelData;
import model.interfaces.Ability;
import model.interfaces.HasVertices;
import model.interfaces.MoveAble;
import model.objectModel.EpsilonModel;
import model.objectModel.normalEnemies.NormalEnemyModel;
import utils.Math;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NecropickModel extends NormalEnemyModel implements MoveAble ,Ability{
    private Timer timer;
    private Timer abilityTimer;
    private boolean hasAbility;

    public NecropickModel(Vector position ,String id){
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.id = id;
        this.HP = 20;
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
}
