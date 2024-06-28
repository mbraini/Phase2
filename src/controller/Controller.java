package controller;

import controller.enums.ObjectType;
import controller.manager.GameManager;
import controller.manager.Spawner;
import data.Constants;
import model.ModelRequests;
import model.objectModel.EpsilonModel;
import model.objectModel.effectModel.EffectModel;
import model.objectModel.normalEnemies.necropickModel.NecropickModel;
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
import view.gamePanels.ImaginaryPanel;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;
import view.painter.Render;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Controller {

    public static void updateView(){
        synchronized (ModelData.getModels()) {

            //////// syncronize them later !

            ArrayList<ObjectView> objectViews = (ArrayList<ObjectView>) ViewData.getViews().clone();
            ArrayList<FrameView> frameViews = (ArrayList<FrameView>) ViewData.getFrames().clone();
            ArrayList<ObjectModel> objectModels = (ArrayList<ObjectModel>) ModelData.getModels().clone();
            ArrayList<FrameModel> frameModels = (ArrayList<FrameModel>) ModelData.getFrames().clone();
            ArrayList<EffectView> effectViews = (ArrayList<EffectView>) ViewData.getEffectViews().clone();
            ArrayList<EffectModel> effectModels = (ArrayList<EffectModel>) ModelData.getEffectModels().clone();

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
                    frameViews.get(i).setDimension(frame.getSize());
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
                    effectViews.get(i).setColor(effectModel.getColor());
                }


                ViewData.setViews(objectViews);
                ViewData.setFrames(frameViews);
//        ViewData.setLocalViews(locals);

                setVariables();
            }
            catch (Exception e){
                System.out.println("update view exeption!");
            }
            HashMap<ObjectView, FrameView> locals = new HashMap<>();
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
        EpsilonModel epsilon = new EpsilonModel(
                new Vector(Constants.SCREEN_SIZE.width / 2d ,
                        Constants.SCREEN_SIZE.height / 2d
                )
                ,Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        ModelData.addModel(epsilon);
        ViewData.addObject(new EpsilonView(epsilon.getPosition() ,epsilon.getId()));

        FrameModel frameModel = new FrameModel(
                new Vector(
                    Constants.SCREEN_SIZE.width / 2d - Constants.GAME_WIDTH / 2d ,
                    Constants.SCREEN_SIZE.height / 2d - Constants.GAME_HEIGHT / 2d
                ),
                new Dimension(Constants.GAME_WIDTH ,Constants.GAME_HEIGHT),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        ModelData.addFrame(frameModel);
        ViewData.addFrame(new FrameView(
                frameModel.getPosition(),
                frameModel.getSize(),
                frameModel.getId())
        );
        ViewData.addImaginaryPanel(new ImaginaryPanel(frameModel.getId()));

        Spawner.addObject(new Vector(0 ,0),
                ObjectType.wyrm
        );

//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d - 150 ,Constants.SCREEN_SIZE.height / 2d - 150),
//                ObjectType.omenoct
//        );

//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d + 150 ,Constants.SCREEN_SIZE.height / 2d + 150),
//                ObjectType.archmire
//        );

        Spawner.addFrame(new Vector(Constants.SCREEN_SIZE.width / 3d ,Constants.SCREEN_SIZE.height / 3d),
                new Dimension(Constants.GAME_WIDTH ,Constants.GAME_HEIGHT)
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
