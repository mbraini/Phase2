package model.viewRequests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import constants.Constants;
import controller.configs.Configs;
import controller.configs.helper.SkillTreeJsonHelper;
import controller.enums.SkillTreeAbilityType;
import controller.manager.GameState;
import model.skillTreeAbilities.SkillTreeAbility;
import model.skillTreeAbilities.SkillTreeAbilityHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SkillTreeAbilityRequests {

    public static void abilityRequest(SkillTreeAbilityType type) {
        if (canUse(type)) {
            GameState.setXp(GameState.getXp() - SkillTreeAbilityHandler.getAbility(type).getInGameXpCost());
            SkillTreeAbilityHandler.activateSkillTreeAbility(type);
        }
    }

    private static boolean canUse(SkillTreeAbilityType type) {
        SkillTreeAbility ability = SkillTreeAbilityHandler.getAbility(type);
        if (ability.isBought() && ability.CanCast() && GameState.getXp() >= ability.getInGameXpCost()){
            return true;
        }
        return false;
    }

    public static void buyRequest(SkillTreeAbilityType type) {

        if (canBuy(type) > 0) {
            buy(type ,canBuy(type));
        }

    }

    private static void buy(SkillTreeAbilityType type ,int cost) {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        SkillTreeJsonHelper helper;
        try {
            scanner = new Scanner(new File("src/controller/configs/skillTree.json"));
            while (scanner.hasNextLine())
                stringBuilder.append(scanner.nextLine());
            scanner.close();
            helper = gson.fromJson(stringBuilder.toString() ,SkillTreeJsonHelper.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        GameState.setXp(GameState.getXp() - cost);
        switch (type) {
            case ares :
                helper.ares = true;
                Configs.SkillTreeConfigs.aresBought = true;
                break;
            case astrape:
                helper.astrape = true;
                Configs.SkillTreeConfigs.astrapeBought = true;
                break;
            case cerberus:
                helper.cerberus = true;
                Configs.SkillTreeConfigs.cerberusBought = true;
                break;
            case aceso:
                helper.aceso = true;
                Configs.SkillTreeConfigs.acesoBought = true;
                break;
            case melampus:
                helper.melampus = true;
                Configs.SkillTreeConfigs.melampusBought = true;
                break;
            case chiron:
                helper.chiron = true;
                Configs.SkillTreeConfigs.chironBought = true;
                break;
            case athena:
                helper.athena = true;
                Configs.SkillTreeConfigs.athenaBought = true;
                break;
            case proteus:
                helper.proteus = true;
                Configs.SkillTreeConfigs.proteusBought = true;
                break;
            case empusa:
                helper.empusa = true;
                Configs.SkillTreeConfigs.empusaBought = true;
                break;
            case dolus:
                helper.dolus = true;
                Configs.SkillTreeConfigs.dolusBought = true;
                break;
        }
        String json = gson.toJson(helper);
        try {
            PrintWriter printWriter = new PrintWriter("src/controller/configs/skillTree.json");
            printWriter.write(json);
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int canBuy(SkillTreeAbilityType type) {
        switch (type) {
            case ares :
                if (GameState.getXp() >= Constants.ARES_UNLOCK_COST)
                    return Constants.ARES_UNLOCK_COST;
                break;
            case astrape:
                if (GameState.getXp() >= Constants.ASTRAPE_UNLOCK_COST && Configs.SkillTreeConfigs.aresBought)
                    return Constants.ASTRAPE_UNLOCK_COST;
                break;
            case cerberus:
                if (GameState.getXp() >= Constants.CERBERUS_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.aresBought && Configs.SkillTreeConfigs.astrapeBought)
                        return Constants.CERBERUS_UNLOCK_COST;
                }
                break;
            case aceso:
                if (GameState.getXp() >= Constants.ACESO_UNLOCK_COST)
                    return Constants.ACESO_UNLOCK_COST;
                break;
            case melampus:
                if (GameState.getXp() >= Constants.MELAMPUS_UNLOCK_COST && Configs.SkillTreeConfigs.acesoBought)
                    return Constants.MELAMPUS_UNLOCK_COST;
                break;
            case chiron:
                if (GameState.getXp() >= Constants.CHIRON_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.acesoBought && Configs.SkillTreeConfigs.melampusBought)
                        return Constants.CHIRON_UNLOCK_COST;
                }
                break;
            case athena:
                if (GameState.getXp() >= Constants.ATHENA_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.acesoBought && Configs.SkillTreeConfigs.melampusBought)
                        return Constants.ATHENA_UNLOCK_COST;
                }
                break;
            case proteus:
                if (GameState.getXp() >= Constants.PROTEUS_UNLOCK_COST)
                    return Constants.PROTEUS_UNLOCK_COST;
                break;
            case empusa:
                if (GameState.getXp() >= Constants.EMPUSA_UNLOCK_COST && Configs.SkillTreeConfigs.proteusBought)
                    return Constants.EMPUSA_UNLOCK_COST;
                break;
            case dolus:
                if (GameState.getXp() >= Constants.DOLUS_UNLOCK_COST) {
                    if (Configs.SkillTreeConfigs.proteusBought && Configs.SkillTreeConfigs.empusaBought)
                        return Constants.DOLUS_UNLOCK_COST;
                }
                break;
        }
        return -1;
    }
}
