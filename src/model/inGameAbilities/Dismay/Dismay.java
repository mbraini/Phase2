package model.inGameAbilities.Dismay;

import controller.enums.InGameAbilityType;
import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.inGameAbilities.InGameAbility;
import model.objectModel.fighters.EpsilonModel;
import utils.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dismay extends InGameAbility {

    private int timePassed;
    private EpsilonProtectorModel protectorModel;
    private EpsilonModel epsilon;
    private Timer timer;

    public Dismay(EpsilonModel epsilon){
        this.epsilon = epsilon;
        type = InGameAbilityType.dismay;
        xpCost = 120;
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
                    protectorModel.die();
                    timer.stop();
                }
            }
        });
        protectorModel = new EpsilonProtectorModel(
                epsilon,
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
    }

    @Override
    public void performAbility() {
        Spawner.spawnProtector(protectorModel);
        isActive = true;
        isAvailable = false;
        timer.start();
    }
}
