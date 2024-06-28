package controller.manager;

import controller.enums.ObjectType;
import data.Constants;
import model.ModelData;
import model.ModelRequests;
import model.objectModel.EpsilonModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.basicEnemies.SquarantineModel;
import model.objectModel.basicEnemies.TrigorathModel;
import model.objectModel.miniBossEnemies.blackOrbModel.BlackOrbLaserEffectModel;
import model.objectModel.miniBossEnemies.blackOrbModel.BlackOrbModel;
import model.objectModel.miniBossEnemies.blackOrbModel.OrbModel;
import model.objectModel.normalEnemies.archmireModel.ArchmireModel;
import model.objectModel.normalEnemies.archmireModel.ArchmireEffectModel;
import model.objectModel.normalEnemies.necropickModel.NecropickModel;
import model.objectModel.normalEnemies.omenoctModel.OmenoctModel;
import model.objectModel.normalEnemies.wyrmModel.WyrmModel;
import model.objectModel.projectiles.EpsilonBulletModel;
import model.objectModel.projectiles.NecropickBulletModel;
import model.objectModel.projectiles.OmenoctBulletModel;
import utils.Helper;
import utils.Vector;
import view.ViewData;
import view.ViewRequest;
import view.gamePanels.ImaginaryPanel;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
import view.objectViews.basicEnemyView.SquarantineView;
import view.objectViews.basicEnemyView.TrigorathView;
import view.objectViews.miniBossEnemyView.BlackOrbLaserEffectView;
import view.objectViews.miniBossEnemyView.OrbView;
import view.objectViews.normalEnemyView.NecropickView;
import view.objectViews.normalEnemyView.OmenoctView;
import view.objectViews.normalEnemyView.WyrmView;
import view.objectViews.normalEnemyView.archmireView.ArchmireEffectView;
import view.objectViews.normalEnemyView.archmireView.ArchmireView;
import view.objectViews.projectiles.EpsilonBulletView;
import view.objectViews.projectiles.NecropickBulletView;
import view.objectViews.projectiles.OmenoctBulletView;

import java.awt.*;

public abstract class Spawner {

    public synchronized static void addFrame(FrameModel frameModel){
        ModelRequests.addFrameModel(frameModel);
        ViewRequest.addFrameView(new FrameView(
                frameModel.getPosition(),
                frameModel.getSize(),
                frameModel.getId()
        ));
    }

    public synchronized static void addFrame(Vector position , Dimension size){
        String id = Helper.RandomStringGenerator(Constants.ID_SIZE);
        addFrameWithId(position ,size ,id);
    }

    public synchronized static void addFrameWithId(Vector position ,Dimension size ,String id){
        ModelRequests.addFrameModel(new FrameModel(position ,size ,id));
        ViewRequest.addFrameView(new FrameView(position ,size ,id));
    }


    public synchronized static void addObjectWithId(Vector position, ObjectType objectType ,String id){
        switch (objectType) {
            case epsilon:
                ModelRequests.addObjectModel(new EpsilonModel(position, id));
                ViewRequest.addObjectView(new EpsilonView(position, id));
                break;
            case trigorath:
                ModelRequests.addObjectModel(new TrigorathModel(position, id));
                ViewRequest.addObjectView(new TrigorathView(position, id));
                break;
            case squarantine:
                ModelRequests.addObjectModel(new SquarantineModel(position, id));
                ViewRequest.addObjectView(new SquarantineView(position, id));
                break;
            case omenoct:
                ModelRequests.addObjectModel(new OmenoctModel(position, id));
                ViewRequest.addObjectView(new OmenoctView(position, id));
                break;
            case necropick:
                ModelRequests.addObjectModel(new NecropickModel(position ,id));
                ViewRequest.addObjectView(new NecropickView(position ,id));
                break;
            case archmire:
                ModelRequests.addObjectModel(new ArchmireModel(position ,id));
                ViewRequest.addObjectView(new ArchmireView(position ,id));
                break;
            case wyrm:
                WyrmModel wyrmModel = new WyrmModel(position ,id);
                ModelRequests.addObjectModel(wyrmModel);
                ViewRequest.addObjectView(new WyrmView(wyrmModel.getPosition() ,id));
                addFrame(wyrmModel.getFrameModel());
                break;
            case blackOrb :
                new BlackOrbModel(position).spawn();
                break;
            case orb:
                ModelRequests.addObjectModel(new OrbModel(position ,id));
                ViewRequest.addObjectView(new OrbView(position ,id));
        }
    }

    public synchronized static void addObject(Vector position ,ObjectType objectType){
        addObjectWithId(position ,objectType ,Helper.RandomStringGenerator(Constants.ID_SIZE));
    }


    public synchronized static void addProjectile(Vector position ,Vector direction ,ObjectType objectType){
        String id = Helper.RandomStringGenerator(Constants.ID_SIZE);
        addProjectileWithId(position ,direction ,objectType ,id);
    }

    public synchronized static void addProjectileWithId(Vector position ,Vector direction ,ObjectType objectType ,String id){
        switch (objectType) {
            case epsilonBullet:
                ModelRequests.addObjectModel(new EpsilonBulletModel(
                                position,
                                direction,
                                id
                        )
                );
                ViewRequest.addObjectView(new EpsilonBulletView(position, id));
                break;
            case omenoctBullet:
                ModelRequests.addObjectModel(new OmenoctBulletModel(
                                position,
                                direction,
                                id
                        )
                );
                ViewRequest.addObjectView(new OmenoctBulletView(position, id));
                break;
            case necropickBullet:
                ModelRequests.addObjectModel(new NecropickBulletModel(position ,direction ,id));
                ViewRequest.addObjectView(new NecropickBulletView(position ,0 ,id));
                break;
        }
    }

    public synchronized static void addArchmireEffect(ArchmireEffectModel archmireEffectModel){
        String id = archmireEffectModel.getId();
        ModelRequests.addEffectModel(archmireEffectModel);
        ViewRequest.addEffectView(new ArchmireEffectView(archmireEffectModel.getArea() ,id));
    }

    public static void addBlackOrbEffectModel(BlackOrbLaserEffectModel effectModel) {
        ModelRequests.addEffectModel(effectModel);
        ViewRequest.addEffectView(new BlackOrbLaserEffectView(effectModel.getArea() ,effectModel.getId()));
    }
}
