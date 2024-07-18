package model.objectModel.fighters.finalBoss;

import controller.manager.Spawner;
import controller.manager.loading.SkippedByJson;
import constants.Constants;
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
    @SkippedByJson
    private BossThread bossThread;

    public Boss(){
        head = new HeadModel(
                new Vector(
                        Constants.SCREEN_SIZE.width / 2d,
                        Constants.HEAD_DIMENSION.height / 2d
                ),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        leftHand = new HandModel(
                new Vector(
                        Constants.HAND_DIMENSION.width / 2d,
                        Constants.SCREEN_SIZE.height / 2d
                ),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        rightHand = new HandModel(
                new Vector(
                        Constants.SCREEN_SIZE.width - Constants.HAND_DIMENSION.width / 2d,
                        Constants.SCREEN_SIZE.height / 2d
                ),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        punch = new PunchModel(
                new Vector(
                        Constants.PUNCH_DIMENSION.width / 2d,
                        Constants.SCREEN_SIZE.height - Constants.PUNCH_DIMENSION.height /2d
                ),
                Helper.RandomStringGenerator(Constants.ID_SIZE)
        );
        bossThread = new BossThread(this);
        bossThread.start();
    }

    public void spawnHelpers(){
        Spawner.spawnHead(head);
        Spawner.spawnHand(leftHand);
        Spawner.spawnHand(rightHand);
    }

    public void spawnPunch(){
        Spawner.addPunch(punch);
    }


    public HandModel getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(HandModel leftHand) {
        this.leftHand = leftHand;
    }

    public HandModel getRightHand() {
        return rightHand;
    }

    public void setRightHand(HandModel rightHand) {
        this.rightHand = rightHand;
    }

    public HeadModel getHead() {
        return head;
    }

    public void setHead(HeadModel head) {
        this.head = head;
    }

    public PunchModel getPunch() {
        return punch;
    }

    public void setPunch(PunchModel punch) {
        this.punch = punch;
    }
}
