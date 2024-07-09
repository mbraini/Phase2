package controller.listeners;


import controller.Controller;
import model.ModelData;
import model.objectModel.fighters.EpsilonModel;
import utils.Math;
import utils.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EpsilonCirculation implements MouseMotionListener {
    private EpsilonModel epsilon;
    public EpsilonCirculation(){
        this.epsilon = (EpsilonModel) ModelData.getModels().getFirst();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        Vector mousePosition = new Vector(e.getX() ,e.getY());
        Vector epsilonPosition = epsilon.getPosition();
        if (mousePosition.Equals(epsilonPosition))
            return;
        Vector direction = Math.VectorAdd(Math.ScalarInVector(-1 ,epsilonPosition) ,mousePosition);
        double cosTheta = Math.DotProduct(direction ,new Vector(0 ,-1)) / (Math.VectorSize(direction));
        double theta = java.lang.Math.acos(cosTheta);
        if ((direction.getX() >= 0 && direction.getY() >= 0) || (direction.getX() >= 0 && direction.getY() <= 0))
            theta *= -1;
        epsilon.Rotate(theta);
    }
}
