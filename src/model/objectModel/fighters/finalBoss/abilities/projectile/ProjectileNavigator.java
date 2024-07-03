package model.objectModel.fighters.finalBoss.abilities.projectile;

import data.Constants;
import model.objectModel.fighters.EpsilonModel;
import model.objectModel.fighters.finalBoss.bossHelper.HeadModel;
import utils.Math;
import utils.Vector;

public class ProjectileNavigator {

    private EpsilonModel epsilon;
    private HeadModel head;

    public ProjectileNavigator(EpsilonModel epsilon ,HeadModel head){
        this.head = head;
        this.epsilon = epsilon;
    }

    public void navigate(){

        Vector distance = Math.VectorAdd(
                epsilon.getPosition(),
                Math.ScalarInVector(-1 ,head.getPosition())
        );

        Vector velocity = Math.VectorWithSize(
                distance,
                Constants.PROJECTILE_NAVIGATE_VELOCITY
        );

        if (Math.VectorSize(distance) <= Constants.PROJECTILE_NAVIGATE_RADIOS)
            velocity = Math.ScalarInVector(-1 , velocity);
        head.setVelocity(velocity);

    }

    public boolean hasArrived(){
        Vector distance = Math.VectorAdd(
                epsilon.getPosition(),
                Math.ScalarInVector(-1 ,head.getPosition())
        );
        double dist = Constants.PROJECTILE_NAVIGATE_VELOCITY * Constants.UPS
                + Constants.PROJECTILE_NAVIGATE_RADIOS;
        if (Math.VectorSize(distance) <= dist)
            return true;
        return false;
    }


}
