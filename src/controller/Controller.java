package controller;

import controller.enums.ObjectType;
import controller.manager.GameManager;
import controller.manager.Spawner;
import data.Constants;
import model.ModelRequests;
import model.threads.FrameThread;
import model.threads.GameLoop;
import model.GameState;
import model.ModelData;
import model.animations.GameStartAnimation;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.ObjectModel;
import utils.Helper;
import utils.Vector;
import view.ViewRequest;
import view.ViewData;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.painter.Render;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Controller {

    public static void updateView(){
        ArrayList<ObjectView> views = ViewData.getViews();
        ArrayList<FrameView> frames = ViewData.getFrames();
        HashMap<ObjectView ,FrameView> locals = new HashMap<>();

        for (int i = 0 ;i < ModelData.getFrames().size() ;i++){
            FrameModel frame = ModelData.getFrames().get(i);
            frames.get(i).setPosition(frame.getPosition());
            frames.get(i).setDimension(frame.getSize());
        }

        for (int i = 0 ;i < ModelData.getModels().size() ;i++){
            ObjectModel model = ModelData.getModels().get(i);
            views.get(i).setPosition(model.getPosition());
            views.get(i).setTheta(model.getTheta());
            views.get(i).setHovering(model.isHovering());

//            FrameModel frameModel = ModelData.getLocalFrames().get(model);
//            if (ModelData.getLocalFrames().get(model) != null && ModelData.getLocalFrames().containsKey(model)){
//                locals.put(
//                        ViewData.getViews().get(ModelData.getModels().indexOf(model)),
//                        ViewData.getFrames().get(ModelData.getFrames().indexOf(frameModel))
//                );
//            }
//            else {
//                locals.put(
//                        ViewData.getViews().get(ModelData.getModels().indexOf(model)),
//                        null
//                );
//            }
        }


        ViewData.setViews(views);
        ViewData.setFrames(frames);
        ViewData.setLocalViews(locals);

        setVariables();
    }

    public static void removeObject(ObjectModel model) {
        ModelRequests.removeObjectModelReq.add(model.getId());
        ViewRequest.removeObjectViewReq.add(model.getId());
    }

    public static void sendViewUpdates() {

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
        modelStarter();
        viewStarter();
        addEpsilonAndFrame();
        new GameStartAnimation(ModelData.getFrames().getFirst()).StartAnimation();
        Controller.threadsStarter();
    }

    private static void addEpsilonAndFrame() {
        Spawner.addObject(
                        new Vector(Constants.SCREEN_SIZE.width / 2d , Constants.SCREEN_SIZE.height / 2d) ,
                        Helper.RandomStringGenerator(Constants.ID_SIZE) ,
                ObjectType.epsilon
        );

        Spawner.addFrame(
                new Vector(
                        Constants.SCREEN_SIZE.width / 2d - Constants.GAME_WIDTH / 2d ,
                        Constants.SCREEN_SIZE.height / 2d - Constants.GAME_HEIGHT / 2d),
                new Dimension(Constants.GAME_WIDTH ,Constants.GAME_HEIGHT),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );

        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 3d ,Constants.SCREEN_SIZE.height / 3d) ,
                Helper.RandomStringGenerator(Constants.ID_SIZE),
                ObjectType.omenoct
        );

        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 4d ,Constants.SCREEN_SIZE.height / 2d) ,
                Helper.RandomStringGenerator(Constants.ID_SIZE),
                ObjectType.omenoct
        );

        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d ,Constants.SCREEN_SIZE.height / 2d + 10) ,
                Helper.RandomStringGenerator(Constants.ID_SIZE),
                ObjectType.omenoct
        );
        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d ,Constants.SCREEN_SIZE.height / 2d + 150) ,
                Helper.RandomStringGenerator(Constants.ID_SIZE),
                ObjectType.necropick
        );

    }

    public static void threadsStarter() {
        new FrameThread().start();
        new GameLoop().start();
        new Render().start();
    }

    private static void controllerStarter() {
        /////todo
    }

    private static void modelStarter() {
        ModelData.setModels(new ArrayList<>());
        ModelData.setFrames(new ArrayList<>());
    }

    private static void viewStarter() {
        ViewData.setLocalViews(new HashMap<>());
        ViewData.setPanels(new ArrayList<>());
        ViewData.setViews(new ArrayList<>());
        ViewData.setFrames(new ArrayList<>());
    }

    public static void setUpManager(){
        new GameManager();
    }


}
