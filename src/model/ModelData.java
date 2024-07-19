package model;

import controller.enums.InGameAbilityType;
import model.inGameAbilities.*;
import model.inGameAbilities.Dismay.Dismay;
import model.objectModel.effects.EffectModel;
import model.objectModel.fighters.AbstractEnemy;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.ObjectModel;
import model.skillTreeAbilities.SkillTreeAbility;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ModelData {

    private ArrayList<FrameModel> frames;
    private ArrayList<ObjectModel> models;
    private HashMap<ObjectModel ,FrameModel> localFrames;
    private ArrayList<EffectModel> effectModels;
    private ArrayList<AbstractEnemy> abstractEnemies;
    private ArrayList<InGameAbility> inGameAbilities;
    private ArrayList<SkillTreeAbility> skillTreeAbilities;
    public  ArrayList<AbstractEnemy> getAbstractEnemies() {
        return abstractEnemies;
    }
    private EpsilonModel epsilon;
    private FrameModel epsilonFrame;

    public void resetAll() {
        frames = new ArrayList<>();
        models = new ArrayList<>();
        localFrames = new HashMap<>();
        effectModels = new ArrayList<>();
        abstractEnemies = new ArrayList<>();
        inGameAbilities = new ArrayList<>();
        skillTreeAbilities = new ArrayList<>();
    }


    public void setAbstractEnemies(ArrayList<AbstractEnemy> abstractEnemies) {
        this.abstractEnemies = abstractEnemies;
    }

    public ArrayList<FrameModel> getFrames() {
        return frames;
    }

    public ArrayList<ObjectModel> getModels() {
        return models;
    }

    public void setFrames(ArrayList<FrameModel> frames) {
        this.frames = frames;
    }

    public void setModels(ArrayList<ObjectModel> models) {
        this.models = models;
    }

    public void addModel(ObjectModel objectModel){
        models.add(objectModel);
    }

    public void addFrame(FrameModel frameModel){
        frames.add(frameModel);
    }

    public HashMap<ObjectModel, FrameModel> getLocalFrames() {
        return localFrames;
    }

    public void setLocalFrames(HashMap<ObjectModel, FrameModel> localFrames) {
        this.localFrames = localFrames;
    }

    public ArrayList<EffectModel> getEffectModels() {
        return effectModels;
    }

    public void setEffectModels(ArrayList<EffectModel> effectModels) {
        this.effectModels = effectModels;
    }

    public void removeModel(String id) {
        for (ObjectModel model : models){
            if (model.getId().equals(id)){
                models.remove(model);
                return;
            }
        }
    }

    public void removeFrame(String id) {
        for (FrameModel frameModel : frames){
            if (frameModel.getId().equals(id)){
                frames.remove(frameModel);
                return;
            }
        }
    }

    public void removeEffect(String id){
        for (EffectModel effectModel : effectModels){
            if (effectModel.getId().equals(id)){
                effectModels.remove(effectModel);
                return;
            }
        }
    }

    public void addEffect(EffectModel effectModel) {
        effectModels.add(effectModel);
    }

    public void removeAbstractEnemy(String id){
        for (AbstractEnemy abstractEnemy : abstractEnemies){
            if (abstractEnemy.getId().equals(id)){
                abstractEnemies.remove(abstractEnemy);
                return;
            }
        }
    }

    public void addAbstractEnemy(AbstractEnemy abstractEnemy){
        abstractEnemies.add(abstractEnemy);
    }

    public ArrayList<InGameAbility> getInGameAbilities() {
        return inGameAbilities;
    }

    public void setInGameAbilities(ArrayList<InGameAbility> inGameAbilities) {
        this.inGameAbilities = inGameAbilities;
    }

    public ArrayList<SkillTreeAbility> getSkillTreeAbilities() {
        return skillTreeAbilities;
    }

    public void setSkillTreeAbilities(ArrayList<SkillTreeAbility> skillTreeAbilities) {
        this.skillTreeAbilities = skillTreeAbilities;
    }

    public EpsilonModel getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(EpsilonModel epsilon) {
        this.epsilon = epsilon;
    }

    public FrameModel getEpsilonFrame() {
        return epsilonFrame;
    }

    public void setEpsilonFrame(FrameModel epsilonFrame) {
        this.epsilonFrame = epsilonFrame;
    }
}
