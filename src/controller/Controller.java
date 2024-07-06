package controller;

import controller.enums.ObjectType;
import controller.manager.GameManager;
import controller.manager.Spawner;
import data.Constants;
import model.ModelRequests;
import model.interfaces.ImageChanger;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.frameModel.FrameModelBuilder;
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
                    frameViews.get(i).setDimension(frame.getSize());
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
        ViewData.addFrame(new FrameView(
                frameModel.getPosition(),
                frameModel.getSize(),
                frameModel.getId())
        );
        ViewData.addImaginaryPanel(new ImaginaryPanel(frameModel.getId()));


         Spawner.addObject(new Vector(200 ,200),
                ObjectType.barricados
        );
//        Spawner.addObject(new Vector(300 ,300),
//                ObjectType.necropick
//        );
//        Spawner.addObject(new Vector(0 ,600),
//                ObjectType.necropick
//        );
//        Spawner.addObject(new Vector(600 ,300),
//                ObjectType.necropick
//        );
//        Spawner.addObject(new Vector(0 ,0),
//                ObjectType.wyrm
//        );

//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d ,Constants.SCREEN_SIZE.height / 2d + 100),
//                ObjectType.trigorath
//        );
//
//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d - 150 ,Constants.SCREEN_SIZE.height / 2d - 150),
//                ObjectType.omenoct
//        );
//
//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d + 150 ,Constants.SCREEN_SIZE.height / 2d + 150),
//                ObjectType.archmire
//        );
//
//
//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d + 150 ,Constants.SCREEN_SIZE.height / 2d + 150),
//                ObjectType.squarantine
//        );
//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d + 150 ,Constants.SCREEN_SIZE.height / 2d + 150),
//                ObjectType.squarantine
//        );
//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d + 150 ,Constants.SCREEN_SIZE.height / 2d + 150),
//                ObjectType.trigorath
//        );
//        Spawner.addObject(new Vector(Constants.SCREEN_SIZE.width / 2d + 150 ,Constants.SCREEN_SIZE.height / 2d + 150),
//                ObjectType.trigorath
//        );
////
////
//        Spawner.addObject(
//                new Vector(Constants.SCREEN_SIZE.width / 2d ,Constants.SCREEN_SIZE.height / 2d),
//                ObjectType.blackOrb
//        );
//
//        FrameModelBuilder builder1 = new FrameModelBuilder(
//                new Vector(
//                        10 ,
//                        10
//                ),
//                new Dimension(800,800),
//                Helper.RandomStringGenerator(Constants.ID_SIZE)
//        );
//        builder1.setSolid(true);
//        FrameModel frameModel1 = builder1.create();
//        Spawner.addFrame(frameModel1);
//
//        FrameModelBuilder builder2 = new FrameModelBuilder(
//                new Vector(
//                        Constants.SCREEN_SIZE.width - 200 ,
//                        Constants.SCREEN_SIZE.height - 200
//                ),
//                new Dimension(200,200),
//                Helper.RandomStringGenerator(Constants.ID_SIZE)
//        );
//        builder2.setSolid(true);
//        FrameModel frameModel2 = builder2.create();
//        Spawner.addFrame(frameModel2);

//        Spawner.spawnBoss();
    }

    public static void threadsStarter() {
        new FrameThread().start();
        new GameLoop().start();
        new Render().start();
        GameManager manager = new GameManager();
        manager.getGameManager().start();
    }

    private static void controllerStarter() {
        /////todo
    }

    private static void modelStarter() {
        ModelData.setModels(new ArrayList<>());
        ModelData.setFrames(new ArrayList<>());
    }

    private static void viewStarter() {
        ViewData.setPanels(new ArrayList<>());
        ViewData.setViews(new ArrayList<>());
        ViewData.setFrames(new ArrayList<>());
    }

    public static void setUpManager(){
        new GameManager();
    }


}
