package controller.manager;

import controller.Controller;
import controller.enums.ModelType;
import constants.Constants;
import model.ModelRequests;
import model.inGameAbilities.Dismay.EpsilonProtectorModel;
import model.objectModel.CollectiveModel;
import model.objectModel.effects.ArchmireAoeEffectModel;
import model.objectModel.effects.BlackOrbAoeEffectModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.EpsilonVertexModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.vomit.BossAoeEffectModel;
import model.objectModel.fighters.finalBoss.bossHelper.HandModel;
import model.objectModel.fighters.finalBoss.bossHelper.HeadModel;
import model.objectModel.fighters.finalBoss.bossHelper.PunchModel;
import model.objectModel.fighters.miniBossEnemies.barricadosModel.BarricadosFirstModel;
import model.objectModel.fighters.miniBossEnemies.barricadosModel.BarricadosSecondModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.fighters.basicEnemies.SquarantineModel;
import model.objectModel.fighters.basicEnemies.TrigorathModel;
import model.objectModel.fighters.miniBossEnemies.blackOrbModel.BlackOrbModel;
import model.objectModel.fighters.miniBossEnemies.blackOrbModel.OrbModel;
import model.objectModel.fighters.normalEnemies.archmireModel.ArchmireModel;
import model.objectModel.fighters.normalEnemies.necropickModel.NecropickModel;
import model.objectModel.fighters.normalEnemies.omenoctModel.OmenoctModel;
import model.objectModel.fighters.normalEnemies.wyrmModel.WyrmModel;
import model.objectModel.projectiles.*;
import model.skillTreeAbilities.Cerberus.CerberusModel;
import utils.Helper;
import utils.Vector;
import view.ViewRequest;
import view.objectViews.*;
import view.objectViews.basicEnemyView.SquarantineView;
import view.objectViews.basicEnemyView.TrigorathView;
import view.objectViews.bossView.BossAoeEffectView;
import view.objectViews.bossView.HandView;
import view.objectViews.bossView.HeadView;
import view.objectViews.bossView.PunchView;
import view.objectViews.miniBossEnemyView.BarricadosView;
import view.objectViews.miniBossEnemyView.BlackOrbLaserEffectView;
import view.objectViews.miniBossEnemyView.OrbView;
import view.objectViews.normalEnemyView.NecropickView;
import view.objectViews.normalEnemyView.OmenoctView;
import view.objectViews.normalEnemyView.WyrmView;
import view.objectViews.normalEnemyView.archmireView.ArchmireEffectView;
import view.objectViews.normalEnemyView.archmireView.ArchmireView;
import view.objectViews.projectiles.*;

import java.util.Random;

public abstract class Spawner {


    public synchronized static void addFrame(FrameModel frameModel){
        Controller.getController(Controller.getIP()).getModelRequests().addFrameModel(frameModel);
        ViewRequest.addFrameView(new FrameView(
                frameModel.getPosition(),
                frameModel.getSize(),
                frameModel.getId()
        ));
    }

