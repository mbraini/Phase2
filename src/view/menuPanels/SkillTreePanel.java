package view.menuPanels;


import data.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SkillTreePanel extends PIG {
    JButton back;
    JPanel ares;
    JPanel aceso;
    JPanel proteus;
    JLabel aresL;
    JLabel acesoL;
    JLabel proteusL;
    JLabel xp;

    public SkillTreePanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initContainers();

        initAres();
        initAceso();
        initProteus();
        initBack();
        initLables();
        initAL();
    }

    private void initLables() {
        xp = new JLabel();
        xp.setBounds(Constants.GAME_WIDTH / 7 * 3 ,Constants.GAME_HEIGHT / 12 ,Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 12);
//        xp.setText("XP :" +(int) GameState.xp);
        xp.setBackground(Color.WHITE);
        xp.setOpaque(true);
        xp.setFont(new Font(null,Font.BOLD ,15));
        xp.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        xp.setHorizontalAlignment(JLabel.CENTER);
        xp.setVerticalAlignment(JLabel.CENTER);
        this.add(xp);

        aresL = new JLabel();
        aresL.setBounds(Constants.GAME_WIDTH / 10 ,Constants.GAME_HEIGHT / 12 * 7 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 12);
        aresL.setText("Ares");
        aresL.setBackground(Color.WHITE);
        aresL.setOpaque(true);
        aresL.setFont(new Font(null,Font.BOLD ,15));
        aresL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        aresL.setHorizontalAlignment(JLabel.CENTER);
        aresL.setVerticalAlignment(JLabel.CENTER);
        this.add(aresL);

        acesoL = new JLabel();
        acesoL.setBounds(Constants.GAME_WIDTH / 10 * 4 ,Constants.GAME_HEIGHT / 12 * 7 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 12);
        acesoL.setText("Aceso");
        acesoL.setBackground(Color.WHITE);
        acesoL.setOpaque(true);
        acesoL.setFont(new Font(null,Font.BOLD ,15));
        acesoL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        acesoL.setHorizontalAlignment(JLabel.CENTER);
        acesoL.setVerticalAlignment(JLabel.CENTER);
        this.add(acesoL);

        proteusL = new JLabel();
        proteusL.setBounds(Constants.GAME_WIDTH / 10 * 7 ,Constants.GAME_HEIGHT / 12 * 7 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 12);
        proteusL.setText("Proteus");
        proteusL.setBackground(Color.WHITE);
        proteusL.setOpaque(true);
        proteusL.setFont(new Font(null,Font.BOLD ,15));
        proteusL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        proteusL.setHorizontalAlignment(JLabel.CENTER);
        proteusL.setVerticalAlignment(JLabel.CENTER);
        this.add(proteusL);
    }

    private void initContainers() {
        ares = new JPanel();
        aceso = new JPanel();
        proteus = new JPanel();
        ares.setOpaque(false);
        aceso.setOpaque(false);
        proteus.setOpaque(false);
        this.add(ares);
        this.add(aceso);
        this.add(proteus);
    }

    private void initAres() {
        ares.setBounds(getWidth() / 10 ,getHeight() / 12 * 3 ,getWidth() / 10 * 2 ,getHeight() / 12 * 3);
        ares.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 750) {
//                    GameState.xp -= 750;
//                    Controller.updateConfigs();
//                    Ares.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void initAceso() {
        aceso.setBounds(getWidth() / 10 * 4 ,getHeight() / 12 * 3 ,getWidth() / 10 * 2 ,getHeight() / 12 * 3);
        aceso.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    Aceso.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    private void initProteus() {
        proteus.setBounds(getWidth() / 10 * 7 ,getHeight() / 12 * 3 ,getWidth() / 10 * 2 ,getHeight() / 12 * 3);
        proteus.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 1000) {
//                    GameState.xp -= 1000;
//                    Controller.updateConfigs();
//                    Proteus.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void initBack() {
        back = new JButton();
        back.setBounds(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 12 * 10 ,Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 12);
        back.setText("back");
        back.setBackground(Color.WHITE);
        back.setOpaque(true);
        back.setFont(new Font(null,Font.BOLD ,15));
        back.setHorizontalTextPosition(JLabel.RIGHT);
        back.setVerticalTextPosition(JLabel.TOP);
        back.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        back.setFocusable(false);
        this.add(back);
    }
    private void initAL() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.menuPanel.start();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Constants.ares ,getWidth() / 10 ,getHeight() / 12 * 3 ,getWidth() / 10 * 2 ,getHeight() / 12 * 3 ,null);
        g.drawImage(Constants.aceso ,getWidth() / 10 * 4 ,getHeight() / 12 * 3 ,getWidth() / 10 * 2 ,getHeight() / 12 * 3 ,null);
        g.drawImage(Constants.proteus ,getWidth() / 10 * 7 ,getHeight() / 12 * 3 ,getWidth() / 10 * 2 ,getHeight() / 12 * 3 ,null);
    }

    @Override
    public void start() {
        this.setVisible(true);
//        xp.setText("XP :" +(int) GameState.xp);
    }

    @Override
    public void end() {
        this.setVisible(false);
    }
}
