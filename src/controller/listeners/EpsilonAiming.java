package controller.listeners;


import controller.Controller;
import controller.enums.ModelType;
import controller.manager.Spawner;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;
import view.soundEffects.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class EpsilonAiming extends MouseAdapter {

    double timer;
    public static int AIM_CODE = 1;
    public EpsilonAiming(){
        timer = 0;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() != AIM_CODE) {
            return;
        }
        if (GameState.isPause())
            return;
        timer = GameState.getTime();
        EpsilonModel epsilon =(EpsilonModel) ModelData.getModels().getFirst();
        Vector clickedPoint = new Vector(
                e.getX() - Constants.SCREEN_SIZE.width ,
                e.getY() - Constants.SCREEN_SIZE.height
        );
        if (clickedPoint.Equals(epsilon.getPosition()))
            return;
        if (!Controller.shootRequest(clickedPoint))
            return;

        try {
            Sound sound = new Sound(Constants.BulletFiredSound);
            sound.play();
        } catch (UnsupportedAudioFileException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
    }
}
