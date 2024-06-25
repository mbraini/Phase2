package model.objectModel.frameModel;

import data.Constants;
import model.interfaces.MoveAble;
import utils.Math;
import utils.Vector;

import java.awt.*;

public class FrameModel implements MoveAble {

    private Vector position;
    private Dimension size;
    private String id;
    private Vector upDownA = new Vector();
    private Vector leftRightA = new Vector();
    private Vector upDownV = new Vector();
    private Vector leftRightV = new Vector();
    private Vector upDownP = new Vector();
    private Vector leftRightP = new Vector();
    private final Vector positionInit;
    private final Dimension dimensionInit;
    private boolean isResizing;
    private boolean isIsometric;
    private boolean isSolid;
    private boolean isShrinking;

    public FrameModel(Vector positionInit ,Dimension dimensionInit ,String id){
        this.position = positionInit;
        this.size = dimensionInit;
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

    @Override
    public void move() {
        setUpDownV(
                getUpDownV().x + upDownA.x * Constants.FRAME_ANIMATION_REFRESH_RATE ,
                getUpDownV().y + upDownA.y * Constants.FRAME_ANIMATION_REFRESH_RATE
        );
        setLeftRightV(
                getLeftRightV().x + leftRightA.x * Constants.FRAME_ANIMATION_REFRESH_RATE ,
                getLeftRightV().y + leftRightA.y * Constants.FRAME_ANIMATION_REFRESH_RATE
        );

        Vector upDownMoved = new Vector(
                (2 * getUpDownV().x - upDownA.x * Constants.FRAME_ANIMATION_REFRESH_RATE)
                        * Constants.FRAME_ANIMATION_REFRESH_RATE / 2 ,
                (2 * getUpDownV().y - upDownA.y * Constants.FRAME_ANIMATION_REFRESH_RATE)
                        * Constants.FRAME_ANIMATION_REFRESH_RATE / 2
        );
        Vector leftRightMoved = new Vector(
                (2 * getLeftRightV().x - leftRightA.x
                        * Constants.FRAME_ANIMATION_REFRESH_RATE) * Constants.FRAME_ANIMATION_REFRESH_RATE / 2
                ,(2 * getLeftRightV().y - leftRightA.y * Constants.FRAME_ANIMATION_REFRESH_RATE)
                * Constants.FRAME_ANIMATION_REFRESH_RATE / 2
        );
        setUpDownP(Math.VectorAdd(upDownMoved ,getUpDownP()));
        setLeftRightP(Math.VectorAdd(leftRightMoved ,getLeftRightP()));
        if (dimensionInit.height + upDownP.x + upDownP.y <= Constants.MINIMUM_FRAME_DIMENSION.height) {
            setUpDownP(Math.VectorAdd(Math.ScalarInVector(-1, upDownMoved), getUpDownP()));
        }
        if (dimensionInit.width + leftRightP.x + leftRightP.y <= Constants.MINIMUM_FRAME_DIMENSION.width) {
            setLeftRightP(Math.VectorAdd(Math.ScalarInVector(-1, leftRightMoved), getLeftRightP()));
        }

        revalidate();
    }

    private void revalidate() {
        setSize(new Dimension((int)(leftRightP.y + leftRightP.x + dimensionInit.width) ,(int)(upDownP.y + upDownP.x + dimensionInit.height)));
        setPosition(new Vector(-leftRightP.x + positionInit.x, -upDownP.x + positionInit.y));
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

}
