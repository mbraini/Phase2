package model.objectModel.normalEnemies.helper;

import data.Constants;
import model.ModelData;
import model.collision.Collision;
import model.objectModel.frameModel.FrameLocations;
import model.objectModel.frameModel.FrameModel;
import utils.Math;
import utils.Vector;

public class OmenoctNavigater {

    private Vector position;
    private Vector velocity;
    private Vector destination;
    private FrameLocations willAttachTo;

    public OmenoctNavigater(Vector position ,Vector velocity){
        this.position = position;
        this.velocity = velocity;
    }



    public void navigateFrame() {
        FrameModel epsilonFrame = ModelData.getLocalFrames().get(ModelData.getModels().getFirst());
        assert epsilonFrame!= null;
        double top = epsilonFrame.getPosition().getY();
        double bottom = epsilonFrame.getPosition().getY() + epsilonFrame.getSize().height;
        double left = epsilonFrame.getPosition().getX();
        double right = epsilonFrame.getPosition().getX() + epsilonFrame.getSize().width;

        double topDistance = java.lang.Math.abs(position.y - top);
        double bottomDistance = java.lang.Math.abs(position.y - bottom);
        double leftDistance = java.lang.Math.abs(position.x - left);
        double rightDistance = java.lang.Math.abs(position.x - right);

        if (Collision.isInFrame(epsilonFrame, position)){
            double min = java.lang.Math.min(
                    java.lang.Math.min(leftDistance ,rightDistance),
                    java.lang.Math.min(topDistance ,bottomDistance)
            );

            if (min == topDistance){
                velocity = Math.VectorWithSize(new Vector(0 ,-1) , Constants.OMENOCT_NAVIGATE_VELOCITY);
                destination = new Vector(position.x ,top);
                willAttachTo = FrameLocations.top;
            }
            if (min == bottomDistance){
                velocity = Math.VectorWithSize(new Vector(0 ,1) ,Constants.OMENOCT_NAVIGATE_VELOCITY);
                destination = new Vector(position.x ,bottom);
                willAttachTo = FrameLocations.bottom;
            }
            if (min == leftDistance){
                velocity = Math.VectorWithSize(new Vector(-1 ,0) ,Constants.OMENOCT_NAVIGATE_VELOCITY);
                destination = new Vector(left ,position.y);
                willAttachTo = FrameLocations.left;
            }
            if (min == rightDistance){
                velocity = Math.VectorWithSize(new Vector(1 ,0) ,Constants.OMENOCT_NAVIGATE_VELOCITY);
                destination = new Vector(right ,position.y);
                willAttachTo = FrameLocations.right;
            }
        }
        else {
            if (position.x > left && position.x < right){
                if (topDistance <= bottomDistance){
                    velocity = Math.VectorWithSize(new Vector(0 ,1) ,Constants.OMENOCT_NAVIGATE_VELOCITY);
                    destination = new Vector(position.x ,top);
                    willAttachTo = FrameLocations.top;
                }
                else {
                    velocity = Math.VectorWithSize(new Vector(0 ,-1) ,Constants.OMENOCT_NAVIGATE_VELOCITY);
                    destination = new Vector(position.x ,bottom);
                    willAttachTo = FrameLocations.bottom;
                }
                return;
            }
            if (position.y > top && position.y < bottom){
                if (topDistance <= bottomDistance){
                    velocity = Math.VectorWithSize(new Vector(1 ,0) ,Constants.OMENOCT_NAVIGATE_VELOCITY);
                    destination = new Vector(left ,position.y);
                    willAttachTo = FrameLocations.left;
                }
                else {
                    velocity = Math.VectorWithSize(new Vector(-1 ,0) ,Constants.OMENOCT_NAVIGATE_VELOCITY);
                    destination = new Vector(right ,position.y);
                    willAttachTo = FrameLocations.right;
                }
                return;
            }
            Vector topLeft = epsilonFrame.getPosition().clone();
            Vector topRight = Math.VectorAdd(
                    epsilonFrame.getPosition() ,
                    new Vector(epsilonFrame.getSize().width, 0)
            );
            Vector bottomRight = Math.VectorAdd(
                    epsilonFrame.getPosition() ,
                    new Vector(epsilonFrame.getSize().width, epsilonFrame.getSize().height)
            );

            Vector bottomLeft = Math.VectorAdd(
                    epsilonFrame.getPosition() ,
                    new Vector(0, epsilonFrame.getSize().height)
            );

            double topLeftD = Math.VectorSize(Math.VectorAdd(
                    Math.ScalarInVector(-1 ,topLeft),
                    position
            ));

            double topRightD = Math.VectorSize(Math.VectorAdd(
                    Math.ScalarInVector(-1 ,topRight),
                    position
            ));

            double bottomRightD = Math.VectorSize(Math.VectorAdd(
                    Math.ScalarInVector(-1 ,bottomRight),
                    position
            ));

            double bottomLeftD = Math.VectorSize(Math.VectorAdd(
                    Math.ScalarInVector(-1 ,bottomLeft),
                    position
            ));

            double min = java.lang.Math.min(
                    java.lang.Math.min(topLeftD ,topRightD),
                    java.lang.Math.min(bottomLeftD ,bottomRightD)
            );

            if (min == topLeftD){
                velocity = Math.VectorWithSize(
                        Math.VectorAdd(Math.ScalarInVector(-1 ,position), topLeft) ,
                        Constants.OMENOCT_NAVIGATE_VELOCITY
                );
                destination = topLeft;
                willAttachTo = FrameLocations.topLeft;
            }

            if (min == topRightD){
                velocity = Math.VectorWithSize(
                        Math.VectorAdd(Math.ScalarInVector(-1 ,position), topRight) ,
                        Constants.OMENOCT_NAVIGATE_VELOCITY
                );
                destination = topRight;
                willAttachTo = FrameLocations.topRight;
            }

            if (min == bottomLeftD){
                velocity = Math.VectorWithSize(
                        Math.VectorAdd(Math.ScalarInVector(-1 ,position), bottomLeft) ,
                        Constants.OMENOCT_NAVIGATE_VELOCITY
                );
                destination = bottomLeft;
                willAttachTo = FrameLocations.bottomLeft;
            }

            if (min == bottomRightD){
                velocity = Math.VectorWithSize(
                        Math.VectorAdd(Math.ScalarInVector(-1 ,position), bottomRight) ,
                        Constants.OMENOCT_NAVIGATE_VELOCITY
                );
                destination = bottomRight;
                willAttachTo = FrameLocations.bottomRight;
            }
        }
    }

    public Vector getVelocity() {
        return velocity;
    }


    public Vector getDestination() {
        return destination;
    }


    public FrameLocations getWillAttachTo() {
        return willAttachTo;
    }

}
