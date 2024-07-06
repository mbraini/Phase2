package controller.manager;

import controller.enums.ObjectType;
import data.Constants;
import model.ModelRequests;
import model.objectModel.CollectiveModel;
import model.objectModel.effects.ArchmireAoeEffectModel;
import model.objectModel.effects.BlackOrbAoeEffectModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.vomit.BossAoeEffectModel;
import model.objectModel.fighters.finalBoss.bossHelper.HandModel;
import model.objectModel.fighters.finalBoss.bossHelper.HeadModel;
import model.objectModel.fighters.finalBoss.bossHelper.PunchModel;
import model.objectModel.fighters.miniBossEnemies.barricadosModel.BarricadosFirstModel;
import model.objectModel.fighters.miniBossEnemies.barricadosModel.BarricadosModel;
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
import utils.Helper;
import utils.Vector;
import view.ViewRequest;
import view.objectViews.CollectiveView;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
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

import java.awt.*;
import java.util.Random;

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
                BlackOrbModel blackOrbModel = new BlackOrbModel(
                        position,
                        Helper.RandomStringGenerator(Constants.ID_SIZE)
                );
                ModelRequests.addAbstractEnemy(blackOrbModel);
                blackOrbModel.spawn();
                break;
            case barricados:
                int randomInteger = (new Random()).nextInt(0 ,2);
                if (randomInteger == 0){
                    BarricadosFirstModel barricadosModel = new BarricadosFirstModel(position ,id);
                    ModelRequests.addObjectModel(barricadosModel);
                }
                else {
                    BarricadosSecondModel barricadosModel = new BarricadosSecondModel(position ,id);
                    ModelRequests.addObjectModel(barricadosModel);
                    ModelRequests.addFrameModel(barricadosModel.getFrameModel());
                    ViewRequest.addFrameView(
                            new FrameView(
                                    barricadosModel.getFrameModel().getPosition(),
                                    barricadosModel.getFrameModel().getSize(),
                                    barricadosModel.getId()
                            )
                    );
                }
                ViewRequest.addObjectView(new BarricadosView(
                        position,
                        id
                ));
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
                ViewRequest.addObjectView(new NecropickBulletView(position ,id));
                break;
            case wyrmBullet:
                ModelRequests.addObjectModel(new WyrmBulletModel(position ,direction ,id));
                ViewRequest.addObjectView(new WyrmBulletView(position ,id));
                break;
            case bossBullet:
                ModelRequests.addObjectModel(new BossBulletModel(position ,direction ,id));
                ViewRequest.addObjectView(new BossBulletView(position ,id));
        }
    }

    public synchronized static void addArchmireEffect(ArchmireAoeEffectModel archmireEffectModel){
        String id = archmireEffectModel.getId();
        ModelRequests.addEffectModel(archmireEffectModel);
        ViewRequest.addEffectView(new ArchmireEffectView(archmireEffectModel.getArea() ,id));
    }

    public static void addBlackOrbEffectModel(BlackOrbAoeEffectModel effectModel) {
        ModelRequests.addEffectModel(effectModel);
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
        ModelRequests.addObjectModel(new CollectiveModel(position,id ,value));
        ViewRequest.addObjectView(new CollectiveView(position ,id));
    }

    public static synchronized void spawnBoss(){
        Boss boss = new Boss();
        ModelRequests.addAbstractEnemy(boss);
        boss.spawnHelpers();
        boss.spawnPunch();
    }


    public synchronized static void spawnHead(HeadModel head) {
        ModelRequests.addObjectModel(head);
        ViewRequest.addObjectView(new HeadView(head.getPosition() ,head.getId()));

        ModelRequests.addFrameModel(head.getFrame());
        ViewRequest.addFrameView(new FrameView(
                head.getFrame().getPosition(),
                head.getFrame().getSize(),
                head.getFrame().getId()
        ));
    }

    public synchronized static void spawnHand(HandModel hand){
        ModelRequests.addObjectModel(hand);
        ViewRequest.addObjectView(new HandView(hand.getPosition() ,hand.getId()));

        ModelRequests.addFrameModel(hand.getFrame());
        ViewRequest.addFrameView(new FrameView(
                hand.getFrame().getPosition(),
                hand.getFrame().getSize(),
                hand.getFrame().getId()
        ));
    }

    public static void addBossEffect(BossAoeEffectModel effectModel) {
        ModelRequests.addEffectModel(effectModel);
        ViewRequest.addEffectView(new BossAoeEffectView(effectModel.getArea() ,effectModel.getId()));
    }

    public static void addPunch(PunchModel punch) {
        ModelRequests.addObjectModel(punch);
        ViewRequest.addObjectView(
                new PunchView(
                        punch.getPosition(),
                        punch.getId()
                )
        );

        ModelRequests.addFrameModel(punch.getFrame());
        ViewRequest.addFrameView(
                new FrameView(
                        punch.getFrame().getPosition(),
                        punch.getFrame().getSize(),
                        punch.getFrame().getId()
                )
        );
    }

    public static void addOrb(Vector position, BlackOrbModel blackOrbModel, int number, String id) {
        ModelRequests.addObjectModel(new OrbModel(
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
}
