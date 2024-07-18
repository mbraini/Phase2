package model.inGameAbilities;

import controller.enums.InGameAbilityType;
import controller.manager.loading.SkippedByJson;
import constants.Constants;
import controller.manager.GameState;
import model.viewRequests.ShootRequest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Empower extends InGameAbility{
    @SkippedByJson
    private Timer timer;
    private int timePassed;

    public Empower(){
        type = InGameAbilityType.empower;
        xpCost = 75;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(Constants.IN_GAME_ABILITY_TIMER_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += Constants.IN_GAME_ABILITY_TIMER_REFRESH_RATE;
                if (timePassed >= Constants.EMPOWER_DURATION) {
                    isAvailable = true;
                    isActive = false;
                    ShootRequest.setExtraAim(0);
                    timePassed = 0;
                    timer.stop();
                }
            }
        });
    }

    @Override
    public void performAbility() {
        ShootRequest.setExtraAim(2);
        isActive = true;
        isAvailable = false;
        timer.start();
    }

    @Override
    public void setUp() {
        initTimer();
        if (timePassed <= Constants.EMPOWER_DURATION && isActive) {
            ShootRequest.setExtraAim(2);
            timer.start();
        }
    }
}
