package view.painter;

import constants.RefreshRateConstants;
import constants.SizeConstants;
import controller.ViewRequestController;
import utils.Vector;
import view.ViewData;
import view.ViewRequest;
import view.abilities.AbilityView;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.CerberusView;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;
import view.objectViews.normalEnemyView.archmireView.ArchmireView;

import java.util.ArrayList;

public class Render extends Thread {

    private ArrayList<FrameView> frames;
    private ArrayList<ObjectView> views;
    private ArrayList<EffectView> effects;
    private ArrayList<AbilityView> abilityViews;

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaPaint = 0;
        while (!interrupted()){
            long now = System.nanoTime();
            deltaPaint += (now - lastTime) / ns;
            lastTime = now;
            if (deltaPaint >= RefreshRateConstants.FPS) {
                ViewRequest.checkRequests();
                ViewRequestController.updateView();
                paint();
                deltaPaint = 0;
            }
        }
    }

    private void paint(){
        synchronized (ViewData.getViews()) {
            frames = (ArrayList<FrameView>) ViewData.getFrames().clone();
            views = (ArrayList<ObjectView>) ViewData.getViews().clone();
            effects = (ArrayList<EffectView>) ViewData.getEffectViews().clone();
            abilityViews = (ArrayList<AbilityView>) ViewData.getAbilityViews().clone();
        }
        updateFrames(frames);
        updateImaginaryPanels(frames);
        paintViews();
    }

    private void updateImaginaryPanels(ArrayList<FrameView> frames) {
        for (int i = 0 ;i < frames.size() ;i++){
            Vector frameLocation = frames.get(i).getPosition();
            ViewData.getPanels().get(i).setLocation(
                    (int) -frameLocation.getX() - SizeConstants.SCREEN_SIZE.width,
                    (int) -frameLocation.getY() - SizeConstants.SCREEN_SIZE.height
            );
        }
    }

    private void paintViews() {
        ArrayList<ObjectView> cerberuces = defineCerberuces();
        ArrayList<ObjectView> archmires = defineArchmires();
        ArrayList<ObjectView> otherViews = defineOtherViews();
        ArrayList<ObjectView> epsilons = defineEpsilons();
        for (ImaginaryPanel imaginaryPanel : ViewData.getPanels()) {
            imaginaryPanel.setVariables();
            imaginaryPanel.setEffects(effects);
            imaginaryPanel.setCerberuses(cerberuces);
            imaginaryPanel.setArchmires(archmires);
            imaginaryPanel.setOtherViews(otherViews);
            imaginaryPanel.setEpsilons(epsilons);
            imaginaryPanel.setAbilityViews(abilityViews);
            imaginaryPanel.revalidate();
            imaginaryPanel.repaint();
        }
    }

    private ArrayList<ObjectView> defineEpsilons() {
        ArrayList<ObjectView> answer = new ArrayList<>();
        for (ObjectView view : views) {
            if (view instanceof EpsilonView) {
                answer.add(view);
            }
        }
        return answer;
    }

    private ArrayList<ObjectView> defineOtherViews() {
        ArrayList<ObjectView> answer = new ArrayList<>();
        for (ObjectView view : views) {
            if (!(view instanceof CerberusView) && !(view instanceof ArchmireView)) {
                answer.add(view);
            }
        }
        return answer;
    }

    private ArrayList<ObjectView> defineArchmires() {
        ArrayList<ObjectView> answer = new ArrayList<>();
        for (ObjectView view : views) {
            if (view instanceof ArchmireView) {
                answer.add(view);
            }
        }
        return answer;
    }

    private ArrayList<ObjectView> defineCerberuces() {
        ArrayList<ObjectView> answer = new ArrayList<>();
        for (ObjectView view : views) {
            if (view instanceof CerberusView) {
                answer.add(view);
            }
        }
        return answer;
    }

    private void updateFrames(ArrayList<FrameView> frames){
        for (int i = 0 ;i < frames.size() ;i++){
            frames.get(i).update();
        }
    }

}
