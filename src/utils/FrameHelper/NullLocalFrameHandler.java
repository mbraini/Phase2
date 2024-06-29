package utils.FrameHelper;

import model.collision.Collision;
import model.logics.FrameHit;
import model.logics.Impact;
import model.objectModel.EpsilonModel;
import model.objectModel.ObjectModel;
import model.objectModel.frameModel.FrameModel;
import model.objectModel.projectiles.BulletModel;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class NullLocalFrameHandler {

    ObjectModel model;
    ArrayList<FrameModel> frames;
    HashMap<ObjectModel ,FrameModel> previousLocals;
    public NullLocalFrameHandler(
            ObjectModel model,
            ArrayList<FrameModel> frames ,
            HashMap<ObjectModel , FrameModel> previousLocals
    )
    {
        this.model = model;
        this.frames = frames;
        this.previousLocals = previousLocals;
    }

    public void handle() {
        if (model instanceof BulletModel)
            bulletHandler();
        else if (model instanceof EpsilonModel)
            epsilonHandler();
    }

    private void epsilonHandler() {
        Vector impactPoint = model.getPosition().clone();
        HashMap<Double ,FrameModel> map = new HashMap<>();
        double min = FrameCalculationHelper.findClosestDistanceToFrameEdges(
                model.getPosition(),
                frames.getFirst()
        );
        for (FrameModel frameModel : frames){
            double distance = FrameCalculationHelper.findClosestDistanceToFrameEdges(
                    model.getPosition(),
                    frameModel
            );
            map.put(
                    distance,
                    frameModel
            );
            if (distance < min)
                min = distance;
        }
        FrameModel closestFrame = map.get(min);
        Vector newPosition = getEpsilonNewPosition(closestFrame ,min);
        model.setPosition(newPosition);
        new Impact(impactPoint).MakeImpact();
    }

    private void bulletHandler() {
        if (previousLocals.get(model) == null){
            HashMap<Double ,FrameModel> map = new HashMap<>();
            double min = FrameCalculationHelper.findClosestDistanceToFrameEdges(
                    model.getPosition(),
                    frames.getFirst()
            );
            for (FrameModel frameModel : frames){
                double distance = FrameCalculationHelper.findClosestDistanceToFrameEdges(
                        model.getPosition(),
                        frameModel
                );
                map.put(
                        distance,
                        frameModel
                );
                if (distance < min)
                    min = distance;
            }
            new FrameHit(map.get(min) ,model).handle();
        }
        else {
            new FrameHit(previousLocals.get(model), model).handle();
        }
        model.die();
    }

    private Vector getEpsilonNewPosition(FrameModel closestFrame , double distance){
        distance += 1;
        Vector position = model.getPosition().clone();
        Vector up = Math.VectorWithSize(
                new Vector(0 ,-1),
                distance
        );
        Vector down = Math.VectorWithSize(
                new Vector(0 ,1),
                distance
        );
        Vector right = Math.VectorWithSize(
                new Vector(1 ,0),
                distance
        );
        Vector left = Math.VectorWithSize(
                new Vector(-1 ,0),
                distance
        );
        Vector upRight = Math.VectorWithSize(
                new Vector(1 ,-1),
                distance
        );
        Vector upLeft = Math.VectorWithSize(
                new Vector(-1 ,-1),
                distance
        );
        Vector downRight = Math.VectorWithSize(
                new Vector(1 ,1),
                distance
        );
        Vector downLeft = Math.VectorWithSize(
                new Vector(-1 ,1),
                distance
        );
        if (Collision.isInFrame(closestFrame ,Math.VectorAdd(up ,position))) {
            return Math.VectorAdd(up, position);
        }
        else if (Collision.isInFrame(closestFrame ,Math.VectorAdd(down ,position))) {
            return Math.VectorAdd(down, position);
        }
        else if (Collision.isInFrame(closestFrame ,Math.VectorAdd(right ,position))) {
            return Math.VectorAdd(right, position);
        }
        else if (Collision.isInFrame(closestFrame ,Math.VectorAdd(left ,position))) {
            return Math.VectorAdd(left, position);
        }
        else if (Collision.isInFrame(closestFrame ,Math.VectorAdd(upRight ,position))) {
            return Math.VectorAdd(upRight, position);
        }
        else if (Collision.isInFrame(closestFrame ,Math.VectorAdd(upLeft ,position))) {
            return Math.VectorAdd(upLeft, position);
        }
        else if (Collision.isInFrame(closestFrame ,Math.VectorAdd(downRight ,position))) {
            return Math.VectorAdd(downRight, position);
        }
        else {
            return Math.VectorAdd(downLeft, position);
        }
    }


}
