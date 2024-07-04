package view;

import data.Constants;
import view.soundEffects.Sound;
import view.menuPanels.MainFrame;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Application implements Runnable{
    private static MainFrame mainFrame;

    @Override
    public void run() {
        getImages();
        getAudios();
        startMainFrame();
    }

    private void getImages(){
        try {
            Constants.epsilonImage = ImageIO.read(new File("src/view/objectViews/images/epsilonImage.png"));
            Constants.trigorathImage = ImageIO.read(new File("src/view/objectViews/images/trigorathImage.png"));
            Constants.squarantineImage = ImageIO.read(new File("src/view/objectViews/images/squarantineImage.png"));
            Constants.banish = ImageIO.read(new File("src/view/objectViews/images/Banish.png"));
            Constants.empower = ImageIO.read(new File("src/view/objectViews/images/Empower.png"));
            Constants.heal = ImageIO.read(new File("src/view/objectViews/images/Heal.png"));
            Constants.ares = ImageIO.read(new File("src/view/objectViews/images/Ares.png"));
            Constants.aceso = ImageIO.read(new File("src/view/objectViews/images/Aceso.png"));
            Constants.proteus = ImageIO.read(new File("src/view/objectViews/images/Proteus.png"));
            Constants.endGameImage = ImageIO.read(new File("src/view/objectViews/images/GameOver.png"));
            Constants.omenoct = ImageIO.read(new File("src/view/objectViews/images/omenoct.png"));
            Constants.orb = ImageIO.read(new File("src/view/objectViews/images/orb.png"));
            Constants.smiley = ImageIO.read(new File("src/view/objectViews/images/smiley.png"));
            Constants.hand = ImageIO.read(new File("src/view/objectViews/images/hand.png"));
            Constants.bossAoe = ImageIO.read(new File("src/view/objectViews/images/bossAoe.png"));
            Constants.punch = ImageIO.read(new File("src/view/objectViews/images/punch.png"));
        }
        catch (Exception e){
            System.out.println("File Not Found!");
        }
    }

    private void getAudios() {
        Constants.backGroundSound = "src/view/SoundEffects/Song.wav";
        Constants.BulletFiredSound = "src/view/SoundEffects/Bullet Fired.wav";
        Constants.waveSpawnSound = "src/view/SoundEffects/Wave Spawn.wav";
        Constants.enemyOnDeathSound = "src/view/SoundEffects/EnemyOnDeath.wav";
        Constants.impactSound = "src/view/SoundEffects/ImpactSound.wav";
        Constants.endSound = "src/view/SoundEffects/endSound.wav";
        Constants.winSound = "src/view/SoundEffects/winSound.wav";
        Sound sound = null;
        try {
            sound = new Sound(Constants.backGroundSound);
            Sound.volumeUp();
            Sound.volumeDown();
            sound.loop();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static void endMainFrame(){
        mainFrame.dispose();
    }

    public static void startMainFrame(){
        mainFrame = new MainFrame();
    }


}
