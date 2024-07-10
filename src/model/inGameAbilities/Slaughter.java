package model.inGameAbilities;

import controller.enums.InGameAbilityType;
import model.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Slaughter extends InGameAbility{

    private int timePassed;
    private Timer timer;

    public Slaughter(){
        type = InGameAbilityType.slaughter;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += 1000;
                if (timePassed >= 120000){
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
        timer.start();
    }
}
