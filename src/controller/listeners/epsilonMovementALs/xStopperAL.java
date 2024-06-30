package controller.listeners.epsilonMovementALs;


import model.objectModel.fighters.EpsilonModel;
import controller.listeners.EpsilonMovement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xStopperAL implements ActionListener {
    EpsilonModel epsilon;
    public xStopperAL(EpsilonModel epsilon){
        this.epsilon = epsilon;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        epsilon.setAcceleration(0, epsilon.getAcceleration().y);
        epsilon.setVelocity(0 ,epsilon.getVelocity().y);
        EpsilonMovement.xStopper.stop();
    }
}
