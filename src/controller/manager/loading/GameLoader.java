package controller.manager.loading;

import com.google.gson.*;
import controller.enums.ModelType;
import controller.manager.Spawner;
import model.objectModel.frameModel.FrameModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoader {

    private ArrayList<Object> frames;
    private ArrayList<Object> models;
    private ArrayList<Object> abstractEnemies;
    private ArrayList<Object> effects;
    private GameLoaderHelper helper;
    private static ArrayList<FrameModel> framesSpawnedByObjects;
    private static Gson gson;

    public GameLoader(){
        models = new ArrayList<>();
        frames = new ArrayList<>();
        abstractEnemies = new ArrayList<>();
        effects = new ArrayList<>();
        framesSpawnedByObjects = new ArrayList<>();
    }

    public synchronized void load() {

        Gson gson = getGson();

        StringBuilder modelString = new StringBuilder();
        StringBuilder frameString = new StringBuilder();
        StringBuilder abstractEnemyString = new StringBuilder();
        StringBuilder effectString = new StringBuilder();
        try {
            Scanner modelScanner = new Scanner(new File("src/controller/manager/saving/models.json"));
            Scanner frameScanner = new Scanner(new File("src/controller/manager/saving/frames.json"));
            Scanner abstractEnemyScanner = new Scanner(new File("src/controller/manager/saving/abstractEnemies.json"));
            Scanner effectScanner = new Scanner(new File("src/controller/manager/saving/effects.json"));
            while (modelScanner.hasNextLine()){
                modelString.append(modelScanner.nextLine());
            }
            while (frameScanner.hasNextLine()){
                frameString.append(frameScanner.nextLine());
            }
            while (abstractEnemyScanner.hasNextLine()){
                abstractEnemyString.append(abstractEnemyScanner.nextLine());
            }
            while (effectScanner.hasNextLine()){
                effectString.append(effectScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            JSONArray jModels = (JSONArray) new JSONTokener(modelString.toString()).nextValue();
            for (int i = 0; i <jModels.length() ;i++){
                JSONObject jModel = jModels.getJSONObject(i);
                String jType = jModel.get("type").toString();
                ModelType type = gson.fromJson(jType , ModelType.class);
                GameLoaderHelper.addModel(jModel ,type);
            }
            JSONArray jFrames = (JSONArray) new JSONTokener(frameString.toString()).nextValue();
            for (int i = 0; i < jFrames.length() ;i++){/////////////only one frame!
                JSONObject jModel = jFrames.getJSONObject(i);
                FrameModel frameModel = gson.fromJson(jModel.toString() , FrameModel.class);
                if (!spawnedByObjects(frameModel.getId()))
                    Spawner.addFrame(frameModel);
            }
            for (FrameModel frameModel : framesSpawnedByObjects)
                Spawner.addFrame(frameModel);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean spawnedByObjects(String id) {
        for (FrameModel frameModel : framesSpawnedByObjects){
            if (frameModel.getId().equals(id))
                return true;
        }
        return false;
    }

    private Gson getGson() {
        if (gson != null)
            return gson;
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeNulls();
        gson = builder.create();
        return gson;
    }

    public static void addFrame(FrameModel frameModel){
        framesSpawnedByObjects.add(frameModel);
    }


}
