package model;

public class GameState {

    private static double time;
    private static double xp;
    private static double hp;
    private static int wave;
    private static boolean isPause;
    private static boolean isOver;

    public GameState(){
        /////todo
    }


    public static void reset(){
        time = 0;
        hp = 100;
        wave = 1;
        isPause = false;
        isOver = false;
    }


    public static double getTime() {
        return time;
    }

    public static void setTime(double time) {
        GameState.time = time;
    }

    public static double getXp() {
        return xp;
    }

    public static void setXp(double xp) {
        GameState.xp = xp;
    }

    public static double getHp() {
        return hp;
    }

    public static void setHp(double hp) {
        GameState.hp = hp;
    }

    public static int getWave() {
        return wave;
    }

    public static void setWave(int wave) {
        GameState.wave = wave;
    }

    public static boolean isPause() {
        return isPause;
    }

    public static void setPause(boolean isPause) {
        GameState.isPause = isPause;
    }

    public static boolean isOver() {
        return isOver;
    }

    public static void setOver(boolean isOver) {
        GameState.isOver = isOver;
    }
}
