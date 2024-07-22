package controller;

import controller.enums.InGameAbilityType;
import controller.enums.SkillTreeAbilityType;
import controller.interfaces.SizeChanger;
import controller.listeners.keyHelper.KeyHelper;
import controller.manager.GameManager;
import controller.manager.GameManagerThread;
import controller.manager.loading.GameLoader;
import constants.Constants;
import controller.manager.saving.GameSaver;
import model.ModelRequests;
import model.animations.GameStartAnimation;
import model.inGameAbilities.InGameAbilityHandler;
import model.interfaces.ImageChanger;
import model.objectModel.PortalModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.frameModel.FrameModelBuilder;
import model.skillTreeAbilities.SkillTreeAbilityHandler;
import model.threads.FrameThread;
import model.threads.GameLoop;
import controller.manager.GameState;
import model.ModelData;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.ObjectModel;
import model.viewRequests.ShootRequest;
import model.viewRequests.InGameAbilityRequests;
import model.viewRequests.SkillTreeAbilityRequests;
import utils.Helper;
import utils.Vector;
import view.ViewRequest;
import view.ViewData;
import view.gamePanels.*;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;
import view.painter.Render;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Controller {
    private static Render render;
    private static GameLoop gameLoop;
    private static FrameThread frameThread;
    private static GameManager gameManager;
    private static PortalModel portalModel;
    private static GameMode gameMode;

    public static void updateView(){
        synchronized (ModelData.getModels()) {

            //////// syncronize them later !

            ArrayList<ObjectView> objectViews = (ArrayList<ObjectView>) ViewData.getViews().clone();
            ArrayList<FrameView> frameViews = (ArrayList<FrameView>) ViewData.getFrames().clone();
            ArrayList<ObjectModel> objectModels = (ArrayList<ObjectModel>) ModelData.getModels().clone();
            ArrayList<FrameModel> frameModels = (ArrayList<FrameModel>) ModelData.getFrames().clone();
            ArrayList<EffectView> effectViews = (ArrayList<EffectView>) ViewData.getEffectViews().clone();
            ArrayList<EffectModel> effectModels = (ArrayList<EffectModel>) ModelData.getEffectModels().clone();
            HashMap<ObjectModel ,FrameModel> locals =
                    (HashMap<ObjectModel, FrameModel>) ModelData.getLocalFrames().clone();

            ///////////////

            try {
                for (int i = 0; i < frameViews.size(); i++) {
                    int index = -1;
                    for (int j = 0; j < frameModels.size(); j++) {
                        if (frameModels.get(j).getId().equals(frameViews.get(i).getId()))
                            index = j;
                    }
                    if (index == -1)
                        continue;

                    FrameModel frame = frameModels.get(index);
                    frameViews.get(i).setPosition(frame.getPosition());
                    frameViews.get(i).setDimension(
                            new Dimension(
                                    frame.getSize().width + Constants.barD.width,
                                    frame.getSize().height + Constants.barD.height
                            )
                    );
                    if (locals.get(objectModels.getFirst()) == frame){
                        ViewData.setEpsilonFrame(frameViews.get(i));
                    }
                }

                for (int i = 0; i < objectViews.size(); i++) {
                    int index = -1;
                    for (int j = 0; j < objectModels.size(); j++) {
                        if (objectModels.get(j).getId().equals(objectViews.get(i).getId()))
                            index = j;
                    }
                    if (index == -1)
                        continue;
                    ObjectModel model = objectModels.get(index);
                    objectViews.get(i).setPosition(model.getPosition());
                    objectViews.get(i).setTheta(model.getTheta());
                    objectViews.get(i).setHovering(model.isHovering());
                    if (model instanceof ImageChanger)
                        objectViews.get(i).setImage(((ImageChanger) model).getImage());
                    if (model instanceof SizeChanger && objectViews.get(i) instanceof SizeChanger)
                        ((SizeChanger) objectViews.get(i)).setSize(((SizeChanger) model).getSize());
                }

                for (int i = 0; i < effectViews.size(); i++) {
                    int index = -1;
                    for (int j = 0; j < effectModels.size(); j++) {
                        if (effectModels.get(j).getId() == null)
                            continue;
                        if (effectModels.get(j).getId().equals(effectViews.get(i).getId()))
                            index = j;
                    }
                    if (index == -1)
                        continue;
                    EffectModel effectModel = effectModels.get(index);
                    effectViews.get(i).setArea(effectModel.getArea());
                    effectViews.get(i).setTheta(effectModel.getTheta());
                    effectViews.get(i).setColor(new Color(
                            effectModel.getR(),
                            effectModel.getG(),
                            effectModel.getB()
                    ));
                }


                ViewData.setViews(objectViews);
                ViewData.setFrames(frameViews);
                setVariables();
            }
            catch (Exception e){
                System.out.println("update view exeption!");
            }
        }
    }

    public synchronized static void removeObject(ObjectModel model) {
        ModelRequests.removeObjectModel(model.getId());
        ViewRequest.removeObjectView(model.getId());
    }

    public static void sendViewUpdates() {

    }

    public synchronized static void removeEffect(EffectModel effectModel) {
        ModelRequests.removeEffectModel(effectModel.getId());
        ViewRequest.removeEffectView(effectModel.getId());
    }

    public static void removeFrame(FrameModel frameModel) {
        ModelRequests.removeFrameModel(frameModel.getId());
        ViewRequest.removeFrameView(frameModel.getId());
    }

    public static void resume() {
        GameState.setPause(false);
    }

    public static void pause() {
        GameState.setPause(true);
    }

    public static void randomizeKeys() {
        KeyHelper.randomize();
    }

    public static void reorderKeys() {
        KeyHelper.reorder();
    }

    public static void portalWindow() {
        pause();
        Controller.removeObject(portalModel);
        int totalPR = GameState.getAllPR();
        int PR = (int) (totalPR * GameState.getXpGained() * 10 / GameState.getHp());
        new PortalPanel(new PortalFrame() ,PR).start();
    }

    private static void removePortal() {

    }

    public static boolean addXP(int pr) {
        GameState.setXp(GameState.getXp() + pr);
        if (pr > 0) {
            GameState.setXpGained(GameState.getXpGained() + pr);
        }
        if (GameState.getXp() < 0) {
            GameState.setXp(GameState.getXp() - pr);
            return false;
        }
        return true;
    }

    public static void saveGameInPortal() {
        new GameSaver(
                ModelData.getModels(),
                ModelData.getEffectModels(),
                ModelData.getFrames(),
                ModelData.getAbstractEnemies(),
                ModelData.getInGameAbilities(),
                ModelData.getSkillTreeAbilities(),
                "src/controller/manager/saving/portalSaved"
        ).save();
    }


    private void updateObjectViews(){

    }

    private static void setVariables(){
        ViewData.setTime(GameState.getTime());
        ViewData.setHp(GameState.getHp());
        ViewData.setXp(GameState.getXp());
        ViewData.setWave(GameState.getWave());
    }


    public static void startGame(){
//        gameMode = GameMode.inGame;
//        if (GameSaver.isGameSaved()) {
//            GameState.reset();
//            modelStarter();
//            viewStarter();
//            new GameLoader("src/controller/manager/saving/inGameSaved").load();
//            Controller.threadsStarter();
//        }
//        else {
            GameState.setXp(100000);
            GameState.reset();
            modelStarter();
            viewStarter();
            addEpsilonAndFrame();
//            new GameStartAnimation(ModelData.getFrames().getFirst()).StartAnimation();
            InGameAbilityHandler.initInGameAbilities();
            SkillTreeAbilityHandler.initAbilities();
            Controller.threadsStarter();
//        }
    }

    public static void endGame() {
        endRequest();
        if (GameSaver.isPortalSaved()) {
            gameMode = GameMode.portal;
            new GameLoader("src/controller/manager/saving/portalSaved").load();
            Helper.resetAllJsons("src/controller/manager/saving/portalSaved");
            GameState.setOver(false);
            GameState.setPause(false);
            GameState.setDizzy(false);
            ModelData.getEpsilon().setHP(10);
            threadsStarter();
        }
        else {
            new EndGamePanel(new EndGameFrame()).start();
        }
    }

    private static void endRequest() {
        GameState.setOver(true);
        render.interrupt();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        GameState.reset();
        ModelRequests.endRequest();
        ViewRequest.endRequest();
        Helper.resetAllJsons("src/controller/manager/saving/inGameSaved");
    }


    private static void addEpsilonAndFrame() {
        EpsilonModel epsilon = new EpsilonModel(
                new Vector(Constants.SCREEN_SIZE.width / 2d ,
                        Constants.SCREEN_SIZE.height / 2d
                )
                ,Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        ModelData.addModel(epsilon);
        ModelData.setEpsilon(epsilon);
        ViewData.addObject(new EpsilonView(epsilon.getPosition() ,epsilon.getId()));
        FrameModelBuilder builder = new FrameModelBuilder(
                new Vector(
                        Constants.SCREEN_SIZE.width / 2d - Constants.GAME_WIDTH / 2d ,
                        Constants.SCREEN_SIZE.height / 2d - Constants.GAME_HEIGHT / 2d
                ),
                new Dimension(Constants.GAME_WIDTH ,Constants.GAME_HEIGHT),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        builder.setSolid(true);
        FrameModel frameModel = builder.create();
        ModelData.addFrame(frameModel);
        ModelData.setEpsilonFrame(frameModel);
        ViewData.addFrame(new FrameView(
                frameModel.getPosition(),
                frameModel.getSize(),
                frameModel.getId())
        );
        ViewData.addImaginaryPanel(new ImaginaryPanel(frameModel.getId()));
    }

    public static void threadsStarter() {
        frameThread = new FrameThread();
        gameLoop = new GameLoop();
        render = new Render();
        render.start();
        gameManager = new GameManager();
        frameThread.start();
        gameLoop.start();
        gameManager.getGameManager().start();
    }

    private static void controllerStarter() {
        /////todo
    }

    private static void modelStarter() {
        ModelData.resetAll();
        ModelRequests.resetAll();
    }

    private static void viewStarter() {
        ViewData.resetAll();
        ViewRequest.resetAll();
    }

    public static void setUpManager(){
        new GameManager();
    }



    public static boolean shootRequest(Vector clickedPoint){
        if (ShootRequest.canShoot()){
            new ShootRequest(ModelData.getEpsilon()).shoot(clickedPoint);
            return true;
        }
        return false;
    }

    public static void inGameAbilityRequest(InGameAbilityType type){
        InGameAbilityRequests.abilityRequest(type);
    }

    public static void skillTreeAbilityRequest(SkillTreeAbilityType type) {
        SkillTreeAbilityRequests.abilityRequest(type);
    }

    public static PortalModel getPortalModel() {
        return portalModel;
    }

    public static void setPortalModel(PortalModel portalModel) {
        Controller.portalModel = portalModel;
    }
}
