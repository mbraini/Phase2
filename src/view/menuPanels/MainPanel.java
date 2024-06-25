package view.menuPanels;


import data.Constants;

import java.awt.*;

public class MainPanel extends PIG {
    public MainPanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }
    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
