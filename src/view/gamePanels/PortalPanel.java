package view.gamePanels;

import constants.SizeConstants;
import controller.Controller;
import view.menuPanels.PIG;
import view.objectViews.panels.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PortalPanel extends PIG {

    private PortalFrame portalFrame;
    private final int PR;
    private JPanel pay;
    private MyLabel payL1;
    private MyLabel payL2;
    private MyLabel payL3;
    private JPanel decline;
    private MyLabel declineL1;
    private MyLabel choose;

    public PortalPanel(PortalFrame portalFrame ,int PR) {
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.portalFrame = portalFrame;
        this.PR = PR;

        initContainers();
        initPayPR();
        initDecline();
        initPayLabels();
        initDeclineLabels();
        portalFrame.add(this);
        this.setFocusable(true);
        this.grabFocus();
        initChoose();
        this.setVisible(true);
        portalFrame.setVisible(true);
    }

    private void initChoose() {
        choose = new MyLabel(
                new Point(
                        getWidth() / 5 * 2,
                        getHeight() / 9
                ),
                new Dimension(
                        getWidth() / 5 ,
                        getHeight() / 7
                ),
                "Choose One!",
                this
        );
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
        declineL1 = new MyLabel(
                new Point(
                        pay.getWidth() / 7 ,
                        pay.getHeight() / 7 * 3
                ),
                new Dimension(
                        pay.getWidth() / 7 * 5 ,
                        pay.getHeight() / 7
                ),
                "gain " + PR / 10 + " " + "XP",
                this
        );
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
        payL3 = new MyLabel(
                new Point(
                        pay.getWidth() / 7 ,
                        pay.getHeight() / 7 * 5
                ),
                new Dimension(
                        pay.getWidth() / 7 * 5,
                        pay.getHeight() / 7
                ),
                "save the game",
                this
        );
    }

    private void initPayL2() {
        payL2 = new MyLabel(
                new Point(
                        pay.getWidth() / 7 ,
                        pay.getHeight() / 7 * 3
                ),
                new Dimension(
                        pay.getWidth() / 7 * 5,
                        pay.getHeight() / 7
                ),
                "lose " + PR + " " + "XP",
                this
        );
    }

    private void initPayL1() {
        payL1 = new MyLabel(
                new Point(
                        pay.getWidth() / 7 ,
                        pay.getHeight() / 7
                ),
                new Dimension(
                        pay.getWidth() / 7 * 5 ,
                        pay.getHeight() / 7
                ),
                "gain 10 hp",
                this
        );
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
                Controller.addXP(PR / 10);
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
        repaint();
    }

    @Override
    public void end() {
        setVisible(false);
        portalFrame.dispose();
    }
}
