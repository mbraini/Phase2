package model.objectModel.normalEnemies;

import data.Constants;
import model.GameState;
import model.ModelData;
import model.collision.Collision;
import model.interfaces.Ability;
import model.interfaces.FrameAttacher;
import model.interfaces.IsPolygon;
import model.objectModel.EpsilonModel;
import model.objectModel.frameModel.FrameLocations;
import model.objectModel.frameModel.FrameModel;
import utils.Vector;

import java.util.ArrayList;

public class OmenoctModel extends NormalEnemyModel implements Ability , IsPolygon , FrameAttacher {
    private ArrayList<Vector> vertices;
    private FrameLocations frameLocation;
    private double timer;

    @Override
    protected void meleeAttack(EpsilonModel epsilon) {

    }

    @Override
    public void ability() {
        if (frameLocation == null){
            navigateFrame();
            return;
        }
        else {
            if (GameState.getTime() - timer >= Constants.OMENOCT_FIRING_TIME){
                ////////////
            }
        }
    }

    private void navigateFrame() {
        FrameModel epsilonFrame = ModelData.getLocalFrames().get(ModelData.getModels().getFirst());
        if (Collision.isInFrame(epsilonFrame, this.getPosition())){
            double top = epsilonFrame.getPosition().getY();
            double bottom = epsilonFrame.getPosition().getY() + epsilonFrame.getSize().height;
            double left = epsilonFrame.getPosition().getX();
            double right = epsilonFrame.getPosition().getX() + epsilonFrame.getSize().width;

            double topDistance = Math.abs(position.y - top);
            double bottomDistance = Math.abs(position.y - bottom);
            double leftDistance = Math.abs(position.x - left);
            double rightDistance = Math.abs(position.x - right);

            double min = Math.min(
                    Math.min(leftDistance ,rightDistance),
                    Math.min(topDistance ,bottomDistance)
            );

            if (min == topDistance){
//                velocity =
            }
        }
    }

    @Override
    public boolean hasAbility() {
        return false;
    }

    @Override
    public ArrayList<Vector> getVertices() {
        return null;
    }

    @Override
    public FrameLocations getAttachedLocation() {
        return frameLocation;
    }

}
