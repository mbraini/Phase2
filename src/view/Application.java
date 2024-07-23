package view;

import constants.Constants;
import controller.Controller;
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
        Controller.rumModel();
        runView();
    }


    private void runView() {
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
            Constants.endGameImage = ImageIO.read(new File("src/view/objectViews/images/GameOver.png"));
            Constants.omenoct = ImageIO.read(new File("src/view/objectViews/images/omenoct.png"));
            Constants.orb = ImageIO.read(new File("src/view/objectViews/images/orb.png"));
            Constants.smiley = ImageIO.read(new File("src/view/objectViews/images/smiley.png"));
            Constants.hand = ImageIO.read(new File("src/view/objectViews/images/hand.png"));
            Constants.bossAoe = ImageIO.read(new File("src/view/objectViews/images/bossAoe.png"));
            Constants.punch = ImageIO.read(new File("src/view/objectViews/images/punch.png"));
            Constants.slumber = ImageIO.read(new File("src/view/objectViews/images/slumber.png"));
            Constants.archmire = ImageIO.read(new File("src/view/objectViews/images/archmire.png"));
            Constants.skeleton = ImageIO.read(new File("src/view/objectViews/images/skeleton.png"));
            Constants.barricados = ImageIO.read(new File("src/view/objectViews/images/barricados.png"));
            Constants.wyrm = ImageIO.read(new File("src/view/objectViews/images/wyrm.png"));
            Constants.portal = ImageIO.read(new File("src/view/objectViews/images/portal.png"));
            Constants.slaughter = ImageIO.read(new File("src/view/objectViews/images/slaughter.png"));
            Constants.dismay = ImageIO.read(new File("src/view/objectViews/images/dismay.png"));
            Constants.necropick = ImageIO.read(new File("src/view/objectViews/images/necropick.png"));
            Constants.ares = ImageIO.read(new File("src/view/objectViews/images/ares.png"));
            Constants.astrape = ImageIO.read(new File("src/view/objectViews/images/astrape.png"));
            Constants.cerberus = ImageIO.read(new File("src/view/objectViews/images/cerberus.png"));
            Constants.aceso = ImageIO.read(new File("src/view/objectViews/images/aceso.png"));
            Constants.melampus = ImageIO.read(new File("src/view/objectViews/images/melampus.png"));
            Constants.chiron = ImageIO.read(new File("src/view/objectViews/images/chiron.png"));
            Constants.athena = ImageIO.read(new File("src/view/objectViews/images/athena.png"));
            Constants.proteus = ImageIO.read(new File("src/view/objectViews/images/proteus.png"));
            Constants.empusa = ImageIO.read(new File("src/view/objectViews/images/empusa.png"));
            Constants.dolus = ImageIO.read(new File("src/view/objectViews/images/dolus.png"));
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