    public synchronized static void spawnObjectWithId(Vector position, ModelType modelType, String id){
        switch (modelType) {
            case epsilon:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new EpsilonModel(position, id));
                ViewRequest.addObjectView(new EpsilonView(position, id));
                break;
            case trigorath:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new TrigorathModel(position, id));
                ViewRequest.addObjectView(new TrigorathView(position, id));
                break;
            case squarantine:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new SquarantineModel(position, id));
                ViewRequest.addObjectView(new SquarantineView(position, id));
                break;
            case omenoct:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new OmenoctModel(position, id));
                ViewRequest.addObjectView(new OmenoctView(position, id));
                break;
            case necropick:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new NecropickModel(position ,id));
                ViewRequest.addObjectView(new NecropickView(position ,id));
                break;
            case archmire:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new ArchmireModel(position ,id));
                ViewRequest.addObjectView(new ArchmireView(position ,id));
                break;
            case wyrm:
                WyrmModel wyrmModel = new WyrmModel(position ,id);
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(wyrmModel);
                ViewRequest.addObjectView(new WyrmView(wyrmModel.getPosition() ,id));
                addFrame(wyrmModel.getFrameModel());
                break;
            case blackOrb :
                BlackOrbModel blackOrbModel = new BlackOrbModel(
                        position,
                        id
                );
                Controller.getController(Controller.getIP()).getModelRequests().addAbstractEnemy(blackOrbModel);
                blackOrbModel.spawn();
                break;
            case barricados:
                int randomInteger = (new Random()).nextInt(0 ,2);
                if (randomInteger == 0){
                    BarricadosFirstModel barricadosModel = new BarricadosFirstModel(position ,id);
                    Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(barricadosModel);
                }
                else {
                    BarricadosSecondModel barricadosModel = new BarricadosSecondModel(position ,id);
                    Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(barricadosModel);
                    addFrame(barricadosModel.getFrameModel());
                }
                ViewRequest.addObjectView(new BarricadosView(
                        position,
                        id
                ));
                break;
            case cerberus:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new CerberusModel(position ,id));
                 ViewRequest.addObjectView(new CerberusView(position ,id));
                 break;
        }
    }

    public synchronized static void spawnObject(Vector position , ModelType modelType){
        spawnObjectWithId(position , modelType,Helper.RandomStringGenerator(Constants.ID_SIZE));
    }



    public synchronized static void addProjectile(Vector position , Vector direction , ModelType modelType){
        String id = Helper.RandomStringGenerator(Constants.ID_SIZE);
        addProjectileWithId(position ,direction , modelType,id);
    }

    public synchronized static void addProjectileWithId(Vector position , Vector direction , ModelType modelType, String id){
        switch (modelType) {
            case epsilonBullet:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new EpsilonBulletModel(
                                position,
                                direction,
                                id
                        )
                );
                ViewRequest.addObjectView(new EpsilonBulletView(position, id));
                break;
            case omenoctBullet:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new OmenoctBulletModel(
                                position,
                                direction,
                                id
                        )
                );
                ViewRequest.addObjectView(new OmenoctBulletView(position, id));
                break;
            case necropickBullet:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new NecropickBulletModel(position ,direction ,id));
                ViewRequest.addObjectView(new NecropickBulletView(position ,id));
                break;
            case wyrmBullet:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new WyrmBulletModel(position ,direction ,id));
                ViewRequest.addObjectView(new WyrmBulletView(position ,id));
                break;
            case bossBullet:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new BossBulletModel(position ,direction ,id));
                ViewRequest.addObjectView(new BossBulletView(position ,id));
                break;
            case slaughterBullet:
                Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new SlaughterBulletModel(position, direction ,id));
                ViewRequest.addObjectView(new SlaughterBulletView(position ,id));
                break;
        }
    }

    public synchronized static void addArchmireEffect(ArchmireAoeEffectModel archmireEffectModel){
        String id = archmireEffectModel.getId();
        Controller.getController(Controller.getIP()).getModelRequests().addEffectModel(archmireEffectModel);
        ViewRequest.addEffectView(new ArchmireEffectView(archmireEffectModel.getArea() ,id));
    }

    public static void addBlackOrbEffectModel(BlackOrbAoeEffectModel effectModel) {
        Controller.getController(Controller.getIP()).getModelRequests().addEffectModel(effectModel);
        ViewRequest.addEffectView(new BlackOrbLaserEffectView(effectModel.getArea() ,effectModel.getId()));
    }

    public static void addCollectives(Vector position ,int count ,int value){
        Random random = new Random();
        for (int i = 0; i < count; i++){
            int x = random.nextInt(
                    (int) position.x - Constants.COLLECTIVE_BOX_DIMENSION.width ,
                    (int) position.x + Constants.COLLECTIVE_BOX_DIMENSION.width
            );
            int y = random.nextInt(
                    (int) position.y - Constants.COLLECTIVE_BOX_DIMENSION.height ,
                    (int) position.y + Constants.COLLECTIVE_BOX_DIMENSION.height
            );
            addCollective(new Vector(x ,y) ,value);
        }
    }

    private static void addCollective(Vector position, int value) {
        String id = Helper.RandomStringGenerator(Constants.ID_SIZE);
        Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new CollectiveModel(position,id ,value));
        ViewRequest.addObjectView(new CollectiveView(position ,id));
    }

    public static synchronized void spawnBoss(){
        Boss boss = new Boss();
        Controller.getController(Controller.getIP()).getModelRequests().addAbstractEnemy(boss);
        boss.spawnHelpers();
        boss.spawnPunch();
    }


    public synchronized static void spawnHead(HeadModel head) {
        Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(head);
        ViewRequest.addObjectView(new HeadView(head.getPosition() ,head.getId()));

        Controller.getController(Controller.getIP()).getModelRequests().addFrameModel(head.getFrame());
        ViewRequest.addFrameView(new FrameView(
                head.getFrame().getPosition(),
                head.getFrame().getSize(),
                head.getFrame().getId()
        ));
    }

    public synchronized static void spawnHand(HandModel hand){
        Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(hand);
        ViewRequest.addObjectView(new HandView(hand.getPosition() ,hand.getId()));

        Controller.getController(Controller.getIP()).getModelRequests().addFrameModel(hand.getFrame());
        ViewRequest.addFrameView(new FrameView(
                hand.getFrame().getPosition(),
                hand.getFrame().getSize(),
                hand.getFrame().getId()
        ));
    }

    public static void addBossEffect(BossAoeEffectModel effectModel) {
        Controller.getController(Controller.getIP()).getModelRequests().addEffectModel(effectModel);
        ViewRequest.addEffectView(new BossAoeEffectView(effectModel.getArea() ,effectModel.getId()));
    }

    public static void addPunch(PunchModel punch) {
        Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(punch);
        ViewRequest.addObjectView(
                new PunchView(
                        punch.getPosition(),
                        punch.getId()
                )
        );

        Controller.getController(Controller.getIP()).getModelRequests().addFrameModel(punch.getFrame());
        ViewRequest.addFrameView(
                new FrameView(
                        punch.getFrame().getPosition(),
                        punch.getFrame().getSize(),
                        punch.getFrame().getId()
                )
        );
    }

    public static void spawnOrb(Vector position, BlackOrbModel blackOrbModel, int number, String id) {
        Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(new OrbModel(
                position,
                blackOrbModel,
                number,
                id
        ));
        ViewRequest.addObjectView(new OrbView(
                position,
                id
        ));
    }

    public static synchronized void spawnProtector(EpsilonProtectorModel protectorModel) {
        Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(protectorModel);
        ViewRequest.addObjectView(new EpsilonProtectorView(
                protectorModel.getPosition(),
                protectorModel.getId()
        ));
    }

    public static void spawnVertex(EpsilonVertexModel epsilonVertexModel) {
        Controller.getController(Controller.getIP()).getModelRequests().addObjectModel(epsilonVertexModel);
        ViewRequest.addObjectView(
                new EpsilonVertexView(
                        epsilonVertexModel.getPosition(),
                        epsilonVertexModel.getId()
                )
        );
    }
}
