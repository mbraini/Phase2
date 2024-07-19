package view;

import model.objectModel.ObjectModel;
import model.objectModel.frameModel.FrameModel;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;

import java.util.ArrayList;

public class ViewRequest {

    private static ArrayList<String> removeObjectViewReq;
    private static ArrayList<String> removeFrameViewReq;
    private static ArrayList<String> removeEffectViewReq;
    private static ArrayList<EffectView> addedEffectViews;
    private static ArrayList<ObjectView> addedObjectViews;
    private static ArrayList<FrameView> addedFrameViews;
    private static boolean endRequest;

    public static void resetAll() {
        removeObjectViewReq = new ArrayList<>();
        removeFrameViewReq = new ArrayList<>();
        removeEffectViewReq = new ArrayList<>();
        addedEffectViews = new ArrayList<>();
        addedObjectViews = new ArrayList<>();
        addedFrameViews = new ArrayList<>();
    }

    public static void checkRequests(){
        if (endRequest) {
            endRequest = false;
            resetAll();
            ViewData.resetAll();
        }
        checkObjects();
        checkFrames();
        checkEffects();
    }

    private synchronized static void checkObjects() {
        for (int i = 0; i < addedObjectViews.size() ;i++){
            ViewData.addObject(addedObjectViews.get(i));
            addedObjectViews.remove(i);
            i--;
        }
        for (int i = 0;i < removeObjectViewReq.size() ;i++){
            ViewData.removeView(removeObjectViewReq.get(i));
            removeObjectViewReq.remove(i);
            i--;
        }
    }

    private synchronized static void checkFrames() {
        for (int i = 0 ;i < addedFrameViews.size() ;i++){
            ViewData.addFrame(addedFrameViews.get(i));
            ViewData.addImaginaryPanel(new ImaginaryPanel(addedFrameViews.get(i).getId()));
            addedFrameViews.remove(i);
            i--;
        }
        for (int i = 0 ;i < removeFrameViewReq.size() ;i++){
            ViewData.removeFrame(removeFrameViewReq.get(i));
            removeFrameViewReq.remove(i);
            i--;
        }
    }

    private synchronized static void checkEffects(){
        for (int i = 0 ;i < addedEffectViews.size() ;i++){
            ViewData.addEffect(addedEffectViews.get(i));
            addedEffectViews.remove(i);
            i--;
        }
        for (int i = 0 ;i < removeEffectViewReq.size() ;i++){
            ViewData.removeEffect(removeEffectViewReq.get(i));
            removeEffectViewReq.remove(i);
            i--;
        }
    }

    public synchronized static void addObjectView(ObjectView objectView){
        addedObjectViews.add(objectView);
    }

    public synchronized static void addFrameView(FrameView frameView){
        addedFrameViews.add(frameView);
    }

    public synchronized static void removeObjectView(String id){
        removeObjectViewReq.add(id);
    }

    public synchronized static void removeFrameView(String id){
        removeFrameViewReq.add(id);
    }

    public synchronized static void addEffectView(EffectView effectView){
        addedEffectViews.add(effectView);
    }

    public synchronized static void removeEffectView(String id){
        removeEffectViewReq.add(id);
    }

    public synchronized static void endRequest() {
        endRequest = true;
    }


}
