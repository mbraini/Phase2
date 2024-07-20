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
    JLabel xp;
    JButton menu;
    private final EndGameFrame endGameFrame;
    public EndGamePanel(EndGameFrame endGameFrame){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.endGameFrame = endGameFrame;
        initMenu();
        initXP();
        endGameFrame.add(this);
        endGameFrame.setVisible(true);
    }

    private void initXP() {
        xp = new JLabel();
        xp.setBounds(getWidth() / 3 ,getHeight() / 10 * 6 ,getWidth() / 3 ,getHeight() / 10);
        xp.setFont(new Font(null,Font.BOLD ,15));
        xp.setText("XP: " + (int) GameState.getXp());
        xp.setBackground(Color.WHITE);
        xp.setOpaque(true);
        xp.setHorizontalAlignment(JLabel.CENTER);
        xp.setVerticalAlignment(JLabel.CENTER);
        this.add(xp);
    }

    private void initMenu() {
        menu = new JButton();
        menu.setBounds(getWidth() / 3 ,getHeight() / 10 * 8 ,getWidth() / 3 ,getHeight() / 10);
        menu.setFont(new Font(null,Font.BOLD ,15));
        menu.setText("menu");
        menu.setBackground(Color.WHITE);
        menu.setOpaque(true);
        menu.setHorizontalTextPosition(JLabel.RIGHT);
        menu.setVerticalTextPosition(JLabel.TOP);
        menu.setFocusable(false);

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
        setSize(getSize());
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
    }
}
