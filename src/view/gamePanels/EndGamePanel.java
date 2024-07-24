package view.gamePanels;


import constants.Constants;
import controller.manager.GameState;
import view.Application;
import view.menuPanels.PIG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGamePanel extends PIG {
    int xpGained;
    int totalShots;
    int successfulShots;
    int enemyKilled;
    int timePassed;
    JButton menu;
    private final EndGameFrame endGameFrame;
    public EndGamePanel(EndGameFrame endGameFrame ,int xpGained ,int enemyKilled ,int totalShots ,int successfulShots ,int timePassed){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.endGameFrame = endGameFrame;

        this.timePassed = timePassed;
        this.successfulShots = successfulShots;
        this.enemyKilled = enemyKilled;
        this.xpGained = xpGained;
        this.totalShots = totalShots;
        endGameFrame.add(this);
        this.setFocusable(true);
        this.grabFocus();
        initMenu();
        this.setVisible(true);
    }

    private void initMenu() {
        menu = new JButton();
        menu.setBounds(getWidth() / 5 * 2 ,getHeight() / 14 * 12 ,getWidth() / 5 ,getHeight() / 14);
        menu.setText("menu");
        menu.setBackground(Color.WHITE);
        menu.setOpaque(true);
        menu.setFont(new Font(null,Font.BOLD ,15));
        menu.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        menu.setHorizontalTextPosition(JLabel.CENTER);
        menu.setVerticalTextPosition(JLabel.CENTER);
        menu.setFocusable(false);
        this.add(menu);

        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGameFrame.dispose();
                Application.startMainFrame();
            }
        });
        this.add(menu);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void end() {
        this.setVisible(false);
        endGameFrame.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constants.endGameImage ,getWidth() / 3 ,getHeight() / 6 ,getWidth() / 3 ,getHeight() / 3 ,null);
        g.setFont(new Font(null,Font.BOLD ,15));
        g.setColor(Color.MAGENTA);
        g.drawString("xp earned: " + xpGained ,getWidth() / 7 * 4 ,getHeight() / 14 * 8);
        g.setColor(Color.RED);
        g.drawString("successfulShots: " + successfulShots ,getWidth() / 7 * 2 ,getHeight() / 14 * 8);
        g.drawString("enemyKilled: " + enemyKilled,getWidth() / 7 * 3 ,getHeight() / 14 * 10);
        g.setColor(Color.WHITE);
        g.drawString("timePassed: " + timePassed,getWidth() / 7 ,getHeight() / 14 * 10);
        g.setColor(Color.BLUE);
        g.drawString("totalShots: " + totalShots,getWidth() / 7 * 5 ,getHeight() / 14 * 10);
    }
}
