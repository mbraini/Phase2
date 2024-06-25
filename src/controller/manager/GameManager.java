package controller.manager;

import controller.enums.ObjectType;
import data.Constants;
import model.ModelData;
import utils.Helper;
import utils.Vector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {

    private Timer spawner;

    public GameManager(){
        spawner = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


//                Spawner.addObject(
//                        Helper.createRandomPosition(ModelData.getLocalFrames().get(ModelData.getModels().getFirst())),
//                        Helper.RandomStringGenerator(Constants.ID_SIZE),
//                        ObjectType.trigorath
//                );
//
//                Spawner.addObject(
//                        Helper.createRandomPosition(ModelData.getLocalFrames().get(ModelData.getModels().getFirst())),
//                        Helper.RandomStringGenerator(Constants.ID_SIZE),
//                        ObjectType.squarantine
//                );

            }
        });
        spawner.start();
    }

}
