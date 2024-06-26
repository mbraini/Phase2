package model;

import java.util.ArrayList;

public class ModelRequests {

    public static ArrayList<String> removeObjectModelReq = new ArrayList<>();
    public static ArrayList<String> removeFrameModelReq = new ArrayList<>();

    public static void checkRequests(){
        checkObjects();
        checkFrames();
        removeObjectModelReq = new ArrayList<>();
        removeFrameModelReq = new ArrayList<>();
    }

    private static void checkObjects() {
        for (int i = 0 ;i < removeObjectModelReq.size() ;i++){
            ModelData.removeModel(removeObjectModelReq.get(i));
        }
    }

    private static void checkFrames() {
        for (int i = 0 ;i < removeFrameModelReq.size() ;i++){
            ModelData.removeFrame(removeFrameModelReq.get(i));
        }
    }

}
