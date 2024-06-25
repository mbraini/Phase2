package view.painter;

import controller.Controller;
import controller.listeners.EpsilonAiming;
import controller.listeners.EpsilonCirculation;
import controller.listeners.EpsilonMovement;
import data.Constants;
import utils.Vector;
import view.ViewData;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;

import java.util.ArrayList;

public class Render extends Thread {

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaPaint = 0;
        while (true){
            long now = System.nanoTime();
            deltaPaint += (now - lastTime) / ns;
            lastTime = now;
            if (deltaPaint >= Constants.FPS) {
                Controller.updateView();
                paint();
                deltaPaint = 0;
            }
        }
    }

    private void paint(){
        ArrayList<FrameView> frames = ViewData.getFrames();
        ArrayList<ObjectView> views = ViewData.getViews();

        updateFrames(frames);
        updateImaginaryPanels(frames);
        paintViews(views);
    }

    private void updateImaginaryPanels(ArrayList<FrameView> frames) {
        for (int i = 0 ;i < frames.size() ;i++){
            Vector frameLocation = frames.get(i).getPosition();
            ViewData.getPanels().get(i).setLocation(
                    (int) -frameLocation.getX() - Constants.SCREEN_SIZE.width,
                    (int) -frameLocation.getY() - Constants.SCREEN_SIZE.height
            );
        }
    }

    private void paintViews(ArrayList<ObjectView> views) {
        for (ImaginaryPanel imaginaryPanel : ViewData.getPanels()) {
            imaginaryPanel.setVariables();
            imaginaryPanel.setViews(views);
            imaginaryPanel.revalidate();
            imaginaryPanel.repaint();
        }
    }

    private void updateFrames(ArrayList<FrameView> frames){
        for (int i = 0 ;i < frames.size() ;i++){
            frames.get(i).update();
        }
    }

}
