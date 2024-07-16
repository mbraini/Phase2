package model.objectModel.fighters.finalBoss.abilities.vomit;

import controller.Controller;
import controller.manager.loading.SkippedByJson;
import data.Constants;
import model.interfaces.Fader;
import model.interfaces.IsCircle;
import model.logics.Impact;
import model.objectModel.effects.AoeEffectModel;
import model.objectModel.effects.EffectModel;
import utils.Vector;
import utils.area.Circle;

public class BossAoeEffectModel extends AoeEffectModel implements Fader , IsCircle {
    private double time;
    @SkippedByJson
    private Vomit vomit;
    @SkippedByJson
    private VomitThread thread;

    public BossAoeEffectModel(Vector center ,VomitThread thread ,Vomit vomit, String id){
        this.id = id;
        this.vomit = vomit;
        this.thread = thread;
        area = new Circle(Constants.VOMIT_RADIOS ,center);
    }

    @Override
    public void die() {
        Controller.removeEffect(this);
        synchronized (vomit.getEffects()){
            vomit.removeEffect(id);
        }
        thread.dealDamage(this);
        new Impact(((Circle)area).getCenter()).MakeImpact();
    }

    @Override
    public void addTime(double time) {
        this.time += time;
    }

    @Override
    public void fadeIf() {
        if (time >= 3000)
            die();
    }

    @Override
    public double getRadios() {
        return Constants.VOMIT_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return ((Circle) area).getCenter();
    }
}
