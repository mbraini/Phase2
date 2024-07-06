package model.objectModel.fighters.miniBossEnemies.barricadosModel;

import model.interfaces.Fader;
import utils.Vector;

import java.util.ArrayList;

public class BarricadosFirstModel extends BarricadosModel {

    public BarricadosFirstModel(Vector position ,String id){
        this.position = position;
        this.HP = 10000000;
        this.id = id;
        this.vulnerableToEpsilonBullet = false;
        this.vulnerableToEpsilonMelee = false;
        initVertices();
    }

    @Override
    public void die() {

    }
}
