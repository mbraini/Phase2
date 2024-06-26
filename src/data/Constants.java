package data;

import java.awt.*;

public class Constants {

    public final static int FPS = 10;
    public static final int UPS = 10;
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();



    public static final int GAME_HEIGHT = 700;
    public static final int GAME_WIDTH = 700;
    public static final Dimension barD = new Dimension(14,37);
    public static final int TRIGORATH_DAMAGE = 15;
    public static final int SQURANTINE_DAMAGE = 10;
    public static final int OMENOCT_FIRING_TIME = 2000;
    public static final double OMENOCT_NAVIGATE_VELOCITY = 0.1;
    public static final double OMENOCT_BULLET_VELOCITY = 0.6;
    public static final double OMENOCT_RADIOS = 30;
    public static final double OMENOCT_BULLET_RADIOUS = 8;
    public static final double FRAME_SHRINKAGE_DISTANCE = 50;
    public static final double NECROPICK_SPAWN_RADIOS = 300;
    public static final double NECROPCIK_NAVIGATION_VELOCITY = 1;
    public static final Dimension NECROPICK_DIMENSION = new Dimension(50 ,50);
    public static final Dimension OMENOCT_DIMENTION = new Dimension(50 ,50);
    public static final Object NECROPCIK_BULLET_RADIOS = 5;
    public static final Dimension ARCHMIRE_DIMENSION = new Dimension(100 ,100);
    public static Dimension EPSILON_DIMENSION = new Dimension(25 ,25);
    public static final Dimension EPSILON_FINAL_DIMENSION = new Dimension(25 ,25);
    public static Dimension TRIGORATH_DIMENTION = new Dimension(50 ,50);
    public static Dimension Squarantine_DIMENTION = new Dimension(50 ,50);
    public static final double EPSILON_BULLET_VELOCITY = 0.6;
    public static final double EPSILON_BULLET_DIAMETER = 10;
    public static final int ID_SIZE = 10;
    public static final double EPSILON_DAMAGE = 5;
    public static final int MELEI_ATTACK = 10;
    public static final Dimension MINIMUM_FRAME_DIMENSION = new Dimension(200 + barD.width ,200 + barD.height);
    public static final double IMPACT_AREA = 200;
    public static final int ENEMY_SPAWN_MARGIN = 500;
    public static final double DASH_DISTANCE = 100;
    public static final double DASH_ROTATION = Math.PI;
    public static final double EPSILON_DECELERATION = 0.0001;
    public static Image epsilonImage;
    public static Image trigorathImage;
    public static Image squarantineImage;
    public static Image empower;
    public static Image heal;
    public static Image banish;
    public static final double ENEMY_LINEAR_SPEED = 0.2;
    public static final double COLLECTIVE_RADIOS = 5;
    public static final int COLLECTIVE_FADE = 7000;
    public static final double COLLECTIVE_VELOCITY = 0.6;
    public static final int COLLECTIVE_ABILITY_ACTIVATION_RADIOS = 100;

    public static final double ENEMY_ROTATION_SPEED = Math.PI * 2 / 1000;
    public static final int DASH_TIME = 500;
    public static final double AIMING_PAUSE_TIME = 100;
    public static final int FRAME_ANIMATION_REFRESH_RATE = 10;
    public static final int FRAME_BULLET_RESIZE = 50;
    public static final int FRAME_SHRINKAGE_TIME = 200;
    public static final double FRAME_SHRINKAGE_VELOCITY = 0.01;
    public static final Dimension COLLECTIVE_BOX_DIMENSION = new Dimension(100 ,100);
    public static final int EPSILON_VERTICES_RADIOS = 3;
    public static final String GAME_DIFFICULTY = "HARD";
    public static String backGroundSound;
    public static String BulletFiredSound;
    public static String waveSpawnSound;
    public static final int WIN_ANIMATION_TIME = 3000;
    public static final int WIN_ANIMATION_REFRESH_RATE = 10;
    public static String enemyOnDeathSound;
    public static String impactSound;
    public static Image endGameImage;
    public static Image ares;
    public static Image aceso;
    public static Image proteus;
    public static Image omenoct;
    public static String winSound;
    public static String endSound;

}
