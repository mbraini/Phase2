package controller.manager.saving;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.manager.GameState;
import controller.manager.WaveSpawner;
import controller.manager.loading.SkippedByJson;
import model.inGameAbilities.InGameAbility;
import model.objectModel.ObjectModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.fighters.AbstractEnemy;
import model.objectModel.frameModel.FrameModel;
import model.skillTreeAbilities.SkillTreeAbility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GameSaver {

    private ArrayList<FrameModel> frames;
    private ArrayList<ObjectModel> models;
    private ArrayList<AbstractEnemy> abstractEnemies;
    private ArrayList<EffectModel> effects;
    private ArrayList<InGameAbility> abilities;
    private ArrayList<SkillTreeAbility> skillTreeAbilities;
    private String path;
    private static Gson gson;

    public GameSaver(ArrayList<ObjectModel> models, ArrayList<EffectModel> effects,
                     ArrayList<FrameModel> frames, ArrayList<AbstractEnemy> abstractEnemies,
                     ArrayList<InGameAbility> abilities ,ArrayList<SkillTreeAbility> skillTreeAbilities,
                     String path
    )
    {
        this.models = (ArrayList<ObjectModel>) models.clone();
        this.effects = (ArrayList<EffectModel>) effects.clone();
        this.frames = (ArrayList<FrameModel>) frames.clone();
        this.abstractEnemies = (ArrayList<AbstractEnemy>) abstractEnemies.clone();
        this.abilities = (ArrayList<InGameAbility>) abilities.clone();
        this.skillTreeAbilities = (ArrayList<SkillTreeAbility>) skillTreeAbilities.clone();
        this.path = path;
    }


    public synchronized void save() {
        saveGame();
        saveAbilities();
        saveSkillTree();
        saveGameState();
    }

    private void saveGameState() {
        GameManagerHelperSaver gameState = new GameManagerHelperSaver();
        gameState.time = GameState.getTime();
        gameState.xp = GameState.getXp();
        gameState.hp = GameState.getHp();
        gameState.wave = GameState.getWave();
        gameState.enemyKilled = GameState.getEnemyKilled();
        gameState.totalBullets = GameState.getTotalBullets();
        gameState.successfulBullets = GameState.getSuccessfulBullets();
        gameState.enemyCount = GameState.getEnemyCount();
        gameState.isPause = GameState.isPause();
        gameState.isOver = GameState.isOver();
        gameState.repeatedCount = WaveSpawner.repeatedCount;
        gameState.isDizzy = GameState.isDizzy();

        Gson gson = getGson();
        String gameStateString = gson.toJson(gameState);
        PrintWriter modelWriter = null;
        try {
            modelWriter = new PrintWriter(path + "/gameState.json");
            modelWriter.write(gameStateString);
            modelWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveSkillTree() {
        Gson gson = getGson();
        String skillTreeString = gson.toJson(skillTreeAbilities);
        PrintWriter modelWriter = null;
        try {
            modelWriter = new PrintWriter(path + "/skillTree.json");
            modelWriter.write(skillTreeString);
            modelWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveAbilities() {
        Gson gson = getGson();
        String abilityString = gson.toJson(abilities);
        PrintWriter modelWriter = null;
        try {
            modelWriter = new PrintWriter(path + "/abilities.json");
            modelWriter.write(abilityString);
            modelWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveGame() {
        Gson gson = getGson();

        String modelString = gson.toJson(models);
        String frameString = gson.toJson(frames);
        String abstractEnemyString = gson.toJson(abstractEnemies);
        String effectString = gson.toJson(effects);

        try {
            PrintWriter modelWriter = new PrintWriter(path + "/models.json");
            modelWriter.write(modelString);
            modelWriter.close();
            PrintWriter frameWriter = new PrintWriter(path + "/frames.json");
            frameWriter.write(frameString);
            frameWriter.close();
            PrintWriter abstractEnemyWriter = new PrintWriter(
                    path + "/abstractEnemies.json"
            );
            abstractEnemyWriter.write(abstractEnemyString);
            abstractEnemyWriter.close();
            PrintWriter effectWriter = new PrintWriter(path + "/effects.json");
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
        gson = builder.create();
        return gson;
    }

    public static boolean isPortalSaved() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/controller/manager/saving/portalSaved/models.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        if (stringBuilder.toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isGameSaved() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/controller/manager/saving/inGameSaved/models.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        if (stringBuilder.toString().isEmpty()) {
            return false;
        }
        return true;
    }


}
