package controller.manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {

    private WaveSpawner waveSpawner;
    private GameManagerThread gameManager;

    public GameManager(){
        gameManager = new GameManagerThread();
        this.waveSpawner = new WaveSpawner();
    }

    public GameManagerThread getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManagerThread gameManager) {
        this.gameManager = gameManager;
    }
}
