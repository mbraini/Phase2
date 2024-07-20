package view.gamePanels;

import constants.Constants;

import javax.swing.*;

public class PortalFrame extends JFrame {

    public PortalFrame(){
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Constants.GAME_WIDTH + Constants.barD.width ,Constants.GAME_HEIGHT + Constants.barD.height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
