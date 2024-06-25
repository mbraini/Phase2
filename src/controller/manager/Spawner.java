package controller.manager;

import controller.enums.ObjectType;
import data.Constants;
import model.ModelData;
import model.objectModel.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.basicEnemies.SquarantineModel;
import model.objectModel.basicEnemies.TrigorathModel;
import model.objectModel.normalEnemies.omenoctModel.OmenoctModel;
import model.objectModel.projectiles.EpsilonBulletModel;
import model.objectModel.projectiles.OmenoctBulletModel;
import utils.Helper;
import utils.Vector;
import view.ViewData;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
import view.objectViews.basicEnemyView.SquarantineView;
import view.objectViews.basicEnemyView.TrigorathView;
import view.objectViews.normalEnemyView.OmenoctView;
import view.objectViews.projectiles.EpsilonBulletView;
import view.objectViews.projectiles.OmenoctBulletView;

import java.awt.*;

public abstract class Spawner {

    public static void addFrame(Vector position , Dimension size ,String id){
        ModelData.addFrame(new FrameModel(position ,size ,id));

        ViewData.addFrame(new FrameView(position ,size ,id));
        ViewData.addImaginaryPanel(new ImaginaryPanel(ViewData.getFrames().getLast().getId()));
    }

    public static void addObject(Vector position , String id , ObjectType objectType){
        switch (objectType){
            case epsilon :
                ModelData.addModel(new EpsilonModel(position ,id));
                ViewData.addObject(new EpsilonView(position ,id));
                break;
            case trigorath:
                ModelData.addModel(new TrigorathModel(position ,id));
                ViewData.addObject(new TrigorathView(position ,id));
                break;
            case squarantine:
                ModelData.addModel(new SquarantineModel(position ,id));
                ViewData.addObject(new SquarantineView(position ,id));
                break;
            case omenoct:
                ModelData.addModel(new OmenoctModel(position ,id));
                ViewData.addObject(new OmenoctView(position ,id));
                break;
        }
    }

    public static void addProjectile(Vector position ,Vector direction ,ObjectType objectType){
        String id = Helper.RandomStringGenerator(Constants.ID_SIZE);
        switch (objectType){
            case epsilonBullet :
                ModelData.addModel(new EpsilonBulletModel(
                        position ,
                        direction ,
                        id
                        )
                );
                ViewData.addObject(new EpsilonBulletView(position ,0 ,id));
                break;
            case omenoctBullet:
                ModelData.addModel(new OmenoctBulletModel(
                        position ,
                        direction ,
                        id
                        )
                );
                ViewData.addObject(new OmenoctBulletView(position, 0, id));
                break;
        }
    }

}
