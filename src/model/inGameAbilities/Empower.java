package model.inGameAbilities;

import controller.enums.InGameAbilityType;
import controller.listeners.EpsilonAiming;
import data.Constants;
import model.GameState;
import model.viewRequests.ShootRequest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Empower extends InGameAbility{

    private Timer timer;
    private int timePassed;

    public Empower(){
        type = InGameAbilityType.empower;
        xpCost = 75;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                timePassed += 1000;
                if (timePassed >= 10000) {
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
}
