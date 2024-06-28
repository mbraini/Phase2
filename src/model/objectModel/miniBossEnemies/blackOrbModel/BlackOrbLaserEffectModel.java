package model.objectModel.miniBossEnemies.blackOrbModel;

import data.Constants;
import model.objectModel.EffectModel;
import utils.Math;
import utils.Vector;
import utils.area.Polygon;

import java.awt.*;
import java.util.ArrayList;

public class BlackOrbLaserEffectModel extends EffectModel {
    private OrbModel orb1;
    private OrbModel orb2;
    public BlackOrbLaserEffectModel(OrbModel orb1 ,OrbModel orb2 ,String id){
        this.orb1 = orb1;
        this.orb2 = orb2;
        this.color = Color.MAGENTA;
        this.id = id;
        initArea();
    }

    private void initArea() {
        Vector direction = Math.VectorAdd(
                orb2.getPosition(),
                Math.ScalarInVector(-1 ,orb1.getPosition())
        );
        Vector normal1 = Math.NormalWithSize(direction , Constants.ORB_DIMENSION.width /2d);
        Vector normal2 = Math.ScalarInVector(-1 ,normal1);

        Vector vertex1 = Math.VectorAdd(
                orb1.getPosition(),
                normal1
        );
        Vector vertex2 = Math.VectorAdd(
                orb1.getPosition(),
                normal2
        );
        Vector vertex3 = Math.VectorAdd(
                orb2.getPosition(),
                normal2
        );
        Vector vertex4 = Math.VectorAdd(
                orb2.getPosition(),
                normal1
        );

        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();

        xs.add((int) vertex1.x);
        xs.add((int) vertex2.x);
        xs.add((int) vertex3.x);
        xs.add((int) vertex4.x);

        ys.add((int) vertex1.y);
        ys.add((int) vertex2.y);
        ys.add((int) vertex3.y);
        ys.add((int) vertex4.y);

        area = new Polygon(xs ,ys ,xs.size());
    }

    @Override
    public void die() {

    }

}
