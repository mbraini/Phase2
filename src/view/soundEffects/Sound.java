package view.soundEffects;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Sound {

    Clip clip;
    private static float previousVolume = -11.2f;
    private static float currentVolume = -11.2f;
    private FloatControl fc;
    private static boolean mute = false;
    private static ArrayList<FloatControl> fcs = new ArrayList<>();

    public Sound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream sound = AudioSystem.getAudioInputStream(new File(path));
        clip = AudioSystem.getClip();
        clip.open(sound);
        fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(currentVolume);
        fcs.add(fc);
    }
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public static void volumeUp(){
        currentVolume += 3.44f;
        if (currentVolume > 6.0f) {
            currentVolume = 6.0f;
        }
        for (int i= 0; i <fcs.size() ;i++) {
            fcs.get(i).setValue(currentVolume);
        }
    }
    public static void volumeDown(){
        currentVolume -= 3.44f;
        if (currentVolume < -80.0f){
            currentVolume = -80.0f;
        }
        for (int i= 0; i <fcs.size() ;i++) {
            fcs.get(i).setValue(currentVolume);
        }
    }
    public static void volumeMute(){
        if (!mute){
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            mute = true;
        }
        else {
            currentVolume = previousVolume;
            mute = false;
        }
        for (int i= 0; i <fcs.size() ;i++) {
            fcs.get(i).setValue(currentVolume);
        }
    }
}
