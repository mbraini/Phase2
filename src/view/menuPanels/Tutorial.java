package view.menuPanels;


import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutorial extends PIG {

     JLabel shoot;
     JLabel shootGuide;
     JLabel movement;
     JLabel movementGuide1;
     JLabel movementGuide2;
     JLabel movementGuide3;
     JLabel movementGuide4;
     JLabel ares;
     JLabel aresGuide;
     JLabel aceso;
     JLabel acesoGuide;
     JLabel proteus;
     JLabel proteusGuide;
     JLabel shop;
     JLabel shopGuide;
     JButton back;
     public Tutorial(){
         this.setLayout(null);
         this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
         this.setBackground(Color.BLACK);
         this.setVisible(false);

         initShot();
         initMovement();
         initAbilities();
         initShop();
         initBack();
         initBackAL();
     }

    private void initShop() {
        shop = new JLabel();
        shop.setBounds(Constants.GAME_WIDTH / 25 ,Constants.GAME_HEIGHT / 15 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        shop.setText("Shop");
        shop.setBackground(Color.WHITE);
        shop.setOpaque(true);
        shop.setFont(new Font(null,Font.BOLD ,15));
        shop.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        shop.setHorizontalAlignment(JLabel.CENTER);
        shop.setVerticalAlignment(JLabel.CENTER);
        this.add(shop);

        shopGuide = new JLabel();
        shopGuide.setBounds(Constants.GAME_WIDTH / 25 * 2 + Constants.GAME_WIDTH / 5,Constants.GAME_HEIGHT / 15 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        shopGuide.setText("P_KEY");
        shopGuide.setBackground(Color.WHITE);
        shopGuide.setOpaque(true);
        shopGuide.setFont(new Font(null,Font.BOLD ,15));
        shopGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        shopGuide.setHorizontalAlignment(JLabel.CENTER);
        shopGuide.setVerticalAlignment(JLabel.CENTER);
        this.add(shopGuide);
    }

    private void initBackAL() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.menuPanel.start();
            }
        });
    }

    private void initBack() {
        back = new JButton();
        back.setBounds(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 15 * 13 ,Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 15);
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

    private void initAbilities() {
        ares = new JLabel();
        ares.setBounds(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15 * 7 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        ares.setText("Ares");
        ares.setBackground(Color.WHITE);
        ares.setOpaque(true);
        ares.setFont(new Font(null,Font.BOLD ,15));
        ares.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        ares.setHorizontalAlignment(JLabel.CENTER);
        ares.setVerticalAlignment(JLabel.CENTER);
        this.add(ares);

        aresGuide = new JLabel();
        aresGuide.setBounds(Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 15 * 7 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        aresGuide.setText("Q_KEY");
        aresGuide.setBackground(Color.WHITE);
        aresGuide.setOpaque(true);
        aresGuide.setFont(new Font(null,Font.BOLD ,15));
        aresGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        aresGuide.setHorizontalAlignment(JLabel.CENTER);
        aresGuide.setVerticalAlignment(JLabel.CENTER);
        this.add(aresGuide);

        aceso = new JLabel();
        aceso.setBounds(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15 * 9 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        aceso.setText("Aceso");
        aceso.setBackground(Color.WHITE);
        aceso.setOpaque(true);
        aceso.setFont(new Font(null,Font.BOLD ,15));
        aceso.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        aceso.setHorizontalAlignment(JLabel.CENTER);
        aceso.setVerticalAlignment(JLabel.CENTER);
        this.add(aceso);

        acesoGuide = new JLabel();
        acesoGuide.setBounds(Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 15 * 9 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        acesoGuide.setText("W_KEY");
        acesoGuide.setBackground(Color.WHITE);
        acesoGuide.setOpaque(true);
        acesoGuide.setFont(new Font(null,Font.BOLD ,15));
        acesoGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        acesoGuide.setHorizontalAlignment(JLabel.CENTER);
        acesoGuide.setVerticalAlignment(JLabel.CENTER);
        this.add(acesoGuide);

        proteus = new JLabel();
        proteus.setBounds(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15 * 11 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        proteus.setText("Proteus");
        proteus.setBackground(Color.WHITE);
        proteus.setOpaque(true);
        proteus.setFont(new Font(null,Font.BOLD ,15));
        proteus.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        proteus.setHorizontalAlignment(JLabel.CENTER);
        proteus.setVerticalAlignment(JLabel.CENTER);
        this.add(proteus);

        proteusGuide = new JLabel();
        proteusGuide.setBounds(Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 15 * 11 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        proteusGuide.setText("E_KEY");
        proteusGuide.setBackground(Color.WHITE);
        proteusGuide.setOpaque(true);
        proteusGuide.setFont(new Font(null,Font.BOLD ,15));
        proteusGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        proteusGuide.setHorizontalAlignment(JLabel.CENTER);
        proteusGuide.setVerticalAlignment(JLabel.CENTER);
        this.add(proteusGuide);

    }

    private void initMovement() {
        movement = new JLabel();
        movement.setBounds(Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15 * 4 ,Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15);
        movement.setText("Movement");
        movement.setBackground(Color.WHITE);
        movement.setOpaque(true);
        movement.setFont(new Font(null,Font.BOLD ,15));
        movement.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        movement.setHorizontalAlignment(JLabel.CENTER);
        movement.setVerticalAlignment(JLabel.CENTER);
        this.add(movement);

        movementGuide1 = new JLabel();
        movementGuide1.setBounds(Constants.GAME_WIDTH / 7 * 3 ,Constants.GAME_HEIGHT / 15 * 3 ,Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15);
        movementGuide1.setText("UP_KEY");
        movementGuide1.setBackground(Color.WHITE);
        movementGuide1.setOpaque(true);
        movementGuide1.setFont(new Font(null,Font.BOLD ,15));
        movementGuide1.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        movementGuide1.setHorizontalAlignment(JLabel.CENTER);
        movementGuide1.setVerticalAlignment(JLabel.CENTER);
        this.add(movementGuide1);

        movementGuide2 = new JLabel();
        movementGuide2.setBounds(Constants.GAME_WIDTH / 7 * 5 ,Constants.GAME_HEIGHT / 15 * 3 ,Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15);
        movementGuide2.setText("DOWN_KEY");
        movementGuide2.setBackground(Color.WHITE);
        movementGuide2.setOpaque(true);
        movementGuide2.setFont(new Font(null,Font.BOLD ,15));
        movementGuide2.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        movementGuide2.setHorizontalAlignment(JLabel.CENTER);
        movementGuide2.setVerticalAlignment(JLabel.CENTER);
        this.add(movementGuide2);

        movementGuide3 = new JLabel();
        movementGuide3.setBounds(Constants.GAME_WIDTH / 7 * 3 ,Constants.GAME_HEIGHT / 15 * 5 ,Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15);
        movementGuide3.setText("LEFT_KEY");
        movementGuide3.setBackground(Color.WHITE);
        movementGuide3.setOpaque(true);
        movementGuide3.setFont(new Font(null,Font.BOLD ,15));
        movementGuide3.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        movementGuide3.setHorizontalAlignment(JLabel.CENTER);
        movementGuide3.setVerticalAlignment(JLabel.CENTER);
        this.add(movementGuide3);

        movementGuide4 = new JLabel();
        movementGuide4.setBounds(Constants.GAME_WIDTH / 7 * 5 ,Constants.GAME_HEIGHT / 15 * 5 ,Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15);
        movementGuide4.setText("RIGHT_KEY");
        movementGuide4.setBackground(Color.WHITE);
        movementGuide4.setOpaque(true);
        movementGuide4.setFont(new Font(null,Font.BOLD ,15));
        movementGuide4.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        movementGuide4.setHorizontalAlignment(JLabel.CENTER);
        movementGuide4.setVerticalAlignment(JLabel.CENTER);
        this.add(movementGuide4);
    }

    private void initShot() {
        shoot = new JLabel();
        shoot.setBounds(Constants.GAME_WIDTH / 25 * 3 + Constants.GAME_WIDTH / 5 * 2 ,Constants.GAME_HEIGHT / 15 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        shoot.setText("Shoot");
        shoot.setBackground(Color.WHITE);
        shoot.setOpaque(true);
        shoot.setFont(new Font(null,Font.BOLD ,15));
        shoot.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        shoot.setHorizontalAlignment(JLabel.CENTER);
        shoot.setVerticalAlignment(JLabel.CENTER);
        this.add(shoot);

        shootGuide = new JLabel();
        shootGuide.setBounds(Constants.GAME_WIDTH / 25 * 4 + Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 15 ,Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15);
        shootGuide.setText("Left_Mouse_Button");
        shootGuide.setBackground(Color.WHITE);
        shootGuide.setOpaque(true);
        shootGuide.setFont(new Font(null,Font.BOLD ,14));
        shootGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        shootGuide.setHorizontalAlignment(JLabel.CENTER);
        shootGuide.setVerticalAlignment(JLabel.CENTER);
        this.add(shootGuide);
    }


    @Override
    public void start() {
        setVisible(true);
    }

    @Override
    public void end() {
        setVisible(false);
    }
}
