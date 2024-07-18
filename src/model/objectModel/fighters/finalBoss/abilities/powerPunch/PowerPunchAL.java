package model.objectModel.fighters.finalBoss.abilities.powerPunch;

import constants.Constants;
import model.animations.FrameAnimation;
import model.objectModel.frameModel.FrameLocations;
import model.objectModel.frameModel.FrameModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerPunchAL implements ActionListener {

    private PowerPunch powerPunch;
    private FrameLocations frameLocation;
    private FrameModel epsilonModel;

    public PowerPunchAL(FrameModel epsilonModel, FrameLocations frameLocation ,PowerPunch powerPunch){
        this.frameLocation = frameLocation;
        this.epsilonModel = epsilonModel;
        this.powerPunch = powerPunch;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (frameLocation){
            case top :
                new FrameAnimation(
                        epsilonModel,
                        -Constants.PUNCH_FRAME_PUSH_DISTANCE,
                        0,
                        0,
                        0,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
            case right:
                new FrameAnimation(
                        epsilonModel,
                        0,
                        0,
                        -Constants.PUNCH_FRAME_PUSH_DISTANCE,
                        0,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
            case bottom:
                new FrameAnimation(
                        epsilonModel,
                        0,
                        -Constants.PUNCH_FRAME_PUSH_DISTANCE,
                        0,
                        0,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
            case left:
                new FrameAnimation(
                        epsilonModel,
                        0,
                        0,
                        0,
                        -Constants.PUNCH_FRAME_PUSH_DISTANCE,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
        }
        powerPunch.endAbility();
        powerPunch.getTimer().removeActionListener(this);
        powerPunch.getTimer().stop();
    }
}
