package model.objectModel.fighters.finalBoss;

import data.Constants;
import model.objectModel.fighters.AbstractEnemy;
import model.objectModel.fighters.finalBoss.bossHelper.HandModel;
import model.objectModel.fighters.finalBoss.bossHelper.HeadModel;
import model.objectModel.fighters.finalBoss.bossHelper.PunchModel;
import utils.Helper;
import utils.Vector;

public class Boss extends AbstractEnemy {

    private HandModel leftHand;
    private HandModel rightHand;
    private HeadModel head;
    private PunchModel punch;

    public Boss(){
        head = new HeadModel(
                new Vector(
                        Constants.SCREEN_SIZE.width,
                        Constants.HEAD_DIMENSION.height / 2d
                ),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        leftHand = new HandModel(
                new Vector(
                        Constants.HAND_DIMENSION.width / 2d,
                        Constants.HAND_DIMENSION.height / 2d
                ),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        rightHand = new HandModel(
                new Vector(
                        Constants.SCREEN_SIZE.width - Constants.HAND_DIMENSION.width / 2d,
                        Constants.HAND_DIMENSION.height / 2d
                ),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
    }

}
