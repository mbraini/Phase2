package controller;

import controller.enums.InGameAbilityType;
import controller.enums.SkillTreeAbilityType;
import controller.interfaces.SizeChanger;
import controller.listeners.keyHelper.KeyHelper;
import controller.manager.GameManager;
import controller.manager.GameManagerThread;
import controller.manager.loading.GameLoader;
import constants.Constants;
import model.ModelRequests;
import model.animations.GameStartAnimation;
import model.inGameAbilities.InGameAbilityHandler;
import model.interfaces.ImageChanger;
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
import view.gamePanels.EndGameFrame;
import view.gamePanels.EndGamePanel;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;
import view.painter.Render;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private static String IP = "MAHDI-BARANI";
    private static HashMap<String ,Controller> controllerMap = new HashMap<>();
    private Render render;
    private GameLoop gameLoop;
    private FrameThread frameThread;
    private GameManager gameManager;
    private GameState gameState;
    private ModelData modelData;
    private ModelRequests modelRequests;
    private ViewData viewData;
    private ViewRequest viewRequest;

    public Controller() {
        modelData = new ModelData();
        modelRequests = new ModelRequests();
        viewData = new ViewData();
        viewRequest = new ViewRequest();
        gameState = new GameState();
    }

    public void updateView(){
        synchronized (getModelData().getModels()) {

            //////// syncronize them later !

            ArrayList<ObjectView> objectViews = (ArrayList<ObjectView>) getViewData().getViews().clone();
            ArrayList<FrameView> frameViews = (ArrayList<FrameView>) getViewData().getFrames().clone();
            ArrayList<ObjectModel> objectModels = (ArrayList<ObjectModel>) getModelData().getModels().clone();
            ArrayList<FrameModel> frameModels = (ArrayList<FrameModel>) getModelData().getFrames().clone();
            ArrayList<EffectView> effectViews = (ArrayList<EffectView>) getViewData().getEffectViews().clone();
            ArrayList<EffectModel> effectModels = (ArrayList<EffectModel>) getModelData().getEffectModels().clone();
            HashMap<ObjectModel ,FrameModel> locals =
                    (HashMap<ObjectModel, FrameModel>) getModelData().getLocalFrames().clone();

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
        Controller.getController(Controller.getIP()).getModelRequests().removeObjectModel(model.getId());
        ViewRequest.removeObjectView(model.getId());
    }

    public static void sendViewUpdates() {

    }

    public synchronized static void removeEffect(EffectModel effectModel) {
        Controller.getController(Controller.getIP()).getModelRequests().removeEffectModel(effectModel.getId());
        ViewRequest.removeEffectView(effectModel.getId());
    }

    public static void removeFrame(FrameModel frameModel) {
        Controller.getController(Controller.getIP()).getModelRequests().removeFrameModel(frameModel.getId());
        ViewRequest.removeFrameView(frameModel.getId());
    }

    public void resume() {
        getGameState().setPause(false);
    }

    public static void load(){
        synchronized (GameManagerThread.getJsonLock()) {
            new GameLoader().load();
        }
    }

    public void pause() {
        gameState.setPause(true);
    }

    public static void randomizeKeys() {
        KeyHelper.randomize();
    }

    public static void reorderKeys() {
        KeyHelper.reorder();
    }


    private void updateObjectViews(){

    }

    private void setVariables(){
        ViewData.setTime(getGameState().getTime());
        ViewData.setHp(getGameState().getHp());
        ViewData.setXp(getGameState().getXp());
        ViewData.setWave(getGameState().getWave());
    }


    public void startGame(){
        getGameState().setOver(false);
        getGameState().setXp(3000);
//        load();
        modelStarter();
        viewStarter();
        addEpsilonAndFrame();
        new GameStartAnimation(getModelData().getFrames().getFirst()).StartAnimation();
        InGameAbilityHandler.initInGameAbilities();
        SkillTreeAbilityHandler.initAbilities();
        threadsStarter();
    }

    public void endGame() {
        Controller.getController(Controller.getIP()).getModelRequests().endRequest();
        ViewRequest.endRequest();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getGameState().setOver(true);
        render.interrupt();
        new EndGamePanel(new EndGameFrame()).start();
    }


    private void addEpsilonAndFrame() {
        EpsilonModel epsilon = new EpsilonModel(
                new Vector(Constants.SCREEN_SIZE.width / 2d ,
                        Constants.SCREEN_SIZE.height / 2d
                )
                ,Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        getModelData().addModel(epsilon);
        getModelData().setEpsilon(epsilon);
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
        getModelData().addFrame(frameModel);
        getModelData().setEpsilonFrame(frameModel);
        ViewData.addFrame(new FrameView(
                frameModel.getPosition(),
                frameModel.getSize(),
                frameModel.getId())
        );
        ViewData.addImaginaryPanel(new ImaginaryPanel(frameModel.getId()));
    }

    public void threadsStarter() {
        frameThread = new FrameThread();
        gameLoop = new GameLoop();
        render = new Render();
        render.start();
        gameManager = new GameManager();
        GameManagerThread gameManagerThread = gameManager.getGameManager();
        frameThread.start();
        gameLoop.start();
        gameManagerThread.start();
    }

    private static void controllerStarter() {
        /////todo
    }

    private void modelStarter() {
        getModelData().resetAll();
        getModelRequests().resetAll();
        getViewData().resetAll();
    }

    private static void viewStarter() {
        ViewData.resetAll();
        ViewRequest.resetAll();
    }

    public static void setUpManager(){
        new GameManager();
    }



    public boolean shootRequest(Vector clickedPoint){
        if (ShootRequest.canShoot()){
            new ShootRequest((EpsilonModel) getModelData().getModels().getFirst()).shoot(clickedPoint);
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

    public static HashMap<String, Controller> getControllerMap() {
        return controllerMap;
    }

    public static void setControllerMap(HashMap<String, Controller> controllerMap) {
        Controller.controllerMap = controllerMap;
    }

    public static String getIP() {
        return IP;
    }

    public static Controller getController(String ip) {
        return controllerMap.get(ip);
    }

    public ModelData getModelData() {
        return modelData;
    }

    public void setModelData(ModelData modelData) {
        this.modelData = modelData;
    }

    public ModelRequests getModelRequests() {
        return modelRequests;
    }

    public void setModelRequests(ModelRequests modelRequests) {
        this.modelRequests = modelRequests;
    }

    public ViewData getViewData() {
        return viewData;
    }

    public void setViewData(ViewData viewData) {
        this.viewData = viewData;
    }

    public ViewRequest getViewRequest() {
        return viewRequest;
    }

    public void setViewRequest(ViewRequest viewRequest) {
        this.viewRequest = viewRequest;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
