package model.animations;


import model.GameState;
import model.interfaces.ImpactAble;
import model.objectModel.ObjectModel;
import utils.Math;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DashAnimation extends Animation implements ActionListener {
    ObjectModel oigModel;
    Vector direction;
    int time;
    double distance;
    double theta;
    Timer timer;
    static HashMap<ObjectModel ,Timer> dashes = new HashMap<>();

    public DashAnimation(ObjectModel oigModel, Vector direction, int time, double distance ,double theta) {
        this.oigModel = oigModel;
        this.direction = Math.VectorWithSize(direction ,1);
        this.time = time;
        this.distance = distance;
        this.theta = theta;
    }

    @Override
    public void StartAnimation() {
        if (dashes.containsKey(oigModel)){
            StopTimer(dashes.get(oigModel));
            dashes.remove(oigModel);
        }

        /////////////////////////////Calculation
        double a = (2 * distance) / java.lang.Math.pow(time ,2);
        oigModel.setVelocity(Math.ScalarInVector(time * a ,direction));
        oigModel.setAcceleration(Math.ScalarInVector(-a ,direction));

        double alpha = (2 * theta) / java.lang.Math.pow(time ,2);
        oigModel.setOmega(time * alpha);
        oigModel.setAlpha(-alpha);
        //////////////////////////////

        if (oigModel instanceof ImpactAble) {
            ((ImpactAble) oigModel).setImpacted(true);
        }

        timer = new Timer(time, this);
        dashes.put(oigModel ,timer);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (GameState.isPause())
            return;
        StopTimer(timer);
    }

    void StopTimer(Timer timer){
        timer.removeActionListener(this);
        timer.stop();
        if (oigModel instanceof ImpactAble) {
            ((ImpactAble) oigModel).setImpacted(false);
        }
        oigModel.setAcceleration(0 ,0);
        oigModel.setVelocity(0 ,0);

        oigModel.setAlpha(0);
        oigModel.setOmega(0);
    }
}
