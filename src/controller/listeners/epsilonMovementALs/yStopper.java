package controller.listeners.epsilonMovementALs;


import controller.configs.Configs;
import model.objectModel.fighters.EpsilonModel;
import controller.listeners.EpsilonMovement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class yStopper extends Thread {
    EpsilonModel epsilon;
    public yStopper(EpsilonModel epsilon){
        this.epsilon = epsilon;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Configs.EPSILON_DECELERATION_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        epsilon.setAcceleration(epsilon.getAcceleration().x ,0);
        epsilon.setVelocity(epsilon.getVelocity().x ,0);
    }
}