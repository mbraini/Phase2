package model.objectModel.fighters.finalBoss.abilities.powerPunch;

import data.Constants;
import model.animations.DashAnimation;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;
import model.objectModel.fighters.finalBoss.bossHelper.PunchModel;
import model.objectModel.frameModel.FrameLocations;
import model.objectModel.frameModel.FrameModel;
import utils.Math;
import utils.Vector;

import javax.swing.*;

public class PowerPunch extends Ability {
    private Boss boss;
    private FrameModel epsilonFrame;
    private Timer timer;
    private FrameLocations frameLocation;

    public PowerPunch(Boss boss ,FrameModel epsilonFrame){
        this.boss = boss;
        this.epsilonFrame = epsilonFrame;
    }


    @Override
    protected void ownHelpers() {
        boss.getPunch().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();
        chooseFrameLocation();
        PunchAnimation();
        timer = new Timer(1000 ,new PowerPunchAL(epsilonFrame ,frameLocation ,this));
        timer.start();
    }

    private void PunchAnimation() {
        Vector destination = setDestination();
        Vector direction = Math.VectorAdd(
                destination ,
                Math.ScalarInVector(-1 ,boss.getPunch().getPosition())
        );
        new DashAnimation(
                boss.getPunch(),
                direction,
                1000,
                Math.VectorSize(direction),
                0
        ).StartAnimation();
    }

    private Vector setDestination() {
        Vector destination;
        Vector leftMid = new Vector(
                epsilonFrame.getPosition().x,
                epsilonFrame.getPosition().y + epsilonFrame.getSize().height / 2d
        );
        Vector topMid = new Vector(
                epsilonFrame.getPosition().x + epsilonFrame.getSize().width /2d,
                epsilonFrame.getPosition().y
        );
        Vector rightMid = new Vector(
                epsilonFrame.getPosition().x + epsilonFrame.getSize().width,
                epsilonFrame.getPosition().y + epsilonFrame.getSize().height /2d
        );
        Vector bottomMid = new Vector(
                epsilonFrame.getPosition().x + epsilonFrame.getSize().width /2d,
                epsilonFrame.getPosition().y + epsilonFrame.getSize().height
        );
        switch (frameLocation){
            case top :
                destination = Math.VectorAdd(
                        topMid,
                        new Vector(
                                0,
                                -Constants.PUNCH_DIMENSION.height /2d
                        )
                );
                break;
            case right:
                destination = Math.VectorAdd(
                        rightMid,
                        new Vector(
                                Constants.PUNCH_DIMENSION.width /2d,
                                0
                        )
                );
                break;
            case bottom:
                destination = Math.VectorAdd(
                        bottomMid,
                        new Vector(
                                0,
                                Constants.PUNCH_DIMENSION.height /2d
                        )
                );
                break;
            default:
                destination = Math.VectorAdd(
                        leftMid,
                        new Vector(
                                -Constants.PUNCH_DIMENSION.width /2d,
                                0
                        )
                );
                break;
        }
        return destination;
    }

    private void chooseFrameLocation() {
        PunchModel punchModel = boss.getPunch();
        if (punchModel.getPosition().x <= epsilonFrame.getPosition().x){
            frameLocation = FrameLocations.left;
            return;
        }
        if (punchModel.getPosition().x >= epsilonFrame.getPosition().x + epsilonFrame.getSize().width){
            frameLocation = FrameLocations.right;
            return;
        }
        if (punchModel.getPosition().y <= epsilonFrame.getPosition().y){
            frameLocation = FrameLocations.top;
            return;
        }
        frameLocation = FrameLocations.bottom;
    }

    @Override
    protected void endAbility() {

    }

    public Timer getTimer() {
        return timer;
    }
}
