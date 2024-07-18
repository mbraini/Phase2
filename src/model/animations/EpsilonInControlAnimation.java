package model.animations;


import constants.Constants;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.frameModel.FrameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpsilonInControlAnimation extends Animation{
    private EpsilonModel epsilon;
    private Timer timer;
    private FrameModel frame;
    double deltaX;
    double deltaY;
    double epsilonW;
    double epsilonH;
    public EpsilonInControlAnimation(EpsilonModel epsilon ,FrameModel frame){
        this.frame = frame;
        this.epsilon = epsilon;
        deltaX = (frame.getSize().width - Constants.EPSILON_DIMENSION.width);
        deltaY = (frame.getSize().height - Constants.EPSILON_DIMENSION.height);
        ///////////////////////
        deltaX = deltaX * Constants.WIN_ANIMATION_REFRESH_RATE / Constants.WIN_ANIMATION_TIME;
        deltaY = deltaY * Constants.WIN_ANIMATION_REFRESH_RATE / Constants.WIN_ANIMATION_TIME;
        frame.setUpDownV(0 ,0);
        frame.setLeftRightV(0 ,0);
        //////////////////////
        timer = new Timer(Constants.WIN_ANIMATION_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                epsilonW += deltaX;
                epsilonH += deltaY;
                Constants.EPSILON_DIMENSION = new Dimension((int) epsilonW ,(int) epsilonH);
                if (Constants.EPSILON_DIMENSION.width >= frame.getSize().width){
                    timer.stop();
                    new FrameVanishAnimation(frame).StartAnimation();
                }
            }
        });
    }

    @Override
    public void StartAnimation() {
        epsilonW = Constants.EPSILON_DIMENSION.width;
        epsilonH = Constants.EPSILON_DIMENSION.height;
        timer.start();
    }
}
