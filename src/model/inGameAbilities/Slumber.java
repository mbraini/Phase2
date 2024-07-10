package model.inGameAbilities;

import controller.enums.InGameAbilityType;
import controller.manager.loading.SkippedByJson;
import data.Constants;
import model.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slumber extends InGameAbility{

    private int timePassed;
    @SkippedByJson
    private Timer timer;

    public Slumber(){
        type = InGameAbilityType.slumber;
        xpCost = 150;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(Constants.IN_GAME_ABILITY_TIMER_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += Constants.IN_GAME_ABILITY_TIMER_REFRESH_RATE;
                if (timePassed >= Constants.SLUMBER_DURATION){
                    isAvailable = true;
                    isActive = false;
                    timePassed = 0;
                    GameState.setDizzy(false);
                    InGameAbilityHandler.permitAll();
                    timer.stop();
                }
            }
        });
    }


    @Override
    public void performAbility() {
        isActive = true;
        isAvailable = false;
        GameState.setDizzy(true);
        InGameAbilityHandler.disableAll();
        timer.start();
    }

    @Override
    public void setUp() {
        initTimer();
        if (timePassed <= Constants.SLUMBER_DURATION && isActive) {
            GameState.setDizzy(true);
            timer.start();
        }
    }
}
