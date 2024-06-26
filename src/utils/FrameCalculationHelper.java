package utils;

import model.objectModel.frameModel.FrameLocations;
import model.objectModel.frameModel.FrameModel;
import utils.Vector;

public class FrameCalculationHelper {
    
    public static FrameLocations findClosestLocalFrameLocation(FrameModel frameModel , Vector position){
        double top = frameModel.getPosition().getY();
        double bottom = frameModel.getPosition().getY() + frameModel.getSize().height;
        double left = frameModel.getPosition().getX();
        double right = frameModel.getPosition().getX() + frameModel.getSize().width;

        double topDistance = java.lang.Math.abs(position.y - top);
        double bottomDistance = java.lang.Math.abs(position.y - bottom);
        double leftDistance = java.lang.Math.abs(position.x - left);
        double rightDistance = java.lang.Math.abs(position.x - right);
        double min = java.lang.Math.min(
                    java.lang.Math.min(leftDistance ,rightDistance),
                    java.lang.Math.min(topDistance ,bottomDistance)
            );

        if (min == topDistance){
            return FrameLocations.top;
        }
        else if (min == bottomDistance){
            return FrameLocations.bottom;
        }
        else if (min == leftDistance){
            return FrameLocations.left;
        }
        else {
            return FrameLocations.right;
        }

    }

    public static double findClosestDistanceToFrameEdges(Vector position ,FrameModel frameModel) {

        double top = frameModel.getPosition().getY();
        double bottom = frameModel.getPosition().getY() + frameModel.getSize().height;
        double left = frameModel.getPosition().getX();
        double right = frameModel.getPosition().getX() + frameModel.getSize().width;

        double topDistance = java.lang.Math.abs(position.y - top);
        double bottomDistance = java.lang.Math.abs(position.y - bottom);
        double leftDistance = java.lang.Math.abs(position.x - left);
        double rightDistance = java.lang.Math.abs(position.x - right);
        return java.lang.Math.min(
                java.lang.Math.min(leftDistance ,rightDistance),
                java.lang.Math.min(topDistance ,bottomDistance)
        );
    }
}
