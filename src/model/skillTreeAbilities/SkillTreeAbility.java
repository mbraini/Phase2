package model.skillTreeAbilities;

import controller.enums.SkillTreeAbilityType;
import controller.manager.loading.SkippedByJson;
import data.Constants;
import model.GameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class SkillTreeAbility {

    protected boolean isBought;       /////////////////////// fix later !
    protected int inGameXpCost = 100;
    protected int inGameCoolDownTime = 10000;
    protected boolean canCast = true;
    @SkippedByJson////////////////////// fix later !
    protected Timer coolDownTimer;
    protected int coolDownTimePassed;
    protected int unlockXpCost;
    protected SkillTreeAbilityType type;

    protected abstract void cast();

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public int getInGameXpCost() {
        return inGameXpCost;
    }

    public void setInGameXpCost(int inGameXpCost) {
        this.inGameXpCost = inGameXpCost;
    }

    public int getInGameCoolDownTime() {
        return inGameCoolDownTime;
    }

    public void setInGameCoolDownTime(int inGameCoolDownTime) {
        this.inGameCoolDownTime = inGameCoolDownTime;
    }

    public boolean CanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    public Timer getCoolDownTimer() {
        return coolDownTimer;
    }

    public void setCoolDownTimer(Timer coolDownTimer) {
        this.coolDownTimer = coolDownTimer;
    }

    public int getCoolDownTimePassed() {
        return coolDownTimePassed;
    }

    public void setCoolDownTimePassed(int coolDownTimePassed) {
        this.coolDownTimePassed = coolDownTimePassed;
    }

    public int getUnlockXpCost() {
        return unlockXpCost;
    }

    public void setUnlockXpCost(int unlockXpCost) {
        this.unlockXpCost = unlockXpCost;
    }

    public SkillTreeAbilityType getType() {
        return type;
    }

    public void setType(SkillTreeAbilityType type) {
        this.type = type;
    }

    protected void stop() {
        coolDownTimer.stop();
        canCast = true;
    }

    public void setUp(){
        initTimer();
        if (coolDownTimePassed >= 0 && !canCast) {
            coolDownTimer.start();
        }
    }

    protected void initTimer() {
        coolDownTimer = new Timer(Constants.SKILL_TREE_ABILITY_TIMER_REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (GameState.isPause())
                    return;
                coolDownTimePassed += Constants.SKILL_TREE_ABILITY_TIMER_REFRESH_RATE;
                if (coolDownTimePassed >= inGameCoolDownTime){
                    canCast = true;
                    coolDownTimePassed = 0;
                    coolDownTimer.stop();
                }
            }
        });
    }

}
