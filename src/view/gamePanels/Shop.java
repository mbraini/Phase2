package view.gamePanels;


import controller.Controller;
import controller.ViewInGameAbilityRequests;
import controller.enums.InGameAbilityType;
import data.Constants;
import model.GameState;
import model.ModelData;
import model.inGameAbilities.Banish;
import view.menuPanels.PIG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Shop extends PIG {
    ShopFrame shopFrame;
    JButton back;
    JPanel banish;
    JPanel empower;
    JPanel heal;
    JLabel xp;
    JLabel healL;
    JLabel banishL;
    JLabel empowerL;

    public Shop(ShopFrame shopFrame){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.shopFrame = shopFrame;
        initContainers();
        initBanish();
        initEmpower();
        initHeal();
        initLabels();
        initKeyListener();
        shopFrame.add(this);
        this.grabFocus();
        this.setFocusable(true);
        initBack();
        this.setVisible(true);
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
        xp = new JLabel();
        xp.setBounds(Constants.GAME_WIDTH / 5 * 2 ,Constants.GAME_HEIGHT / 12 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 12);
        xp.setText("XP :" +(int) GameState.getXp());
        xp.setBackground(Color.WHITE);
        xp.setOpaque(true);
        xp.setFont(new Font(null,Font.BOLD ,15));
        xp.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        xp.setHorizontalAlignment(JLabel.CENTER);
        xp.setVerticalAlignment(JLabel.CENTER);
        this.add(xp);

        healL = new JLabel();
        healL.setBounds(Constants.GAME_WIDTH / 10 ,Constants.GAME_HEIGHT / 12 * 7 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 12);
        healL.setText("Ares");
        healL.setBackground(Color.WHITE);
        healL.setOpaque(true);
        healL.setFont(new Font(null,Font.BOLD ,15));
        healL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        healL.setHorizontalAlignment(JLabel.CENTER);
        healL.setVerticalAlignment(JLabel.CENTER);
        this.add(healL);

        banishL = new JLabel();
        banishL.setBounds(Constants.GAME_WIDTH / 10 * 4 ,Constants.GAME_HEIGHT / 12 * 7 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 12);
        banishL.setText("Aceso");
        banishL.setBackground(Color.WHITE);
        banishL.setOpaque(true);
        banishL.setFont(new Font(null,Font.BOLD ,15));
        banishL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        banishL.setHorizontalAlignment(JLabel.CENTER);
        banishL.setVerticalAlignment(JLabel.CENTER);
        this.add(banishL);

        empowerL = new JLabel();
        empowerL.setBounds(Constants.GAME_WIDTH / 10 * 7 ,Constants.GAME_HEIGHT / 12 * 7 ,Constants.GAME_WIDTH / 10 * 2 ,Constants.GAME_HEIGHT / 12);
        empowerL.setText("Proteus");
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
        banish.setOpaque(false);
        empower.setOpaque(false);
        heal.setOpaque(false);
        this.add(banish);
        this.add(empower);
        this.add(heal);
    }

    private void initHeal() {
        banish.setBounds(getWidth() / 10 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        banish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewInGameAbilityRequests.abilityRequest(InGameAbilityType.heal);
            }
        });
    }

    private void initEmpower() {
        empower.setBounds(getWidth() / 10 * 4 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        empower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewInGameAbilityRequests.abilityRequest(InGameAbilityType.empower);
            }
        });
    }

    private void initBanish() {
        heal.setBounds(getWidth() / 10 * 7 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2);
        heal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewInGameAbilityRequests.abilityRequest(InGameAbilityType.banish);
            }
        });
    }

    void initBack(){
        back = new JButton();
        back.setBounds(getWidth() / 3 ,getHeight() / 10 * 8 ,getWidth() / 3 ,getHeight() / 10);
        back.setText("Back");
        back.setBackground(Color.WHITE);
        back.setOpaque(true);
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
        g.drawImage(Constants.heal ,getWidth() / 10 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
        g.drawImage(Constants.empower ,getWidth() / 10 * 4 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
        g.drawImage(Constants.banish ,getWidth() / 10 * 7 ,getHeight() / 10 * 3 ,getWidth() / 10 * 2 ,getHeight() / 10 * 2 ,null);
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
