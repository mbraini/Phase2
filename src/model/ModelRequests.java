package model;

import model.objectModel.ObjectModel;
import model.objectModel.EffectModel;
import model.objectModel.frameModel.FrameModel;

import java.util.ArrayList;

public class ModelRequests {

    private static ArrayList<String> removeObjectModelReq = new ArrayList<>();
    private static ArrayList<String> removeFrameModelReq = new ArrayList<>();
    private static ArrayList<String> removeEffectModelReq = new ArrayList<>();
    private static ArrayList<EffectModel> addedEffectModel = new ArrayList<>();
    private static ArrayList<ObjectModel> addedObjectModel = new ArrayList<>();
    private static ArrayList<FrameModel> addedFrameModel = new ArrayList<>();

    public static void checkRequests(){
        checkObjects();
        checkFrames();
        checkEffects();
    }

    private static void checkEffects() {
        for (int i = 0 ;i < addedEffectModel.size() ;i++){
            ModelData.addEffect(addedEffectModel.get(i));
            addedEffectModel.remove(i);
            i--;
        }
        for (int i = 0 ;i < removeEffectModelReq.size() ;i++){
            ModelData.removeEffect(removeEffectModelReq.get(i));
            removeEffectModelReq.remove(i);
            i--;
        }
    }

    private static void checkObjects() {
        for (int i = 0; i <addedObjectModel.size() ;i++){
            ModelData.addModel(addedObjectModel.get(i));
            addedObjectModel.remove(i);
            i--;
        }
        for (int i = 0; i < removeObjectModelReq.size() ;i++){
            ModelData.removeModel(removeObjectModelReq.get(i));
            removeObjectModelReq.remove(i);
            i--;
        }
    }

    private static void checkFrames() {
        for (int i = 0; i <addedFrameModel.size() ;i++){
            ModelData.addFrame(addedFrameModel.get(i));
            addedFrameModel.remove(i);
            i--;
        }
        for (int i = 0 ;i < removeFrameModelReq.size() ;i++){
            ModelData.removeFrame(removeFrameModelReq.get(i));
            removeFrameModelReq.remove(i);
            i--;
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

    public static void addEffectModel(EffectModel effectModel){
        addedEffectModel.add(effectModel);
    }

    public static void removeEffectModel(String id){
        removeEffectModelReq.add(id);
    }


}
