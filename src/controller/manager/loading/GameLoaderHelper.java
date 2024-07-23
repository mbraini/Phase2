package controller.manager.loading;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import constants.Constants;
import controller.enums.EffectType;
import controller.enums.InGameAbilityType;
import controller.enums.ModelType;
import controller.enums.SkillTreeAbilityType;
import controller.manager.Spawner;
import model.ModelData;
import model.ModelRequests;
import model.inGameAbilities.*;
import model.inGameAbilities.Dismay.Dismay;
import model.inGameAbilities.Dismay.EpsilonProtectorModel;
import model.interfaces.ImpactAble;
import model.objectModel.ObjectModel;
import model.objectModel.effects.ArchmireAoeEffectModel;
import model.objectModel.effects.BlackOrbAoeEffectModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.EpsilonVertexModel;
import model.objectModel.fighters.basicEnemies.SquarantineModel;
import model.objectModel.fighters.basicEnemies.TrigorathModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.AbilityType;
import model.objectModel.fighters.finalBoss.bossHelper.BossHelperModel;
import model.objectModel.fighters.finalBoss.bossHelper.HandModel;
import model.objectModel.fighters.finalBoss.bossHelper.HeadModel;
import model.objectModel.fighters.finalBoss.bossHelper.PunchModel;
import model.objectModel.fighters.miniBossEnemies.barricadosModel.BarricadosFirstModel;
import model.objectModel.fighters.miniBossEnemies.barricadosModel.BarricadosSecondModel;
import model.objectModel.fighters.miniBossEnemies.blackOrbModel.BlackOrbModel;
import model.objectModel.fighters.miniBossEnemies.blackOrbModel.OrbModel;
import model.objectModel.fighters.normalEnemies.archmireModel.ArchmireModel;
import model.objectModel.fighters.normalEnemies.necropickModel.NecropickModel;
import model.objectModel.fighters.normalEnemies.omenoctModel.OmenoctModel;
import model.objectModel.fighters.normalEnemies.wyrmModel.WyrmModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.projectiles.*;
import model.skillTreeAbilities.*;
import model.skillTreeAbilities.Cerberus.Cerberus;
import model.skillTreeAbilities.Cerberus.CerberusModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.area.Polygon;
import view.ViewRequest;
import view.objectViews.CerberusView;
import view.objectViews.EpsilonProtectorView;
import view.objectViews.EpsilonView;
import view.objectViews.FrameView;
import view.objectViews.basicEnemyView.SquarantineView;
import view.objectViews.basicEnemyView.TrigorathView;
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

import java.util.ArrayList;

public class GameLoaderHelper {
    private static final Gson gson = getGson();

