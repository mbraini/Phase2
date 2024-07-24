package model.animations;

import constants.ImageConstants;
import constants.SizeConstants;
import controller.Controller;
import controller.manager.GameState;
import model.ModelRequests;
import model.objectModel.fighters.finalBoss.Boss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossDeathAnimation extends Animation{

    private Boss boss;
    private Timer timer;
    private int timePassed;
    private int delay = 60;

    public BossDeathAnimation(Boss boss) {
        this.boss = boss;
        setUpTimer();
    }

    private void setUpTimer() {
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePassed += delay;
                if (timePassed / delay >= 98) {
                    killEveryOne();
                    new EpsilonGetBigAnimation().StartAnimation();
                    timer.stop();
                }
                boss.getHead().setSize(
                        new Dimension(
                                boss.getHead().getSize().width - SizeConstants.HEAD_DIMENSION.width / 100,
                                boss.getHead().getSize().height - SizeConstants.HEAD_DIMENSION.height / 100
                        )
                );
                boss.getLeftHand().setSize(
                        new Dimension(
                                boss.getLeftHand().getSize().width - SizeConstants.HAND_DIMENSION.width / 100,
                                boss.getLeftHand().getSize().height - SizeConstants.HAND_DIMENSION.height / 100
                        )
                );
                boss.getRightHand().setSize(
                        new Dimension(
                                boss.getRightHand().getSize().width - SizeConstants.HAND_DIMENSION.width / 100,
                                boss.getRightHand().getSize().height - SizeConstants.HAND_DIMENSION.height / 100
                        )
                );
                boss.getPunch().setSize(
                        new Dimension(
                                boss.getPunch().getSize().width - SizeConstants.PUNCH_DIMENSION.width / 100,
                                boss.getPunch().getSize().height - SizeConstants.PUNCH_DIMENSION.height / 100
                        )
                );
            }
        });
    }

    private void killEveryOne() {
        if (!boss.getRightHand().isDead()) {
            Controller.removeObject(boss.getRightHand());
            Controller.removeFrame(boss.getLeftHand().getFrame());
            Controller.addXP(100);
        }
        if (!boss.getLeftHand().isDead()) {
            Controller.removeObject(boss.getLeftHand());
            Controller.removeFrame(boss.getRightHand().getFrame());
            Controller.addXP(100);
        }
        Controller.removeObject(boss.getHead());
        Controller.removeFrame(boss.getHead().getFrame());
        Controller.removeObject(boss.getPunch());
        Controller.removeFrame(boss.getPunch().getFrame());
        ModelRequests.removeAbstractEnemy(boss.getId());
        Controller.addXP(300);
    }

    @Override
    public void StartAnimation() {
        GameState.setIsInAnimation(true);
        setUpPictures();
        timer.start();
    }

    private void setUpPictures() {
        boss.getLeftHand().setImage(ImageConstants.skeleton);
        boss.getRightHand().setImage(ImageConstants.skeleton);
        boss.getHead().setImage(ImageConstants.skeleton);
        boss.getPunch().setImage(ImageConstants.skeleton);
    }
}
