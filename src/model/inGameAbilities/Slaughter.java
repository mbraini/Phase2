package model.inGameAbilities;

import controller.enums.InGameAbilityType;
import controller.manager.loading.SkippedByJson;
import constants.Constants;
import controller.manager.GameState;
import model.viewRequests.ShootRequest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slaughter extends InGameAbility{

    private int timePassed;
    private boolean isUsed;
    @SkippedByJson
    private Timer timer;

    public Slaughter(){
        type = InGameAbilityType.slaughter;
        xpCost = 200;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(Constants.IN_GAME_ABILITY_TIMER_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += Constants.IN_GAME_ABILITY_TIMER_REFRESH_RATE;
                if (timePassed >= Constants.SLAUGHTER_COOLDOWN){
                    isAvailable = true;
                    isActive = false;
                    timePassed = 0;
                    timer.stop();
                }
            }
        });
    }


    @Override
    public void performAbility() {
        isActive = true;
        isAvailable = false;
        isUsed = false;
        ShootRequest.setSlaughterBulletCount(1);
        timer.start();
    }

    @Override
    public void setUp() {
        initTimer();
        if (timePassed <= Constants.SLAUGHTER_COOLDOWN && isActive){
            if (!isUsed){
                ShootRequest.setSlaughterBulletCount(
                        ShootRequest.getSlaughterBulletCount() + 1
                );
            }
            timer.start();
        }
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
