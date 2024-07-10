package model.inGameAbilities;

import controller.enums.InGameAbilityType;
import controller.manager.Spawner;
import model.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slumber extends InGameAbility{

    private int timePassed;
    private Timer timer;

    public Slumber(){
        type = InGameAbilityType.slumber;
        xpCost = 150;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += 1000;
                if (timePassed >= 10000){
                    isAvailable = true;
                    isActive = false;
                    timePassed = 0;
                    GameState.setDizzy(false);
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
        timer.start();
    }
}
