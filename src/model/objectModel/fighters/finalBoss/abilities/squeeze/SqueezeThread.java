package model.objectModel.fighters.finalBoss.abilities.squeeze;

import data.Constants;
import model.GameState;

public class SqueezeThread extends Thread {

    private Squeeze squeeze;
    private double time;

    public SqueezeThread(Squeeze squeeze){
        this.squeeze = squeeze;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isPause() && !GameState.isOver() && !isInterrupted()) {
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.SQEEZE_THREAD_REFRESH_RATE) {
                squeeze();
                deltaModel = 0;
                time += Constants.SQEEZE_THREAD_REFRESH_RATE;
            }
        }
    }

    private void squeeze() {
        if (time >= Constants.SQUEEZE_DURATON) {
            squeeze.endAbility();
            return;
        }
        if (!squeeze.hasArrived()){
            squeeze.navigate();
            squeeze.checkArrived();
        }
        else {
            placeHands();
        }
    }

    private void placeHands() {

    }
}
