package model.objectModel.fighters.miniBossEnemies.blackOrbModel;

import data.Constants;
import model.objectModel.fighters.AbstractEnemy;
import model.objectModel.frameModel.FrameModel;
import utils.Vector;

import javax.swing.*;
import java.util.ArrayList;

public class BlackOrbModel extends AbstractEnemy {

    private Timer orbSpawner;
    private Timer frameSpawner;
    private BlackOrbThread blackOrbThread;
    private ArrayList<OrbModel> orbModels;
    private ArrayList<FrameModel> frameModels;
    private Vector center;
    private boolean allFramesSpawned;
    private boolean allOrbsSpawned;
    private int frameCount;
    private int orbCount;

    public BlackOrbModel(Vector center ,String id){
        this.id = id;
        frameModels = new ArrayList<>();
        orbModels = new ArrayList<>();
        blackOrbThread = new BlackOrbThread(this);
        this.center = center;
    }

    public ArrayList<OrbModel> getOrbModels() {
        return orbModels;
    }

    public void setOrbModels(ArrayList<OrbModel> orbModels) {
        this.orbModels = orbModels;
    }

    public ArrayList<FrameModel> getFrameModels() {
        return frameModels;
    }

    public void setFrameModels(ArrayList<FrameModel> frameModels) {
        this.frameModels = frameModels;
    }

    public boolean isAllFramesSpawned() {
        return allFramesSpawned;
    }

    public void setAllFramesSpawned(boolean allFramesSpawned) {
        this.allFramesSpawned = allFramesSpawned;
    }

    public boolean isAllOrbsSpawned() {
        return allOrbsSpawned;
    }

    public void setAllOrbsSpawned(boolean allOrbsSpawned) {
        this.allOrbsSpawned = allOrbsSpawned;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getOrbCount() {
        return orbCount;
    }

    public void setOrbCount(int orbCount) {
        this.orbCount = orbCount;
    }

    public void addFrame(FrameModel frameModel){
        frameModels.add(frameModel);
    }

    public void addOrb(OrbModel orbModel){
        orbModels.add(orbModel);
    }

    public Vector getCenter() {
        return center;
    }

    public void setCenter(Vector center) {
        this.center = center;
    }

    public Timer getFrameSpawner() {
        return frameSpawner;
    }

    public void setFrameSpawner(Timer frameSpawner) {
        this.frameSpawner = frameSpawner;
    }

    public void spawn() {
        frameSpawner = new Timer(Constants.BLACK_ORB_SPAWN_DELAY ,new FrameSpawner(this));
        orbSpawner = new Timer(Constants.BLACK_ORB_SPAWN_DELAY ,new OrbSpawner(this));
        frameSpawner.start();
    }

    public Timer getOrbSpawner() {
        return orbSpawner;
    }

    public void setOrbSpawner(Timer orbSpawner) {
        this.orbSpawner = orbSpawner;
    }

    public BlackOrbThread getBlackOrbThread() {
        return blackOrbThread;
    }

    public void setBlackOrbThread(BlackOrbThread blackOrbThread) {
        this.blackOrbThread = blackOrbThread;
    }

}
