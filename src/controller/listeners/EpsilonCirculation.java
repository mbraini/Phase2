package controller.listeners;


import controller.Controller;
import data.Constants;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class EpsilonCirculation extends MouseMotionAdapter {
    private EpsilonModel epsilon;
    public EpsilonCirculation(){
        this.epsilon = (EpsilonModel) ModelData.getModels().getFirst();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

        Vector mousePosition = new Vector(e.getX() ,e.getY());
        mousePosition = Math.VectorAdd(
                mousePosition,
                new Vector(
                        -Constants.SCREEN_SIZE.width,
                        -Constants.SCREEN_SIZE.height
                )
        );
        Vector epsilonPosition = epsilon.getPosition();
        if (mousePosition.Equals(epsilonPosition))
            return;
        Vector direction = Math.VectorAdd(Math.ScalarInVector(-1 ,epsilonPosition) ,mousePosition);
        double cosTheta = Math.DotProduct(direction ,new Vector(1 ,0)) / (Math.VectorSize(direction));
        double theta = java.lang.Math.acos(cosTheta);
        if (direction.getY() <= 0){
            theta = -theta;
        }
        epsilon.Rotate(-theta);
    }
}
