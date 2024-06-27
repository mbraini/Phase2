package view.gamePanels;

import data.Constants;
import model.GameState;
import view.ViewData;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImaginaryPanel extends JPanel {
    private ArrayList<ObjectView> views;
    private FrameView epsilonFrame;
    private String id;
    public ImaginaryPanel(String id){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBounds(-Constants.SCREEN_SIZE.width ,-Constants.SCREEN_SIZE.height , Constants.SCREEN_SIZE.width * 3 ,Constants.SCREEN_SIZE.height * 3);

        setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (epsilonFrame != null) {
            g.setColor(Color.MAGENTA);
            g.setFont(new Font(null, Font.BOLD, 10));
            g.drawString("XP: " + (int) GameState.getXp(),
                    3 + epsilonFrame.getX() + Constants.SCREEN_SIZE.width,
                    20 + epsilonFrame.getY() + Constants.SCREEN_SIZE.height
            );
            g.setColor(Color.GREEN);
            g.drawString("HP: " + (int) GameState.getHp(),
                    58 + epsilonFrame.getX() + Constants.SCREEN_SIZE.width,
                    20 + epsilonFrame.getY() + Constants.SCREEN_SIZE.height
            );
            g.setColor(Color.RED);
            g.drawString("Wave: " + (int) GameState.getWave(),
                    103 + epsilonFrame.getX() + Constants.SCREEN_SIZE.width,
                    20 + epsilonFrame.getY() + Constants.SCREEN_SIZE.height
            );
            g.setColor(Color.WHITE);
            g.drawString("Time: " + (int) GameState.getTime(),
                    151 + epsilonFrame.getX() + Constants.SCREEN_SIZE.width,
                    20 + epsilonFrame.getY() + Constants.SCREEN_SIZE.height
            );
        }

        Graphics2D g2d = (Graphics2D) g;
        if (views == null)
            return;
        for (int i = 0 ;i < views.size() ;i++){
            views.get(i).draw(g2d);
        }

    }

    public void setViews(ArrayList<ObjectView> views) {
        this.views = views;
    }

    public void setVariables() {
        FrameView epsilonFrame = ViewData.getLocalViews().get(ViewData.getViews().getFirst());
        this.epsilonFrame = epsilonFrame;
    }
}
