package controller.listeners.epsilonMovementALs;


import controller.configs.Configs;
import model.objectModel.fighters.EpsilonModel;
import controller.listeners.EpsilonMovement;

public class xStopper extends Thread {
    private EpsilonModel epsilon;
    public xStopper(EpsilonModel epsilon){
        this.epsilon = epsilon;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(Configs.EPSILON_DECELERATION_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        epsilon.setAcceleration(0, epsilon.getAcceleration().y);
        epsilon.setVelocity(0 ,epsilon.getVelocity().y);
    }
}