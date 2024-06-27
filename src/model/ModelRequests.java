package model;

import model.objectModel.ObjectModel;
import model.objectModel.frameModel.FrameModel;

import java.util.ArrayList;

public class ModelRequests {

    private static ArrayList<String> removeObjectModelReq = new ArrayList<>();
    private static ArrayList<String> removeFrameModelReq = new ArrayList<>();
    private static ArrayList<ObjectModel> addedObjectModel = new ArrayList<>();
    private static ArrayList<FrameModel> addedFrameModel = new ArrayList<>();

    public static void checkRequests(){
        checkObjects();
        checkFrames();
        addedObjectModel = new ArrayList<>();
        addedFrameModel = new ArrayList<>();
        removeObjectModelReq = new ArrayList<>();
        removeFrameModelReq = new ArrayList<>();
    }

    private static void checkObjects() {
        for (int i = 0; i <addedObjectModel.size() ;i++){
            ModelData.addModel(addedObjectModel.get(i));
        }
        for (int i = 0; i < removeObjectModelReq.size() ;i++){
            ModelData.removeModel(removeObjectModelReq.get(i));
        }
    }

    private static void checkFrames() {
        for (int i = 0; i <addedFrameModel.size() ;i++){
            ModelData.addFrame(addedFrameModel.get(i));
        }
        for (int i = 0 ;i < removeFrameModelReq.size() ;i++){
            ModelData.removeFrame(removeFrameModelReq.get(i));
        }
    }


    public static void addObjectModel(ObjectModel objectModel){
        addedObjectModel.add(objectModel);
    }

    public static void addFrameModel(FrameModel frameModel){
        addedFrameModel.add(frameModel);
    }

    public static void removeObjectModel(String id){
        removeObjectModelReq.add(id);
    }

    public static void removeFrameModel(String id){
        removeFrameModelReq.add(id);
    }

}
