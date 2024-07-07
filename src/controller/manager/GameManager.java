package controller.manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {

    private Timer spawner;
    private GameManagerThread gameManager;

    public GameManager(){
        gameManager = new GameManagerThread();
        spawner = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


//                Spawner.addObject(
//                        Helper.createRandomPosition(ModelData.getLocalFrames().get(ModelData.getModels().getFirst())),
//                        Helper.RandomStringGenerator(Constants.ID_SIZE),
//                        ModelType.trigorath
//                );
//
//                Spawner.addObject(
//                        Helper.createRandomPosition(ModelData.getLocalFrames().get(ModelData.getModels().getFirst())),
//                        Helper.RandomStringGenerator(Constants.ID_SIZE),
//                        ModelType.squarantine
//                );

            }
        });
        spawner.start();
    }

    public GameManagerThread getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManagerThread gameManager) {
        this.gameManager = gameManager;
    }
}
