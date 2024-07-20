package view.gamePanels;

import constants.Constants;
import controller.Controller;
import controller.enums.InGameAbilityType;
import view.menuPanels.PIG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PortalPanel extends PIG {

    private PortalFrame portalFrame;
    int PR;
    private JPanel pay;
    private JLabel payL1;
    private JLabel payL2;
    private JLabel payL3;
    private JPanel decline;
    private JLabel declineL1;

    public PortalPanel(PortalFrame portalFrame ,int PR) {
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.portalFrame = portalFrame;
        this.PR = PR;

        initContainers();
        initPayPR();
        initDecline();
        initPayLabels();
        initDeclineLabels();
        portalFrame.add(this);
        portalFrame.setVisible(true);
    }

    private void initDeclineLabels() {
        initDeclineL1();
        decline.add(declineL1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void initDeclineL1() {
        declineL1 = new JLabel();
        declineL1.setBounds(
                pay.getWidth() / 7 ,
                pay.getHeight() / 7 * 3  ,
                pay.getWidth() / 7 * 5 ,
                pay.getHeight() / 7
        );
        declineL1.setText("gain " + PR + " " + "XP");
        declineL1.setForeground(Color.WHITE);
        declineL1.setOpaque(false);
        declineL1.setFont(new Font(null,Font.BOLD ,15));
        declineL1.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        declineL1.setHorizontalAlignment(JLabel.CENTER);
        declineL1.setVerticalAlignment(JLabel.CENTER);
        this.add(declineL1);
    }

    private void initPayLabels() {
        initPayL1();
        initPayL2();
        initPayL3();
        pay.add(payL1);
        pay.add(payL2);
        pay.add(payL3);
    }

    private void initPayL3() {
        payL3 = new JLabel();
        payL3.setBounds(
                pay.getWidth() / 7 ,
                pay.getHeight() / 7 * 5  ,
                pay.getWidth() / 7 * 5,
                pay.getHeight() / 7
        );
        payL3.setText("save the game");
        payL3.setForeground(Color.WHITE);
        payL3.setOpaque(false);
        payL3.setFont(new Font(null,Font.BOLD ,15));
        payL3.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        payL3.setHorizontalAlignment(JLabel.CENTER);
        payL3.setVerticalAlignment(JLabel.CENTER);
        this.add(payL3);
    }

    private void initPayL2() {
        payL2 = new JLabel();
        payL2.setBounds(
                pay.getWidth() / 7 ,
                pay.getHeight() / 7 * 3,
                pay.getWidth() / 7 * 5,
                pay.getHeight() / 7
        );
        payL2.setText("lose " + PR + " " + "XP");
        payL2.setForeground(Color.WHITE);
        payL2.setOpaque(false);
        payL2.setFont(new Font(null,Font.BOLD ,15));
        payL2.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        payL2.setHorizontalAlignment(JLabel.CENTER);
        payL2.setVerticalAlignment(JLabel.CENTER);
        this.add(payL2);
    }

    private void initPayL1() {
        payL1 = new JLabel();
        payL1.setBounds(
                pay.getWidth() / 7 ,
                pay.getHeight() / 7  ,
                pay.getWidth() / 7 * 5 ,
                pay.getHeight() / 7
        );
        payL1.setText("gain 10 hp");
        payL1.setForeground(Color.WHITE);
        payL1.setOpaque(false);
        payL1.setFont(new Font(null,Font.BOLD ,15));
        payL1.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        payL1.setHorizontalAlignment(JLabel.CENTER);
        payL1.setVerticalAlignment(JLabel.CENTER);
        this.add(payL1);
    }

    private void initContainers() {
        pay = new JPanel();
        decline = new JPanel();
        pay.setOpaque(false);
        decline.setOpaque(false);
        add(decline);
        add(pay);
        pay.setLayout(null);
        decline.setLayout(null);
    }

    private void initDecline() {
        decline.setBounds(getWidth() / 9 * 5 ,getHeight() / 3 ,getWidth() / 3 ,getHeight() / 3);
        decline.setFont(new Font(null,Font.BOLD ,15));
        decline.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        decline.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.addXP(PR);
                Controller.resume();
                end();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                decline.setFont(new Font(null,Font.BOLD ,15));
                decline.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                decline.setFont(new Font(null,Font.BOLD ,15));
                decline.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    private void initPayPR() {
        pay.setBounds(getWidth() / 9 ,getHeight() / 3 ,getWidth() / 3 ,getHeight() / 3);
        pay.setFont(new Font(null,Font.BOLD ,15));
        pay.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        pay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Controller.addXP(-PR)) {
                    Controller.saveGameInPortal();
                    Controller.resume();
                    end();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                pay.setFont(new Font(null,Font.BOLD ,15));
                pay.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pay.setFont(new Font(null,Font.BOLD ,15));
                pay.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void end() {
        setVisible(false);
        portalFrame.dispose();
    }
}
