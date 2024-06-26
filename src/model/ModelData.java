package model;

import model.objectModel.frameModel.FrameModel;
import model.objectModel.ObjectModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelData {

    private static ArrayList<FrameModel> frames = new ArrayList<>();
    private static ArrayList<ObjectModel> models = new ArrayList<>();
    private static HashMap<ObjectModel ,FrameModel> localFrames = new HashMap<>();

    public static ArrayList<FrameModel> getFrames() {
        return frames;
    }

    public static ArrayList<ObjectModel> getModels() {
        return models;
    }

    public static void setFrames(ArrayList<FrameModel> frames) {
        ModelData.frames = frames;
    }

    public static void setModels(ArrayList<ObjectModel> models) {
        ModelData.models = models;
    }

    public static void addModel(ObjectModel objectModel){
        models.add(objectModel);
    }

    public static void addFrame(FrameModel frameModel){
        frames.add(frameModel);
    }

    public static HashMap<ObjectModel, FrameModel> getLocalFrames() {
        return localFrames;
    }

    public static void setLocalFrames(HashMap<ObjectModel, FrameModel> localFrames) {
        ModelData.localFrames = localFrames;
    }

    public static void removeModel(String id) {
        for (ObjectModel model : models){
            if (model.getId().equals(id)){
                models.remove(model);
                return;
            }
        }
    }

    public static void removeFrame(String id) {
        for (FrameModel frameModel : frames){
            if (frameModel.getId().equals(id)){
                frames.remove(frameModel);
                return;
            }
        }
    }
}
