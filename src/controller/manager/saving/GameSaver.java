package controller.manager.saving;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.objectModel.ObjectModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.fighters.AbstractEnemy;
import model.objectModel.frameModel.FrameModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameSaver {

    private ArrayList<FrameModel> frames;
    private ArrayList<ObjectModel> models;
    private ArrayList<AbstractEnemy> abstractEnemies;
    private ArrayList<EffectModel> effects;
    private static Gson gson;

    public GameSaver(ArrayList<ObjectModel> models, ArrayList<EffectModel> effects,
                     ArrayList<FrameModel> frames, ArrayList<AbstractEnemy> abstractEnemies)
    {
        this.models = models;
        this.effects = effects;
        this.frames = frames;
        this.abstractEnemies = abstractEnemies;
    }


    public void save() {
        Gson gson = getGson();

        String modelString = gson.toJson(models);
        String frameString = gson.toJson(frames);
        String abstractEnemyString = gson.toJson(abstractEnemies);
        String effectString = gson.toJson(effects);

        try {
            PrintWriter modelWriter = new PrintWriter("src/controller/manager/saving/models.json");
            modelWriter.write(modelString);
            modelWriter.close();
            PrintWriter frameWriter = new PrintWriter("src/controller/manager/saving/frames.json");
            frameWriter.write(frameString);
            frameWriter.close();
            PrintWriter abstractEnemyWriter = new PrintWriter(
                    "src/controller/manager/saving/abstractEnemies.json"
            );
            abstractEnemyWriter.write(abstractEnemyString);
            abstractEnemyWriter.close();
            PrintWriter effectWriter = new PrintWriter("src/controller/manager/saving/effects.json");
            effectWriter.write(effectString);
            effectWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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
}
