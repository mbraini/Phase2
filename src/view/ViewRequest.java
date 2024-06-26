package view;

import java.util.ArrayList;

public class ViewRequest {

    public static ArrayList<String> removeObjectViewReq = new ArrayList<>();
    public static ArrayList<String> removeFrameViewReq = new ArrayList<>();


    public static void checkRequests(){
        checkObjects();
        checkFrames();
        removeObjectViewReq = new ArrayList<>();
        removeFrameViewReq = new ArrayList<>();
    }

    private static void checkObjects() {
        for (int i = 0 ;i < removeObjectViewReq.size() ;i++){
            ViewData.removeView(removeObjectViewReq.get(i));
        }
    }

    private static void checkFrames() {
        for (int i = 0 ;i < removeFrameViewReq.size() ;i++){
            ViewData.removeFrame(removeFrameViewReq.get(i));
        }
    }

}
