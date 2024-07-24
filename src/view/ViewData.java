package view;

import controller.enums.InGameAbilityType;
import controller.enums.SkillTreeAbilityType;
import controller.listeners.EpsilonAiming;
import controller.listeners.EpsilonCirculation;
import controller.listeners.epsilonMovement.EpsilonMovement;
import view.abilities.*;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.effectView.EffectView;

import java.util.ArrayList;

public class ViewData {
    private static ArrayList<ImaginaryPanel> panels;
    private static ArrayList<FrameView> frames;
    private static ArrayList<ObjectView> views;
    private static ArrayList<EffectView> effectViews;
    private static ArrayList<AbilityView> abilityViews;
    private static double time;
    private static double hp;
    private static double xp;
    private static int wave;
    private static FrameView epsilonFrame;

    public static void resetAll() {
        panels = new ArrayList<>();
        if (frames != null) {
            for (FrameView frameView : frames) {
                frameView.dispose();
            }
        }
        frames = new ArrayList<>();
        views = new ArrayList<>();
        effectViews = new ArrayList<>();
        abilityViews = new ArrayList<>();
    }


    public static ArrayList<FrameView> getFrames() {
        return frames;
    }

    public static ArrayList<ObjectView> getViews() {
        return views;
    }

    public static void setFrames(ArrayList<FrameView> frames) {
        ViewData.frames = frames;
    }

    public static void setViews(ArrayList<ObjectView> views) {
        ViewData.views = views;
    }

    public static void addImaginaryPanel(ImaginaryPanel imaginaryPanel){
        frames.getLast().add(imaginaryPanel);

        imaginaryPanel.addMouseListener(new EpsilonAiming());
        imaginaryPanel.addKeyListener(new EpsilonMovement());
        imaginaryPanel.addMouseMotionListener(new EpsilonCirculation());
        imaginaryPanel.grabFocus();

        panels.add(imaginaryPanel);
    }

    public static ArrayList<ImaginaryPanel> getPanels() {
        return panels;
    }

    public static void setPanels(ArrayList<ImaginaryPanel> panels) {
        ViewData.panels = panels;
    }

    public static void addFrame(FrameView newFrame) {
        frames.add(newFrame);
    }

    public static void addObject(ObjectView objectView){
        views.add(objectView);
    }

    public static double getTime() {
        return time;
    }

    public static void setTime(double time) {
        ViewData.time = time;
    }

    public static double getHp() {
        return hp;
    }

    public static void setHp(double hp) {
        ViewData.hp = hp;
    }

    public static double getXp() {
        return xp;
    }

    public static void setXp(double xp) {
        ViewData.xp = xp;
    }

    public static int getWave() {
        return wave;
    }

    public static void setWave(int wave) {
        ViewData.wave = wave;
    }



    public static ArrayList<EffectView> getEffectViews() {
        return effectViews;
    }

    public static void setEffectViews(ArrayList<EffectView> effectViews) {
        ViewData.effectViews = effectViews;
    }

    public synchronized static void removeView(String id) {
        for (ObjectView view : views){
            if (view.getId().equals(id)) {
                views.remove(view);
                return;
            }
        }
    }

    public synchronized static void removeFrame(String id) {
        for (int i = 0; i < frames.size(); i++){
            if (frames.get(i).getId().equals(id)){
                frames.get(i).dispose();
                frames.remove(i);
                panels.remove(i);
                return;
            }
        }
    }

    public synchronized static void removeEffect(String id){
        for (int i = 0 ;i < effectViews.size() ;i++){
            if (effectViews.get(i).getId().equals(id)){
                effectViews.remove(i);
                return;
            }
        }
    }

    public static void addEffect(EffectView effectView) {
        effectViews.add(effectView);
    }

    public static ArrayList<AbilityView> getAbilityViews() {
        return abilityViews;
    }

    public static void setAbilityViews(ArrayList<AbilityView> abilityViews) {
        ViewData.abilityViews = abilityViews;
    }

    public static FrameView getEpsilonFrame() {
        return epsilonFrame;
    }

    public static void setEpsilonFrame(FrameView frameView) {
        ViewData.epsilonFrame = frameView;
    }

    public static void addAbilityWithType(int coolDown, int timePassed, boolean isAvailable, SkillTreeAbilityType type) {
        switch (type) {
            case ares :
                ViewData.addAbility(
                        new AresView(coolDown,timePassed,isAvailable)
                );
                break;
            case astrape:
                ViewData.addAbility(
                        new AstrapeView(coolDown,timePassed,isAvailable)
                );
                break;
            case cerberus:
                ViewData.addAbility(
                        new CerberusView(coolDown,timePassed,isAvailable)
                );
                break;
            case aceso:
                ViewData.addAbility(
                        new AcesoView(coolDown,timePassed,isAvailable)
                );
                break;
            case melampus:
                ViewData.addAbility(
                        new MelampusView(coolDown,timePassed,isAvailable)
                );
                break;
            case chiron:
                ViewData.addAbility(
                        new ChironView(coolDown,timePassed,isAvailable)
                );
                break;
            case athena:
                ViewData.addAbility(
                        new AthenaView(coolDown,timePassed,isAvailable)
                );
                break;case proteus:
                ViewData.addAbility(
                        new ProteusView(coolDown,timePassed,isAvailable)
                );
                break;
            case empusa:
                ViewData.addAbility(
                        new EmpusaView(coolDown,timePassed,isAvailable)
                );
                break;
            case dolus:
                ViewData.addAbility(
                        new DolusView(coolDown,timePassed,isAvailable)
                );
                break;
        }
    }

    public static void addAbilityWithType(int coolDown, int timePassed, boolean isAvailable, InGameAbilityType type) {
        if (type == InGameAbilityType.slaughter) {
            addAbility(new SlaughterView(coolDown,timePassed,isAvailable));
        }
        else {
            addAbility(new SlumberView(coolDown,timePassed,isAvailable));
        }
    }

    private static void addAbility(AbilityView abilityView) {
        abilityViews.add(abilityView);
    }
}
