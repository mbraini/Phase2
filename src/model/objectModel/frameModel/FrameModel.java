package model.objectModel.frameModel;

import data.Constants;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class FrameModel {

    private Vector position;
    private Dimension size;
    private String id;
    private Vector upDownA = new Vector();
    private Vector leftRightA = new Vector();
    private Vector upDownV = new Vector();
    private Vector leftRightV = new Vector();
    private Vector upDownP = new Vector();
    private Vector leftRightP = new Vector();
    private Vector positionInit;
    private final Dimension dimensionInit;
    private boolean isResizing;
    private boolean isIsometric;
    private boolean isSolid;
    private boolean canTopResize = true;
    private boolean canBottomResize = true;
    private boolean canRightResize = true;
    private boolean canLeftResize = true;
    private boolean isShrinking;

    public FrameModel(Vector positionInit ,Dimension dimensionInit ,String id){
        this.position = positionInit;
        this.size = dimensionInit;
        this.id = id;
        this.positionInit = positionInit;
        this.dimensionInit = dimensionInit;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void resize() {
        setUpDownV(
                getUpDownV().x + upDownA.x * Constants.FRAME_ANIMATION_REFRESH_RATE ,
                getUpDownV().y + upDownA.y * Constants.FRAME_ANIMATION_REFRESH_RATE
        );
        setLeftRightV(
                getLeftRightV().x + leftRightA.x * Constants.FRAME_ANIMATION_REFRESH_RATE ,
                getLeftRightV().y + leftRightA.y * Constants.FRAME_ANIMATION_REFRESH_RATE
        );
        Vector upDownMoved = new Vector();
        Vector leftRightMoved = new Vector();
        if (canTopResize){
            upDownMoved.setX((2 * getUpDownV().x - upDownA.x * Constants.FRAME_ANIMATION_REFRESH_RATE)
                    * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);
        }
        if (canBottomResize){
            upDownMoved.setY((2 * getUpDownV().y - upDownA.y * Constants.FRAME_ANIMATION_REFRESH_RATE)
                    * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);
        }
        if (canLeftResize){
            leftRightMoved.setX((2 * getLeftRightV().x - leftRightA.x
                    * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);
        }
        if (canRightResize){
            leftRightMoved.setY((2 * getLeftRightV().y - leftRightA.y * Constants.FRAME_ANIMATION_REFRESH_RATE)
                    * Constants.FRAME_ANIMATION_REFRESH_RATE / 2);
        }
        setUpDownP(Math.VectorAdd(upDownMoved ,getUpDownP()));
        setLeftRightP(Math.VectorAdd(leftRightMoved ,getLeftRightP()));

        revalidate();
    }

    private void revalidate() {
        setSize(new Dimension((int)(leftRightP.y + leftRightP.x + dimensionInit.width) ,(int)(upDownP.y + upDownP.x + dimensionInit.height)));
        setPosition(new Vector(-leftRightP.x + positionInit.x, -upDownP.x + positionInit.y));
    }

    public void transfer(Vector vector){
        positionInit = vector.clone();
        position = vector.clone();
        setLeftRightP(0 ,0);
        setUpDownP(0 ,0);
    }

    public Vector getUpDownV() {
        return upDownV;
    }

    public void setUpDownV(Vector upDownV) {
        this.upDownV = upDownV;
    }

    public Vector getLeftRightV() {
        return leftRightV;
    }

    public void setLeftRightV(Vector leftRightV) {
        this.leftRightV = leftRightV;
    }

    public void setUpDownV(double x , double y) {
        this.upDownV = new Vector(x ,y);
    }

    public void setLeftRightV(double x , double y) {
        this.leftRightV = new Vector(x ,y);
    }

    public Vector getUpDownP() {
        return upDownP;
    }

    public void setUpDownP(Vector upDownP) {
        this.upDownP = upDownP;
    }

    public Vector getLeftRightP() {
        return leftRightP;
    }

    public void setLeftRightP(Vector leftRightP) {
        this.leftRightP = leftRightP;
    }

    public void setUpDownP(double x ,double y) {
        this.upDownP = new Vector(x ,y);
    }

    public void setLeftRightP(double x ,double y) {
        this.leftRightP = new Vector(x ,y);
    }

    public Vector getUpDownA() {
        return upDownA;
    }

    public void setUpDownA(Vector upDownA) {
        this.upDownA = upDownA;
    }

    public Vector getLeftRightA() {
        return leftRightA;
    }

    public void setLeftRightA(Vector leftRightA) {
        this.leftRightA = leftRightA;
    }
    public void setLeftRightA(double x ,double y){
        leftRightA = new Vector(x ,y);
    }
    public void setUpDownA(double x ,double y){
        upDownA = new Vector(x ,y);
    }


    public boolean isResizing() {
        return isResizing;
    }

    public void setResizing(boolean resizing) {
        isResizing = resizing;
    }


    public boolean isIsometric() {
        return isIsometric;
    }

    public void setIsometric(boolean isometric) {
        isIsometric = isometric;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public boolean isShrinking() {
        return isShrinking;
    }

    public void setShrinking(boolean shrinking) {
        isShrinking = shrinking;
    }

    public boolean canTopResize() {
        return canTopResize;
    }

    public void setCanTopResize(boolean canTopResize) {
        this.canTopResize = canTopResize;
    }

    public boolean canBottomResize() {
        return canBottomResize;
    }

    public void setCanBottomResize(boolean canBottomResize) {
        this.canBottomResize = canBottomResize;
    }

    public boolean canRightResize() {
        return canRightResize;
    }

    public void setCanRightResize(boolean canRightResize) {
        this.canRightResize = canRightResize;
    }

    public boolean canLeftResize() {
        return canLeftResize;
    }

    public void setCanLeftResize(boolean canLeftResize) {
        this.canLeftResize = canLeftResize;
    }
}
