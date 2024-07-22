package model.animations;

import constants.Constants;
import controller.Controller;
import controller.manager.GameState;
import model.ModelData;
import model.objectModel.fighters.finalBoss.Boss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpsilonGetBigAnimation extends Animation{

    private Timer timer;
    private int delay = 10;

    public EpsilonGetBigAnimation() {
        setUpTimer();
    }

    private void setUpTimer() {
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ModelData.getEpsilon().getSize().width >= ModelData.getEpsilonFrame().getSize().width) {
                    Controller.endGame();
                    timer.stop();
                }
                ModelData.getEpsilon().setSize(
                        new Dimension(
                                ModelData.getEpsilon().getSize().width + Constants.EPSILON_DIMENSION.width / 10,
                                ModelData.getEpsilon().getSize().height + Constants.EPSILON_DIMENSION.height / 10
                        )
                );
            }
        });
    }

    @Override
    public void StartAnimation() {
        GameState.setIsInAnimation(true);
        timer.start();
    }
}
