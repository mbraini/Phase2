package model.logics;


import data.Constants;
import model.ModelData;
import model.animations.DashAnimation;
import model.interfaces.ImpactAble;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;
import view.soundEffects.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Impact {
    Vector collisionPoint;

    public Impact(Vector collisionPoint){
        this.collisionPoint = collisionPoint;
    }

    public void MakeImpact(){
        try {
            new Sound(Constants.impactSound).play();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        double distance;
        for (int i = 0; i < ModelData.getModels().size() ; i++){
            if (ModelData.getModels().get(i) instanceof ImpactAble) {
                Vector direction;
                direction = Math.VectorAdd(Math.ScalarInVector(-1, collisionPoint), ModelData.getModels().get(i).getPosition());
                distance = Math.VectorSize(direction);
                //////////////////todo
                if (distance >= Constants.IMPACT_AREA) {
                    continue;
                }
                //////////////////todo
                if (distance == 0)
                    continue;
                if (!(ModelData.getModels().get(i) instanceof EpsilonModel))
                    new DashAnimation(ModelData.getModels().get(i), direction ,Constants.DASH_TIME  ,Constants.DASH_DISTANCE ,Constants.DASH_ROTATION).StartAnimation();
                else
                    new DashAnimation(ModelData.getModels().get(i), direction ,Constants.DASH_TIME ,100 ,0).StartAnimation();
            }
        }
    }

}
