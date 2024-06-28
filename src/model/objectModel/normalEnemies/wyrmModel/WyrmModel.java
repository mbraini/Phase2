package model.objectModel.normalEnemies.wyrmModel;

import data.Constants;
import model.ModelData;
import model.interfaces.*;
import model.objectModel.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.frameModel.FrameModelBuilder;
import model.objectModel.normalEnemies.NormalEnemyModel;
import utils.Math;
import utils.Vector;

import javax.swing.*;
import java.util.ArrayList;

public class WyrmModel extends NormalEnemyModel implements Navigator , FrameSticker , MoveAble ,IsPolygon {

    private FrameModel frameModel;
    private boolean isInRange;
    private ArrayList<Vector> vertices;
    private Timer shooter;
    private WyrmThread wyrmThread;

    public WyrmModel(Vector position ,String id){
        this.id = id;
        this.position = position;
        this.velocity = new Vector(0 ,0);
        this.acceleration = new Vector(0 ,0);
        this.HP = 20;

        setThetaRelativeToEpsilon();
        setFrame();

        setPosition(Math.VectorAdd(
                position,
                new Vector(frameModel.getSize().width / 2d ,frameModel.getSize().height / 2d))
        );

    }

    private void setThetaRelativeToEpsilon() {
        Vector epsilonPosition = ModelData.getModels().getFirst().getPosition();
        Vector xVector = new Vector(1 ,0);
        double dotProduct = Math.DotProduct(epsilonPosition ,xVector);
        double cosTheta = dotProduct / Math.VectorSize(epsilonPosition);
        setTheta(java.lang.Math.acos(cosTheta));
    }

    private void setFrame() {
        FrameModelBuilder builder = new FrameModelBuilder(
                position.clone(),
                Constants.WYRM_FRAME_DIMENSION,
                id
        );
        builder.setIsometric(true);
        builder.setSolid(false);
        frameModel = builder.create();
    }

    @Override
    protected void meleeAttack(EpsilonModel epsilon) {

    }

    @Override
    public void die() {

    }

    public FrameModel getFrameModel() {
        return frameModel;
    }

    public void setFrameModel(FrameModel frameModel) {
        this.frameModel = frameModel;
    }

    @Override
    public boolean hasArrived() {
        return isInRange;
    }

    @Override
    public void navigate() {
        WyrmNavigator navigator = new WyrmNavigator(this);
        navigator.navigate();
        isInRange = navigator.hasArrived();
        if (isInRange){
            setVelocity(0 ,0);
            shooter = new Timer(Constants.WYRM_SHOOTING_TIME ,new WyrmShooter(this));
            shooter.start();

            wyrmThread = new WyrmThread(
                    this ,
                    ModelData.getModels().getFirst().getPosition(),
                    true
            );
            wyrmThread.start();
        }
    }

    @Override
    public void setStuckFramePosition() {
        frameModel.transfer(Math.VectorAdd(
                position,
                new Vector(
                        -Constants.WYRM_FRAME_DIMENSION.width / 2d,
                        -Constants.WYRM_FRAME_DIMENSION.height / 2d
                )
        ));
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
    public ArrayList<Vector> getVertices() {
        return vertices;
    }
}
