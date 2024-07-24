package view.menuPanels;


import constants.Constants;
import view.objectViews.panels.MyButton;
import view.objectViews.panels.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutorial extends PIG {

     MyLabel shoot;
     MyLabel shootGuide;
     MyLabel movement;
     MyLabel movementGuide1;
     MyLabel movementGuide2;
     MyLabel movementGuide3;
     MyLabel movementGuide4;
     JLabel ares;
     JLabel aresGuide;
     JLabel aceso;
     JLabel acesoGuide;
     JLabel proteus;
     JLabel proteusGuide;
     MyLabel shop;
     MyLabel shopGuide;
     private MyButton back;
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
        shop = new MyLabel(
                new Point(Constants.GAME_WIDTH / 25 ,Constants.GAME_HEIGHT / 15),
                new Dimension(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15),
                "Shop",
                this
        );

        shopGuide = new MyLabel(
                new Point(Constants.GAME_WIDTH / 25 * 2 + Constants.GAME_WIDTH / 5,Constants.GAME_HEIGHT / 15),
                new Dimension(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15),
                "P_KEY",
                this
        );
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
        back = new MyButton(
                new Point(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 15 * 13),
                new Dimension(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 15),
                "back",
                this
        );
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
        movement = new MyLabel(
                new Point(Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15 * 4),
                new Dimension(Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15),
                "Movement",
                this
        );
        movementGuide1 = new MyLabel(
                new Point(Constants.GAME_WIDTH / 7 * 3 ,Constants.GAME_HEIGHT / 15 * 3),
                new Dimension(Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15),
                "UP_KEY",
                this
        );

        movementGuide2 = new MyLabel(
                new Point(Constants.GAME_WIDTH / 7 * 5 ,Constants.GAME_HEIGHT / 15 * 3),
                new Dimension(Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15),
                "DOWN_KEY",
                this
        );

        movementGuide3 = new MyLabel(
                new Point(Constants.GAME_WIDTH / 7 * 3 ,Constants.GAME_HEIGHT / 15 * 5),
                new Dimension(Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15),
                "LEFT_KEY",
                this
        );

        movementGuide4 = new MyLabel(
                new Point(Constants.GAME_WIDTH / 7 * 5 ,Constants.GAME_HEIGHT / 15 * 5),
                new Dimension(Constants.GAME_WIDTH / 7 ,Constants.GAME_HEIGHT / 15),
                "RIGHT_KEY",
                this
        );
    }

    private void initShot() {
        shoot = new MyLabel(
                new Point(Constants.GAME_WIDTH / 25 * 3 + Constants.GAME_WIDTH / 5 * 2 ,Constants.GAME_HEIGHT / 15),
                new Dimension(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15),
                "Shoot",
                this
        );

        shootGuide = new MyLabel(
                new Point(Constants.GAME_WIDTH / 25 * 4 + Constants.GAME_WIDTH / 5 * 3 ,Constants.GAME_HEIGHT / 15),
                new Dimension(Constants.GAME_WIDTH / 5 ,Constants.GAME_HEIGHT / 15),
                "Left_Mouse_Button",
                this
        );
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
