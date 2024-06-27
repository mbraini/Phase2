package view.painter;

import controller.Controller;
import controller.listeners.EpsilonAiming;
import controller.listeners.EpsilonCirculation;
import controller.listeners.EpsilonMovement;
import data.Constants;
import utils.Vector;
import view.ViewData;
import view.ViewRequest;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;

import java.util.ArrayList;

public class Render extends Thread {

    private ArrayList<FrameView> frames;
    private ArrayList<ObjectView> views;
    private ArrayList<EffectView> effects;

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
                ViewRequest.checkRequests();
                ////////null problems
                checkIfNull();
                /////////////////
                Controller.updateView();
                paint();
                deltaPaint = 0;
            }
        }
    }

    private void checkIfNull() {
        for (int i = 0 ;i < ViewData.getViews().size() ;i++){
            if (ViewData.getViews().get(i) == null){
                ViewData.getViews().remove(i);
                i--;
            }
        }
        for (int i = 0; i < ViewData.getEffectViews().size() ;i++){
            if (ViewData.getEffectViews().get(i) == null){
                ViewData.getEffectViews().remove(i);
                i--;
            }
        }
        for (int i = 0 ;i < ViewData.getFrames().size() ;i++){
            if (ViewData.getFrames().get(i) == null){
                ViewData.getFrames().remove(i);
                i--;
            }
        }
    }

    private void paint(){
        synchronized (ViewData.getViews()) {
            frames = (ArrayList<FrameView>) ViewData.getFrames().clone();
            views = (ArrayList<ObjectView>) ViewData.getViews().clone();
            effects = (ArrayList<EffectView>) ViewData.getEffectViews().clone();
        }
        updateFrames(frames);
        updateImaginaryPanels(frames);
        paintViews(views ,effects);
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

    private void paintViews(ArrayList<ObjectView> views , ArrayList<EffectView> effects) {
        for (ImaginaryPanel imaginaryPanel : ViewData.getPanels()) {
            imaginaryPanel.setVariables();
            imaginaryPanel.setViews(views);
            imaginaryPanel.setEffects(effects);
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
