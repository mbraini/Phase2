package model.objectModel.normalEnemies.wyrmModel;

import data.Constants;
import model.ModelData;
import utils.Math;
import utils.Vector;

public class WyrmNavigator {
    private WyrmModel wyrmModel;
    public WyrmNavigator(WyrmModel wyrmModel){
        this.wyrmModel = wyrmModel;
    }

    public void navigate() {
        Vector epsilonPosition = ModelData.getModels().getFirst().getPosition();
        Vector direction = Math.VectorAdd(
                Math.ScalarInVector(-1 ,wyrmModel.getPosition()),
                epsilonPosition
        );
        wyrmModel.setVelocity(
                Math.VectorWithSize(
                        direction,
                        Constants.WYRM_NAVIGATION_VELOCITY
                )
        );

        Vector xVector = new Vector(1 ,0);
        double dotProduct = Math.DotProduct(epsilonPosition ,xVector);
        double cosTheta = dotProduct / Math.VectorSize(epsilonPosition);
        wyrmModel.setTheta(java.lang.Math.acos(cosTheta));
    }

    public boolean hasArrived() {
        Vector epsilonPosition = ModelData.getModels().getFirst().getPosition();
        Vector distance = Math.VectorAdd(
                epsilonPosition,
                Math.ScalarInVector(-1 ,wyrmModel.getPosition())
        );
        if (java.lang.Math.abs(Math.VectorSize(distance) - Constants.WYRM_NAVIGATION_RADIOS)
                <= Math.VectorSize(wyrmModel.getVelocity()) * Constants.UPS){
            return true;
        }
        return false;
    }
}
