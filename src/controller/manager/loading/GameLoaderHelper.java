package controller.manager.loading;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.enums.EffectType;
import controller.enums.ModelType;
import controller.manager.Spawner;
import model.ModelData;
import model.ModelRequests;
import model.objectModel.ObjectModel;
import model.objectModel.effects.ArchmireAoeEffectModel;
import model.objectModel.effects.BlackOrbAoeEffectModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.basicEnemies.SquarantineModel;
import model.objectModel.fighters.basicEnemies.TrigorathModel;
import model.objectModel.fighters.miniBossEnemies.barricadosModel.BarricadosSecondModel;
import model.objectModel.fighters.miniBossEnemies.blackOrbModel.BlackOrbModel;
import model.objectModel.fighters.miniBossEnemies.blackOrbModel.OrbModel;
import model.objectModel.fighters.normalEnemies.archmireModel.ArchmireModel;
import model.objectModel.fighters.normalEnemies.necropickModel.NecropickModel;
import model.objectModel.fighters.normalEnemies.omenoctModel.OmenoctModel;
import model.objectModel.fighters.normalEnemies.wyrmModel.WyrmModel;
import model.objectModel.frameModel.FrameModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.area.Polygon;
import view.ViewRequest;
import view.objectViews.EpsilonView;
import view.objectViews.basicEnemyView.SquarantineView;
import view.objectViews.basicEnemyView.TrigorathView;
import view.objectViews.miniBossEnemyView.BarricadosView;
import view.objectViews.miniBossEnemyView.BlackOrbLaserEffectView;
import view.objectViews.miniBossEnemyView.OrbView;
import view.objectViews.normalEnemyView.NecropickView;
import view.objectViews.normalEnemyView.OmenoctView;
import view.objectViews.normalEnemyView.WyrmView;
import view.objectViews.normalEnemyView.archmireView.ArchmireEffectView;
import view.objectViews.normalEnemyView.archmireView.ArchmireView;

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
        ObjectModel model;
        if (type == null)
            return;
        switch (type){
            case epsilon :
                model = gson.fromJson(jsonString , EpsilonModel.class);
                ModelData.addModel(model);
                ViewRequest.addObjectView(
                        new EpsilonView(
                                model.getPosition() ,
                                model.getId()
                        )
                );
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
            case necropick:
                model = gson.fromJson(jsonString , NecropickModel.class);
                ModelRequests.addObjectModel(model);
                ((NecropickModel) model).start();
                ViewRequest.addObjectView(new NecropickView(
                        model.getPosition(),
                        model.getId()
                ));
            case archmire:
                model = gson.fromJson(jsonString , ArchmireModel.class);
                try {
                    JSONArray aoeArray = jsonObject.getJSONArray("aoeEffects");
                    for (int i = 0 ;i < aoeArray.length() ;i++){
                        String area = aoeArray.getJSONObject(i).get("area").toString();
                        Polygon polygon = gson.fromJson(area ,Polygon.class);
                        ((ArchmireModel) model).getAoeEffects().get(i).setArchmire((ArchmireModel) model);
                        ((ArchmireModel) model).getAoeEffects().get(i).setArea(polygon);
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
            case wyrm:
                model = gson.fromJson(jsonString , WyrmModel.class);
                ModelRequests.addObjectModel(model);
                GameLoader.addFrame(((WyrmModel) model).getFrameModel());
                ((WyrmModel) model).start();
                ViewRequest.addObjectView(new WyrmView(
                        model.getPosition(),
                        model.getId()
                ));
        }
    }

    public synchronized static void addWyrm(ArchmireModel wyrm, FrameModel frameModel){
        ModelRequests.addObjectModel(wyrm);
        ViewRequest.addObjectView(new WyrmView(
                wyrm.getPosition(),
                wyrm.getId()
        ));

        Spawner.addFrame(frameModel);
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
            addBlackOrbEffect(effectModel);
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

    public synchronized static void addBlackOrbEffect(BlackOrbAoeEffectModel effectModel){
        ModelRequests.addEffectModel(effectModel);
        ViewRequest.addEffectView(new BlackOrbLaserEffectView(
                effectModel.getArea(),
                effectModel.getId()
        ));
    }

    public synchronized static void addBarricadosTheFirst(BarricadosSecondModel barricados){
        ModelRequests.addObjectModel(barricados);
        ViewRequest.addObjectView(new BarricadosView(
                barricados.getPosition(),
                barricados.getId()
        ));
    }

    public synchronized static void addBarricadosTheSecond(BarricadosSecondModel barricados ,FrameModel frameModel){
        ModelRequests.addObjectModel(barricados);
        ViewRequest.addObjectView(new BarricadosView(
                barricados.getPosition(),
                barricados.getId()
        ));

        Spawner.addFrame(frameModel);
    }

    public static void addEffect(EffectModel effect, EffectType effectType) {
        switch (effectType){
            case archmireEffect :
                effect.setArea((Polygon) effect.getArea());
                ModelRequests.addEffectModel(effect);
                ViewRequest.addEffectView(new ArchmireEffectView(
                        effect.getArea(),
                        effect.getId()
                ));
                break;
        }
    }
}
