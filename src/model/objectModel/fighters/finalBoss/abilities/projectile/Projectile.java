package model.objectModel.fighters.finalBoss.abilities.projectile;

import model.ModelData;
import model.interfaces.Navigator;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.Boss;
import model.objectModel.fighters.finalBoss.abilities.Ability;

public class Projectile extends Ability implements Navigator {

    private Boss boss;
    private EpsilonModel epsilon;
    private boolean arrived;
    private ProjectileThread thread;
    private final ProjectileNavigator navigator;

    public Projectile(Boss boss){
        this.boss = boss;
        synchronized (ModelData.getModels()) {
            this.epsilon = (EpsilonModel) ModelData.getModels().getFirst();
        }
        navigator = new ProjectileNavigator(epsilon ,boss.getHead());
        thread = new ProjectileThread(this ,epsilon);
    }


    @Override
    protected void ownHelpers() {
        boss.getHead().setInUse(true);
    }

    @Override
    public void activate() {
        ownHelpers();
        thread.start();
    }

    @Override
    protected void endAbility() {

    }

    @Override
    public boolean hasArrived() {
        return arrived;
    }

    @Override
    public void navigate() {
        navigator.navigate();
    }

    public void checkArrived() {
        arrived = navigator.hasArrived();
    }

}