    private static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeNulls();
        builder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.getAnnotation(SkippedByJson.class) != null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                if (aClass.getAnnotation(SkippedByJson.class) == null)
                    return false;
                return true;
            }
        });
        return builder.create();
    }

    public synchronized static void addModel(JSONObject jsonObject, ModelType type){
        String jsonString = jsonObject.toString();
        ObjectModel model = null;
        if (type == null)
            return;
        switch (type){
            case epsilon :
                model = gson.fromJson(jsonString , EpsilonModel.class);
                ModelData.addModel(model);
                ModelData.setEpsilon((EpsilonModel) model);
                ViewRequest.addObjectView(
                        new EpsilonView(
                                model.getPosition() ,
                                model.getId()
                        )
                );
                for (EpsilonVertexModel epsilonVertexModel : ((EpsilonModel) model).getVertices())
                    Spawner.spawnVertex(epsilonVertexModel);
                break;
            case squarantine:
                model = gson.fromJson(jsonString , SquarantineModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new SquarantineView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case trigorath:
                model = gson.fromJson(jsonString , TrigorathModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new TrigorathView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case omenoct:
                model = gson.fromJson(jsonString , OmenoctModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new OmenoctView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case necropick:
                model = gson.fromJson(jsonString , NecropickModel.class);
                ModelRequests.addObjectModel(model);
                ((NecropickModel) model).start();
                ViewRequest.addObjectView(new NecropickView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case archmire:
                model = gson.fromJson(jsonString , ArchmireModel.class);
                try {
                    JSONArray aoeArray = jsonObject.getJSONArray("aoeEffects");
                    for (int i = 0 ;i < aoeArray.length() ;i++){
                        String area = aoeArray.getJSONObject(i).get("area").toString();
                        Polygon polygon = gson.fromJson(area ,Polygon.class);
                        ((ArchmireModel) model).getAoeEffects().get(i).setArchmire((ArchmireModel) model);
                        ((ArchmireModel) model).getAoeEffects().get(i).setArea(polygon);
                        addEffect(((ArchmireModel) model).getAoeEffects().get(i) ,EffectType.archmireEffect);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                ((ArchmireModel)model).start();
                ModelRequests.addObjectModel((ArchmireModel)model);
                ViewRequest.addObjectView(new ArchmireView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case wyrm:
                model = gson.fromJson(jsonString , WyrmModel.class);
                ModelRequests.addObjectModel(model);
                GameLoader.addFrame(((WyrmModel) model).getFrameModel());
                ((WyrmModel) model).start();
                ViewRequest.addObjectView(new WyrmView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case barricadosTheFirst:
                model = gson.fromJson(jsonString , BarricadosFirstModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new BarricadosView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case barricadosTheSecond:
                model = gson.fromJson(jsonString ,BarricadosSecondModel.class);
                GameLoader.addFrame(((BarricadosSecondModel) model).getFrameModel());
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new BarricadosView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case cerberus:
                model = gson.fromJson(jsonString , CerberusModel.class);
                ((CerberusModel) model).start();
                ModelData.addModel(model);
                ViewRequest.addObjectView(new CerberusView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case epsilonBullet:
                model = gson.fromJson(jsonString , EpsilonBulletModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new EpsilonBulletView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case wyrmBullet:
                model = gson.fromJson(jsonString , WyrmBulletModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new WyrmBulletView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case slaughterBullet:
                model = gson.fromJson(jsonString , SlaughterBulletModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new SlaughterBulletView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case necropickBullet:
                model = gson.fromJson(jsonString , NecropickBulletModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new NecropickBulletView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case omenoctBullet:
                model = gson.fromJson(jsonString , OmenoctBulletModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new OmenoctBulletView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
            case bossBullet:
                model = gson.fromJson(jsonString , BossBulletModel.class);
                ModelRequests.addObjectModel(model);
                ViewRequest.addObjectView(new BossBulletView(
                        model.getPosition(),
                        model.getId()
                ));
                break;
        }
        if (model instanceof ImpactAble) {
            ((ImpactAble) model).setImpacted(false);
        }
    }


    public synchronized static void addBlackOrb(BlackOrbModel blackOrbModel ,JSONObject jsonObject){
        blackOrbModel.start();
        blackOrbModel.getBlackOrbThread().setBlackOrbModel(blackOrbModel);
        for (OrbModel orbModel : blackOrbModel.getOrbModels()) {
            orbModel.setBlackOrbModel(blackOrbModel);
            addOrb(orbModel);
        }
        for (BlackOrbAoeEffectModel effectModel : blackOrbModel.getEffectModels()) {
            effectModel.setBlackOrbModel(blackOrbModel);
            JSONArray aoeArray = null;
            try {
                aoeArray = jsonObject.getJSONArray("effectModels");
                for (int i = 0 ;i < aoeArray.length() ;i++){
                    String area = aoeArray.getJSONObject(i).get("area").toString();
                    Polygon polygon = gson.fromJson(area ,Polygon.class);
                    blackOrbModel.getEffectModels().get(i).setArea(polygon);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            addEffect(effectModel ,EffectType.BlackOrbEffect);
        }

        ModelRequests.addAbstractEnemy(blackOrbModel);
    }

    public synchronized static void addOrb(OrbModel orbModel){
        ModelRequests.addObjectModel(orbModel);
        ViewRequest.addObjectView(
                new OrbView(
                        orbModel.getPosition(),
                        orbModel.getId()
                )
        );
    }

    public static void addEffect(EffectModel effect, EffectType effectType) {
        switch (effectType){
            case archmireEffect :
                ModelRequests.addEffectModel(effect);
                ViewRequest.addEffectView(new ArchmireEffectView(
                        effect.getArea(),
                        effect.getId()
                ));
                break;
            case BlackOrbEffect:
                ModelRequests.addEffectModel(effect);
                ViewRequest.addEffectView(new BlackOrbLaserEffectView(
                        effect.getArea(),
                        effect.getId()
                ));
                break;
        }
    }

    public static void addAbility(JSONObject jAbility, InGameAbilityType type) {
        String abilityString = jAbility.toString();
        InGameAbility ability;
        switch (type){
            case banish :
                ability = gson.fromJson(abilityString ,Banish.class);
                break;
            case empower:
                ability = gson.fromJson(abilityString ,Empower.class);
                break;
            case heal:
                ability = gson.fromJson(abilityString ,Heal.class);
                break;
            case dismay:
                ability = gson.fromJson(abilityString , Dismay.class);
                break;
            case slaughter:
                ability = gson.fromJson(abilityString ,Slaughter.class);
                break;
            default:
                ability = gson.fromJson(abilityString ,Slumber.class);
        }
        ability.setUp();
        InGameAbilityHandler.addAbility(ability);
    }

    public static void addSkillTree(JSONObject jAbility, SkillTreeAbilityType type) {
        String abilityString = jAbility.toString();
        SkillTreeAbility skillTreeAbility;
        switch (type) {
            case cerberus :
                skillTreeAbility = gson.fromJson(abilityString , Cerberus.class);
                break;
            case aceso:
                skillTreeAbility = gson.fromJson(abilityString , Aceso.class);
                break;
            case ares:
                skillTreeAbility = gson.fromJson(abilityString , Ares.class);
                break;
            case astrape:
                skillTreeAbility = gson.fromJson(abilityString , Astrape.class);
                break;
            case chiron:
                skillTreeAbility = gson.fromJson(abilityString , Chiron.class);
                break;
            case dolus:
                skillTreeAbility = gson.fromJson(abilityString , Dolus.class);
                break;
            case empusa:
                skillTreeAbility = gson.fromJson(abilityString , Empusa.class);
                break;
            case melampus:
                skillTreeAbility = gson.fromJson(abilityString , Melapmus.class);
                break;
            default:
                skillTreeAbility = gson.fromJson(abilityString , Proteus.class);
                break;
        }
        skillTreeAbility.setUp();
        SkillTreeAbilityHandler.addSkillTree(skillTreeAbility);
    }

    public static void addBoss(Boss boss) {
        ModelRequests.addAbstractEnemy(boss);
        addHelper(boss.getHead() ,boss);
        addHelper(boss.getLeftHand() ,boss);
        addHelper(boss.getRightHand() ,boss);
        boss.getHead().setInUse(false);
        boss.getLeftHand().setInUse(false);
        boss.getRightHand().setInUse(false);
        if (boss.getAttackPhase() == 2) {
            addHelper(boss.getPunch() ,boss);
            boss.getPunch().setInUse(false);
        }
        boss.getPunch().setImage(Constants.punch);
        boss.start();
    }

    public static void addHelper(BossHelperModel bossHelperModel ,Boss boss) {
        bossHelperModel.setFrame(findFrame(bossHelperModel.getFrame().getId()));
        ModelRequests.addObjectModel(bossHelperModel);
        bossHelperModel.setVelocity(0 ,0);
        bossHelperModel.setAcceleration(0 ,0);
        switch (bossHelperModel.getType()) {
            case hand :
                bossHelperModel.setImage(Constants.hand);
                ViewRequest.addObjectView(new HandView(
                        bossHelperModel.getPosition(),
                        bossHelperModel.getId()
                ));
                break;
            case head:
                bossHelperModel.setImage(Constants.smiley);
                ((HeadModel)bossHelperModel).setBoss(boss);
                ViewRequest.addObjectView(new HeadView(
                        bossHelperModel.getPosition(),
                        bossHelperModel.getId()
                ));
                break;
            case punch:
                bossHelperModel.setImage(Constants.punch);
                ViewRequest.addObjectView(new PunchView(
                        bossHelperModel.getPosition(),
                        bossHelperModel.getId()
                ));
        }
    }

    public static FrameModel findFrame(String id) {
        for (FrameModel frameModel : ModelData.getFrames()) {
            if (frameModel.getId().equals(id))
                return frameModel;
        }
        return null;
    }

    public synchronized static void addFrame(FrameModel frameModel) {
        ModelData.addFrame(frameModel);
        ViewRequest.addFrameView(new FrameView(
                frameModel.getPosition(),
                frameModel.getSize(),
                frameModel.getId()
        ));
    }
}
