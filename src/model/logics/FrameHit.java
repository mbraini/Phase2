package model.logics;

import constants.Constants;
import model.animations.FrameAnimation;
import model.objectModel.ObjectModel;
import model.objectModel.frameModel.FrameLocations;
import model.objectModel.frameModel.FrameModel;
import utils.FrameHelper.FrameCalculationHelper;

public class FrameHit {
    private FrameModel frame;
    private ObjectModel model;

    public FrameHit(FrameModel frame , ObjectModel model){
        this.frame = frame;
        this.model = model;
    }

    public void handle() {
        FrameLocations frameLocation = FrameCalculationHelper.findClosestLocalFrameLocation(
                frame,
                model.getPosition()
        );
        /////////////////conditions.....
        resize(frame ,frameLocation);

    }

    private void resize(FrameModel frame, FrameLocations frameLocation) {
        switch (frameLocation){
            case top :
                new FrameAnimation(
                        frame,
                        Constants.FRAME_BULLET_RESIZE,
                        0,
                        0,
                        0,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
            case bottom:
                new FrameAnimation(
                        frame,
                        0,
                        Constants.FRAME_BULLET_RESIZE,
                        0,
                        0,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
            case right:
                new FrameAnimation(
                        frame,
                        0,
                        0,
                        Constants.FRAME_BULLET_RESIZE,
                        0,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
            case left:
                new FrameAnimation(
                        frame,
                        0,
                        0,
                        0,
                        Constants.FRAME_BULLET_RESIZE,
                        Constants.FRAME_SHRINKAGE_TIME
                ).StartAnimation();
                break;
        }
    }
}
