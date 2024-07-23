package view.objectViews;

import utils.Vector;

import javax.swing.*;
import java.awt.*;

public class FrameView extends JFrame {
    private Vector position;
    private Dimension dimension;
    private String id;
    public static JPanel container = new JPanel();
    public FrameView(Vector position ,Dimension dimension ,String id){
        this.position = position;
        this.dimension = dimension;
        this.id = id;

        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setType(Type.UTILITY);
        this.setLocation((int) position.getX() ,(int) position.getY());

        this.setVisible(true);
    }

    public void update(){
        this.setBounds((int) position.getX() ,(int) position.getY() ,dimension.width ,dimension.height);
        revalidate();
        repaint();
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
