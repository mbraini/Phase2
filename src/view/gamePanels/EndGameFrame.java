package view.gamePanels;

import constants.SizeConstants;

import javax.swing.*;

public class EndGameFrame extends JFrame {

    public EndGameFrame(){
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(SizeConstants.GAME_WIDTH + SizeConstants.barD.width , SizeConstants.GAME_HEIGHT + SizeConstants.barD.height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
