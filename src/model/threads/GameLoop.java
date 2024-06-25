package model.threads;


import data.Constants;
import model.GameState;
import model.ModelData;
import model.collision.Collision;
import model.interfaces.Ability;
import model.interfaces.ImpactAble;
import model.interfaces.MoveAble;
import model.objectModel.ObjectModel;
import model.objectModel.basicEnemies.BasicEnemyModel;
import utils.Pair;

import java.util.ArrayList;

public class GameLoop extends Thread {

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 1000;
        double ns = 1000000000 / amountOfTicks;
        double deltaModel = 0;
        while (!GameState.isPause() && !GameState.isOver()) {
            long now = System.nanoTime();
            deltaModel += (now - lastTime) / ns;
            lastTime = now;
            if (deltaModel >= Constants.UPS) {
                UpdateModel();
                deltaModel = 0;
            }
        }
    }

    private void UpdateModel() {

        ///////concurrent

        ArrayList<ObjectModel> models = ModelData.getModels();
        for (ObjectModel model : models){
            if (model instanceof Ability){
                if (((Ability) model).hasAbility())
                    ((Ability) model).ability();
            }
            if (model instanceof MoveAble)
                ((MoveAble) model).move();
        }

        ArrayList<Pair> collisions = new ArrayList<>();
        for (int i = 0 ;i < ModelData.getModels().size() ;i++){
            for (int j = 0 ;j < ModelData.getModels().size() ;j++){
                if (i == j)
                    continue;
                Pair pair = new Pair(i ,j);
                if (Pair.Contains(collisions ,pair))
                    continue;
                if (Collision.IsColliding(ModelData.getModels().get(i) ,ModelData.getModels().get(j))){
                    new Collision().CollisionResponse(ModelData.getModels().get(i) ,ModelData.getModels().get(j));
                    collisions.add(new Pair(i ,j));
                }
            }
        }


    }


}