package controller.manager.loading;

import com.google.gson.*;
import controller.enums.AbstractEnemyType;
import controller.enums.InGameAbilityType;
import controller.enums.ModelType;
import controller.enums.SkillTreeAbilityType;
import controller.manager.Spawner;
import model.ModelData;
import model.inGameAbilities.InGameAbility;
import model.objectModel.fighters.miniBossEnemies.blackOrbModel.BlackOrbModel;
import model.objectModel.frameModel.FrameModel;
import model.skillTreeAbilities.SkillTreeAbility;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import view.ViewRequest;
import view.objectViews.FrameView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoader {

    private static ArrayList<FrameModel> framesSpawnedByObjects;
    private static Gson gson;

    public GameLoader(){
        framesSpawnedByObjects = new ArrayList<>();
    }

    public synchronized void load() {
        loadGame();
        loadAbilities();
        loadSkillTree();
    }

    private void loadSkillTree() {
        gson = getGson();

        StringBuilder skillTreeString = new StringBuilder();
        try {
            Scanner abilityScanner = new Scanner(new File("src/controller/manager/saving/skillTree.json"));
            while (abilityScanner.hasNextLine())
                skillTreeString.append(abilityScanner.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<SkillTreeAbility> abilities;
        try {
            JSONArray jAbilities = (JSONArray) new JSONTokener(skillTreeString.toString()).nextValue();
            for (int i = 0; i <jAbilities.length() ;i++){
                JSONObject jAbility = jAbilities.getJSONObject(i);
                String jType = jAbility.get("type").toString();
                SkillTreeAbilityType type = gson.fromJson(jType , SkillTreeAbilityType.class);
                GameLoaderHelper.addSkillTree(jAbility ,type);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAbilities() {
        gson = getGson();

        StringBuilder abilityString = new StringBuilder();
        try {
            Scanner abilityScanner = new Scanner(new File("src/controller/manager/saving/abilities.json"));
            while (abilityScanner.hasNextLine())
                abilityString.append(abilityScanner.nextLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<InGameAbility> abilities;
        try {
            JSONArray jAbilities = (JSONArray) new JSONTokener(abilityString.toString()).nextValue();
            for (int i = 0; i <jAbilities.length() ;i++){
                JSONObject jAbility = jAbilities.getJSONObject(i);
                String jType = jAbility.get("type").toString();
                InGameAbilityType type = gson.fromJson(jType , InGameAbilityType.class);
                GameLoaderHelper.addAbility(jAbility ,type);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadGame() {
        gson = getGson();

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
                if (i == 0){
                    ModelData.addFrame(frameModel);
                    ViewRequest.addFrameView(new FrameView(
                            frameModel.getPosition(),
                            frameModel.getSize(),
                            frameModel.getId()
                    ));
                    continue;
                }
                if (!spawnedByObjects(frameModel.getId()))
                    Spawner.addFrame(frameModel);
            }
            for (FrameModel frameModel : framesSpawnedByObjects)
                Spawner.addFrame(frameModel);
            JSONArray jAbstractEnemies = (JSONArray) new JSONTokener(abstractEnemyString.toString()).nextValue();
            for (int i = 0; i < jAbstractEnemies.length() ; i++){/////////////only one frame!
                JSONObject jAbstract = jAbstractEnemies.getJSONObject(i);
                String jType = jAbstract.get("type").toString();
                AbstractEnemyType type = gson.fromJson(jType , AbstractEnemyType.class);
                GameLoaderHelper.addBlackOrb(
                        gson.fromJson(jAbstract.toString() , BlackOrbModel.class) ,
                        jAbstract
                );
            }
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
        builder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.getAnnotation(SkippedByJson.class) != null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                if (aClass.getAnnotation(SkippedByJson.class) == null)
                    return false;
                return true;
            }
        });
        return builder.create();
    }

    public static void addFrame(FrameModel frameModel){
        framesSpawnedByObjects.add(frameModel);
    }


}
