package model.objectModel.fighters.finalBoss.abilities.vomit;

import data.Constants;
import model.GameState;

public class VomitThread extends Thread{

    private double time;

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
                update();
                deltaModel = 0;
                time += Constants.SQEEZE_THREAD_REFRESH_RATE;
            }
        }
    }

    private void update() {

    }
}
