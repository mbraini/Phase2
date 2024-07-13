package view.gamePanels;


import controller.Controller;
import controller.enums.InGameAbilityType;
import data.Constants;
import model.GameState;
import view.ViewData;
import view.menuPanels.PIG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shop extends PIG {
    private ShopFrame shopFrame;
    private JButton back;
    private JPanel banish;
    private JPanel empower;
    private JPanel heal;
    private JPanel dismay;
    private JPanel slumber;
    private JPanel slaughter;
    private JLabel xp;
    private JLabel healL;
    private JLabel banishL;
    private JLabel empowerL;
    private JLabel dismayL;
    private JLabel slumberL;
    private JLabel slaughterL;

    public Shop(ShopFrame shopFrame){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.shopFrame = shopFrame;
        initContainers();
        initBanish();
        initEmpower();
        initHeal();
        initDismay();
        initSlumber();
        initSlaughter();
        initLabels();
        initKeyListener();
        shopFrame.add(this);
        this.grabFocus();
        this.setFocusable(true);
        initBack();
        this.setVisible(true);
    }

    private void initSlaughter() {
        slaughter.setBounds(getWidth() / 10 * 7 ,(int)(getHeight() / 16 * 8.5) ,getWidth() / 10 * 2 ,getHeight() / 16 * 3);
        slaughter.setFont(new Font(null,Font.BOLD ,15));
        slaughter.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        slaughter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.abilityRequest(InGameAbilityType.slaughter);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                slaughter.setFont(new Font(null,Font.BOLD ,15));
                slaughter.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                slaughter.setFont(new Font(null,Font.BOLD ,15));
                slaughter.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    private void initSlumber() {
        slumber.setBounds(getWidth() / 10 * 4 ,(int)(getHeight() / 16 * 8.5) ,getWidth() / 10 * 2 ,getHeight() / 16 * 3);
        slumber.setFont(new Font(null,Font.BOLD ,15));
        slumber.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        slumber.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.abilityRequest(InGameAbilityType.slumber);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                slumber.setFont(new Font(null,Font.BOLD ,15));
                slumber.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                slumber.setFont(new Font(null,Font.BOLD ,15));
                slumber.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    private void initDismay() {
        dismay.setBounds(getWidth() / 10 ,(int)(getHeight() / 16 * 8.5) ,getWidth() / 10 * 2 ,getHeight() / 16 * 3);
        dismay.setFont(new Font(null,Font.BOLD ,15));
        dismay.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        dismay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.abilityRequest(InGameAbilityType.dismay);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                dismay.setFont(new Font(null,Font.BOLD ,15));
                dismay.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dismay.setFont(new Font(null,Font.BOLD ,15));
                dismay.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    private void initKeyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'p' && GameState.isPause()){
                    end();
                }
            }
        });
    }

    private void initLabels() {
        initXPL();
        initHealL();
        initBanishL();
        initEmpowerL();
        initDismayL();
        initSlumberL();
        initSlaughterL();
    }

    private void initSlaughterL() {
        slaughterL = new JLabel();
        slaughterL.setBounds(Constants.GAME_WIDTH / 10 * 7 ,Constants.GAME_HEIGHT / 16 * 12 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 16);
        slaughterL.setText("slaughter");
        slaughterL.setBackground(Color.WHITE);
        slaughterL.setOpaque(true);
        slaughterL.setFont(new Font(null,Font.BOLD ,15));
        slaughterL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        slaughterL.setHorizontalAlignment(JLabel.CENTER);
        slaughterL.setVerticalAlignment(JLabel.CENTER);
        this.add(slaughterL);
    }

    private void initSlumberL() {
        slumberL = new JLabel();
        slumberL.setBounds(Constants.GAME_WIDTH / 10 * 4 ,Constants.GAME_HEIGHT / 16 * 12 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 16);
        slumberL.setText("slumber");
        slumberL.setBackground(Color.WHITE);
        slumberL.setOpaque(true);
        slumberL.setFont(new Font(null,Font.BOLD ,15));
        slumberL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        slumberL.setHorizontalAlignment(JLabel.CENTER);
        slumberL.setVerticalAlignment(JLabel.CENTER);
        this.add(slumberL);
    }

    private void initDismayL() {
        dismayL = new JLabel();
        dismayL.setBounds(Constants.GAME_WIDTH / 10 ,Constants.GAME_HEIGHT / 16 * 12 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 16);
        dismayL.setText("dismay");
        dismayL.setBackground(Color.WHITE);
        dismayL.setOpaque(true);
        dismayL.setFont(new Font(null,Font.BOLD ,15));
        dismayL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        dismayL.setHorizontalAlignment(JLabel.CENTER);
        dismayL.setVerticalAlignment(JLabel.CENTER);
        this.add(dismayL);
    }

    private void initXPL() {
        xp = new JLabel();
        xp.setBounds(Constants.GAME_WIDTH / 10 * 4 ,Constants.GAME_HEIGHT / 16 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 16);
        xp.setText("XP :" +(int) GameState.getXp());
        xp.setBackground(Color.WHITE);
        xp.setOpaque(true);
        xp.setFont(new Font(null,Font.BOLD ,15));
        xp.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        xp.setHorizontalAlignment(JLabel.CENTER);
        xp.setVerticalAlignment(JLabel.CENTER);
        this.add(xp);
    }

    private void initHealL() {
        healL = new JLabel();
        healL.setBounds(Constants.GAME_WIDTH / 10 ,(int) (Constants.GAME_HEIGHT / 16 * 6.5) ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 16);
        healL.setText("heal");
        healL.setBackground(Color.WHITE);
        healL.setOpaque(true);
        healL.setFont(new Font(null,Font.BOLD ,15));
        healL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        healL.setHorizontalAlignment(JLabel.CENTER);
        healL.setVerticalAlignment(JLabel.CENTER);
        this.add(healL);
    }

    private void initBanishL() {
        banishL = new JLabel();
        banishL.setBounds(Constants.GAME_WIDTH / 10 * 4 ,(int) (Constants.GAME_HEIGHT / 16 * 6.5) ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 16);
        banishL.setText("empower");
        banishL.setBackground(Color.WHITE);
        banishL.setOpaque(true);
        banishL.setFont(new Font(null,Font.BOLD ,15));
        banishL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        banishL.setHorizontalAlignment(JLabel.CENTER);
        banishL.setVerticalAlignment(JLabel.CENTER);
        this.add(banishL);
    }

    private void initEmpowerL() {
        empowerL = new JLabel();
        empowerL.setBounds(Constants.GAME_WIDTH / 10 * 7 ,(int) (Constants.GAME_HEIGHT / 16 * 6.5) ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 16);
        empowerL.setText("banish");
        empowerL.setBackground(Color.WHITE);
        empowerL.setOpaque(true);
        empowerL.setFont(new Font(null,Font.BOLD ,15));
        empowerL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        empowerL.setHorizontalAlignment(JLabel.CENTER);
        empowerL.setVerticalAlignment(JLabel.CENTER);
        this.add(empowerL);
    }

    private void initContainers() {
        banish = new JPanel();
        empower = new JPanel();
        heal = new JPanel();
        dismay = new JPanel();
        slumber = new JPanel();
        slaughter = new JPanel();
        banish.setOpaque(false);
        empower.setOpaque(false);
        heal.setOpaque(false);
        dismay.setOpaque(false);
        slumber.setOpaque(false);
        slaughter.setOpaque(false);
        this.add(banish);
        this.add(empower);
        this.add(heal);
        this.add(dismay);
        this.add(slumber);
        this.add(slaughter);
    }

    private void initHeal() {
        heal.setBounds(getWidth() / 10 ,getHeight() / 16 * 3 ,getWidth() / 10 * 2 ,getHeight() / 16 * 3);
        heal.setFont(new Font(null,Font.BOLD ,15));
        heal.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        heal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.abilityRequest(InGameAbilityType.heal);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                heal.setFont(new Font(null,Font.BOLD ,15));
                heal.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                heal.setFont(new Font(null,Font.BOLD ,15));
                heal.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    private void initEmpower() {
        empower.setBounds(getWidth() / 10 * 4 ,getHeight() / 16 * 3 ,getWidth() / 10 * 2 ,getHeight() / 16 * 3);
        empower.setFont(new Font(null,Font.BOLD ,15));
        empower.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        empower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.abilityRequest(InGameAbilityType.empower);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                empower.setFont(new Font(null,Font.BOLD ,15));
                empower.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                empower.setFont(new Font(null,Font.BOLD ,15));
                empower.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    private void initBanish() {
        banish.setBounds(getWidth() / 10 * 7 ,getHeight() / 16 * 3 ,getWidth() / 10 * 2 ,getHeight() / 16 * 3);
        banish.setFont(new Font(null,Font.BOLD ,15));
        banish.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        banish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.abilityRequest(InGameAbilityType.banish);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                banish.setFont(new Font(null,Font.BOLD ,15));
                banish.setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                banish.setFont(new Font(null,Font.BOLD ,15));
                banish.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
    }

    void initBack(){
        back = new JButton();
        back.setBounds(getWidth() / 10 * 4 ,getHeight() / 16 * 14 ,getWidth() / 10 * 2 ,getHeight() / 16);
        back.setText("Back");
        back.setBackground(Color.WHITE);
        back.setOpaque(true);
        back.setFont(new Font(null,Font.BOLD ,15));
        back.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        back.setHorizontalTextPosition(JLabel.RIGHT);
        back.setVerticalTextPosition(JLabel.TOP);
        back.setFocusable(false);
        this.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        xp.setText(ViewData.getXp() + "");
        g.drawImage(Constants.heal ,heal.getX() ,heal.getY() ,heal.getWidth() ,heal.getHeight() ,null);
        g.drawImage(Constants.empower ,empower.getX() ,empower.getY() ,empower.getWidth() ,empower.getHeight() ,null);
        g.drawImage(Constants.banish ,banish.getX() ,banish.getY() ,banish.getWidth() ,banish.getHeight() ,null);
    }

    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void end() {
        Controller.resume();
        shopFrame.dispose();
    }
}
