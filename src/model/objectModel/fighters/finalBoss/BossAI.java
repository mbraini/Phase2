package model.objectModel.fighters.finalBoss;

import data.Constants;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

public class BossAI {

    private Boss boss;
    private EpsilonModel epsilonModel;


    public BossAI(Boss boss , EpsilonModel epsilonModel){
        this.boss = boss;
        this.epsilonModel = epsilonModel;
    }

    public boolean isInProjectileRange() {
        Vector distanceV = Math.VectorAdd(
                boss.getHead().getPosition(),
                Math.ScalarInVector(-1 ,epsilonModel.getPosition())
        );

        if (Math.VectorSize(distanceV) >= Constants.PROJECTILE_ACTIVATION_RADIOS)
            return true;

        return false;
    }

    public boolean isInSqueezePosition() {
        boolean isUnderHead = false;
        boolean isBetweenHands = false;
        if (epsilonModel.getPosition().y >= boss.getHead().getPosition().y){
            isUnderHead = true;
        }
        if (epsilonModel.getPosition().x <= boss.getRightHand().getPosition().x &&
                boss.getLeftHand().getPosition().x <= epsilonModel.getPosition().x)
        {
            double yMin = java.lang.Math.min(
                    boss.getRightHand().getFrame().getPosition().y ,
                    boss.getLeftHand().getFrame().getPosition().y
            );
            double yMax = java.lang.Math.max(
                    boss.getRightHand().getFrame().getPosition().y + boss.getRightHand().getFrame().getSize().height,
                    boss.getLeftHand().getFrame().getPosition().y + boss.getLeftHand().getFrame().getSize().height
            );
            double epsilonY = epsilonModel.getPosition().y;
            if (epsilonY <= yMax && epsilonY >= yMin)
                isBetweenHands = true;
        }

        return isUnderHead && isBetweenHands;
    }




}