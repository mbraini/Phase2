package view;

import constants.ImageConstants;
import constants.SoundPathConstants;
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
            ImageConstants.epsilonImage = ImageIO.read(new File("src/view/objectViews/images/epsilonImage.png"));
            ImageConstants.trigorathImage = ImageIO.read(new File("src/view/objectViews/images/trigorathImage.png"));
            ImageConstants.squarantineImage = ImageIO.read(new File("src/view/objectViews/images/squarantineImage.png"));
            ImageConstants.banish = ImageIO.read(new File("src/view/objectViews/images/Banish.png"));
            ImageConstants.empower = ImageIO.read(new File("src/view/objectViews/images/Empower.png"));
            ImageConstants.heal = ImageIO.read(new File("src/view/objectViews/images/Heal.png"));
            ImageConstants.endGameImage = ImageIO.read(new File("src/view/objectViews/images/GameOver.png"));
            ImageConstants.omenoct = ImageIO.read(new File("src/view/objectViews/images/omenoct.png"));
            ImageConstants.orb = ImageIO.read(new File("src/view/objectViews/images/orb.png"));
            ImageConstants.smiley = ImageIO.read(new File("src/view/objectViews/images/smiley.png"));
            ImageConstants.hand = ImageIO.read(new File("src/view/objectViews/images/hand.png"));
            ImageConstants.bossAoe = ImageIO.read(new File("src/view/objectViews/images/bossAoe.png"));
            ImageConstants.punch = ImageIO.read(new File("src/view/objectViews/images/punch.png"));
            ImageConstants.slumber = ImageIO.read(new File("src/view/objectViews/images/slumber.png"));
            ImageConstants.archmire = ImageIO.read(new File("src/view/objectViews/images/archmire.png"));
            ImageConstants.skeleton = ImageIO.read(new File("src/view/objectViews/images/skeleton.png"));
            ImageConstants.barricados = ImageIO.read(new File("src/view/objectViews/images/barricados.png"));
            ImageConstants.wyrm = ImageIO.read(new File("src/view/objectViews/images/wyrm.png"));
            ImageConstants.portal = ImageIO.read(new File("src/view/objectViews/images/portal.png"));
            ImageConstants.slaughter = ImageIO.read(new File("src/view/objectViews/images/slaughter.png"));
            ImageConstants.dismay = ImageIO.read(new File("src/view/objectViews/images/dismay.png"));
            ImageConstants.necropick = ImageIO.read(new File("src/view/objectViews/images/necropick.png"));
            ImageConstants.ares = ImageIO.read(new File("src/view/objectViews/images/ares.png"));
            ImageConstants.astrape = ImageIO.read(new File("src/view/objectViews/images/astrape.png"));
            ImageConstants.cerberus = ImageIO.read(new File("src/view/objectViews/images/cerberus.png"));
            ImageConstants.aceso = ImageIO.read(new File("src/view/objectViews/images/aceso.png"));
            ImageConstants.melampus = ImageIO.read(new File("src/view/objectViews/images/melampus.png"));
            ImageConstants.chiron = ImageIO.read(new File("src/view/objectViews/images/chiron.png"));
            ImageConstants.athena = ImageIO.read(new File("src/view/objectViews/images/athena.png"));
            ImageConstants.proteus = ImageIO.read(new File("src/view/objectViews/images/proteus.png"));
            ImageConstants.empusa = ImageIO.read(new File("src/view/objectViews/images/empusa.png"));
            ImageConstants.dolus = ImageIO.read(new File("src/view/objectViews/images/dolus.png"));
        }
        catch (Exception e){
            System.out.println("File Not Found!");
        }
    }

    private void getAudios() {
        SoundPathConstants.backGroundSound = "src/view/SoundEffects/Song.wav";
        SoundPathConstants.BulletFiredSound = "src/view/SoundEffects/Bullet Fired.wav";
        SoundPathConstants.waveSpawnSound = "src/view/SoundEffects/Wave Spawn.wav";
        SoundPathConstants.enemyOnDeathSound = "src/view/SoundEffects/EnemyOnDeath.wav";
        SoundPathConstants.impactSound = "src/view/SoundEffects/ImpactSound.wav";
        SoundPathConstants.endSound = "src/view/SoundEffects/endSound.wav";
        SoundPathConstants.winSound = "src/view/SoundEffects/winSound.wav";
        Sound sound = null;
        try {
            sound = new Sound(SoundPathConstants.backGroundSound);
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
