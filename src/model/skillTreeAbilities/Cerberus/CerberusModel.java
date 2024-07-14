package model.skillTreeAbilities.Cerberus;

import controller.manager.loading.SkippedByJson;
import data.Constants;
import model.GameState;
import model.interfaces.CollisionDetector;
import model.interfaces.IsCircle;
import model.objectModel.FighterModel;
import model.objectModel.fighters.EnemyModel;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CerberusModel extends FighterModel implements IsCircle {
    @SkippedByJson
    private Timer timer;
    private int timePassed;
    private int coolDown;
    public CerberusModel(Vector position ,String id){
        this.id = id;
        this.position = position;
        velocity = new Vector();
        acceleration = new Vector();
        isHovering = true;
        hasMeleeAttack = true;
        meleeAttack = 10;
        coolDown = Constants.CERBERUS_COOLDOWN;
        setHP(100000);
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += 1000;
                if (timePassed >= Constants.CERBERUS_COOLDOWN){
                    hasMeleeAttack = true;
                    timePassed = 0;
                    timer.stop();
                }
            }
        });
    }

    @Override
    public void die() {

    }


    @Override
    public double getRadios() {
        return Constants.CERBERUS_RADIOS;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public void damageIf(EnemyModel enemyModel) {
        if (hasMeleeAttack){
            enemyModel.setHP(enemyModel.getHP() - meleeAttack);
            hasMeleeAttack = false;
            timer.start();
        }
    }
}
