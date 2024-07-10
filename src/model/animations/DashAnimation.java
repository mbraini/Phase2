package model.animations;


import data.Constants;
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
    int timePassed;
    double distance;
    double theta;
    Timer timer;
    static HashMap<ObjectModel ,Timer> dashes = new HashMap<>();
    boolean grow;

    public DashAnimation(ObjectModel oigModel, Vector direction, int time, double distance ,double theta ,boolean grow) {
        this.oigModel = oigModel;
        this.direction = Math.VectorWithSize(direction ,1);
        this.time = time;
        this.distance = distance;
        this.theta = theta;
        this.grow = grow;
    }

    @Override
    public void StartAnimation() {
        if (dashes.containsKey(oigModel)){
            StopTimer(dashes.get(oigModel));
            dashes.remove(oigModel);
        }

        /////////////////////////////Calculation
        double a = (2 * distance) / java.lang.Math.pow(time, 2);
        double alpha = (2 * theta) / java.lang.Math.pow(time, 2);
        if (!grow) {
            oigModel.setVelocity(Math.ScalarInVector(time * a, direction));
            oigModel.setAcceleration(Math.ScalarInVector(-a, direction));
            oigModel.setOmega(time * alpha);
            oigModel.setAlpha(-alpha);
        }
        else {
            oigModel.setVelocity(0 ,0);
            oigModel.setAcceleration(Math.ScalarInVector(a ,direction));
            oigModel.setOmega(0);
            oigModel.setAlpha(alpha);
        }
        //////////////////////////////

        if (oigModel instanceof ImpactAble) {
            ((ImpactAble) oigModel).setImpacted(true);
        }

        timer = new Timer(Constants.DASH_TIMER_REFRESH_RATE, this);
        dashes.put(oigModel ,timer);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (GameState.isPause()) {
            return;
        }
        timePassed += Constants.DASH_TIMER_REFRESH_RATE;
        if (timePassed >= time)
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
