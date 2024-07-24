package view.menuPanels;


import constants.SizeConstants;

import java.awt.*;

public class MainPanel extends PIG {
    public MainPanel(){
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
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
