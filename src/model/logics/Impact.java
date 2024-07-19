package model.logics;


import constants.Constants;
import controller.Controller;
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
        for (int i = 0; i < Controller.getController(Controller.getIP()).getModelData().getModels().size() ; i++){
            if (Controller.getController(Controller.getIP()).getModelData().getModels().get(i) instanceof ImpactAble) {
                Vector direction;
                direction = Math.VectorAdd(Math.ScalarInVector(-1, collisionPoint), Controller.getController(Controller.getIP()).getModelData().getModels().get(i).getPosition());
                distance = Math.VectorSize(direction);
                //////////////////todo
                if (distance >= Constants.IMPACT_AREA) {
                    continue;
                }
                //////////////////todo
                if (distance == 0)
                    continue;
                if (!(Controller.getController(Controller.getIP()).getModelData().getModels().get(i) instanceof EpsilonModel))
                    new DashAnimation(
                            Controller.getController(Controller.getIP()).getModelData().getModels().get(i),
                            direction ,Constants.DASH_TIME,
                            Constants.DASH_DISTANCE ,
                            Constants.DASH_ROTATION,
                            false
                    ).StartAnimation();
                else
                    new DashAnimation(
                            Controller.getController(Controller.getIP()).getModelData().getModels().get(i),
                            direction ,
                            Constants.DASH_TIME ,
                            100 ,
                            0 ,
                            false
                    ).StartAnimation();
            }
        }
    }

}
