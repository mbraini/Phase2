package controller.manager;

import model.objectModel.ObjectModel;

import java.util.ArrayList;

public class GameState {

    private double time;
    private double xp = 1000;
    private double hp;
    private int wave = 1;
    private int enemyKilled;
    private int totalBullets;
    private int successfulBullets;
    private int enemyCount;
    private boolean isPause;
    private boolean isOver;
    private boolean isDizzy;

    public GameState(){
        /////todo
    }


    public void reset(){
        time = 0;
        hp = 100;
        wave = 1;
        isPause = false;
        isOver = false;
    }


    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean isOver) {
        this.isOver = isOver;
    }

    public void update(ArrayList<ObjectModel> models ,double time) {
        setHp(models.getFirst().getHP());
        setTime(time);
    }

    public boolean isDizzy() {
        return isDizzy;
    }

    public void setDizzy(boolean isDizzy) {
        this.isDizzy = isDizzy;
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public int getTotalBullets() {
        return totalBullets;
    }

    public void setTotalBullets(int totalBullets) {
        this.totalBullets = totalBullets;
    }

    public int getSuccessfulBullets() {
        return successfulBullets;
    }

    public void setSuccessfulBullets(int successfulBullets) {
        this.successfulBullets = successfulBullets;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

}
