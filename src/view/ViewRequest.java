package view;

import model.objectModel.ObjectModel;
import model.objectModel.frameModel.FrameModel;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;

import java.util.ArrayList;

public class ViewRequest {

    private static ArrayList<String> removeObjectViewReq = new ArrayList<>();
    private static ArrayList<String> removeFrameViewReq = new ArrayList<>();
    private static ArrayList<ObjectView> addedObjectViews = new ArrayList<>();
    private static ArrayList<FrameView> addedFrameViews = new ArrayList<>();

    public static void checkRequests(){
        checkObjects();
        checkFrames();
        addedObjectViews = new ArrayList<>();
        addedFrameViews = new ArrayList<>();
        removeObjectViewReq = new ArrayList<>();
        removeFrameViewReq = new ArrayList<>();
    }

    private static void checkObjects() {
        for (int i = 0; i < addedObjectViews.size() ;i++){
            ViewData.addObject(addedObjectViews.get(i));
        }
        for (int i = 0;i < removeObjectViewReq.size() ;i++){
            ViewData.removeView(removeObjectViewReq.get(i));
        }
    }

    private static void checkFrames() {
        for (int i = 0 ;i < addedFrameViews.size() ;i++){
            ViewData.addFrame(addedFrameViews.get(i));
            ViewData.addImaginaryPanel(new ImaginaryPanel(addedFrameViews.get(i).getId()));
        }
        for (int i = 0 ;i < removeFrameViewReq.size() ;i++){
            ViewData.removeFrame(removeFrameViewReq.get(i));
        }
    }

    public static void addObjectView(ObjectView objectView){
        addedObjectViews.add(objectView);
    }

    public static void addFrameView(FrameView frameView){
        addedFrameViews.add(frameView);
    }

    public static void removeObjectView(String id){
        removeObjectViewReq.add(id);
    }

    public static void removeFrameView(String id){
        removeFrameViewReq.add(id);
    }


}
