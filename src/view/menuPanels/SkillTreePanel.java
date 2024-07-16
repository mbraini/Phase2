package view.menuPanels;


import controller.Controller;
import controller.enums.SkillTreeAbilityType;
import data.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SkillTreePanel extends PIG {
    private JButton back;
    private JPanel ares;
    private JPanel aceso;
    private JPanel proteus;
    private JPanel astrape;
    private JPanel chiron;
    private JPanel dolus;
    private JPanel empusa;
    private JPanel melampus;
    private JPanel cerberus;
    private JPanel athena;
    private JLabel aresL;
    private JLabel acesoL;
    private JLabel proteusL;
    private JLabel astrapeL;
    private JLabel chironL;
    private JLabel dolusL;
    private JLabel empusaL;
    private JLabel melampusL;
    private JLabel cerberusL;
    private JLabel athenaL;
    private JLabel xp;
    private final int widthUnit;
    private final int heightUnit;

    public SkillTreePanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        widthUnit = (int) (getWidth() / 10.4);
        heightUnit  = (int) (getHeight() / 19.5);

        initContainers();

        initAceso();
        initMelampus();
        initChiron();
        initProteus();
        initEmpusa();
        initDolus();
        initAres();
        initAstrape();
        initCerberus();
        initAthena();
        initBack();
        initAL();
    }

    private void initAthena() {
        athena.setBounds(
                (int) (widthUnit * 7.8),
                heightUnit * 12,
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        athena.setFont(new Font(null,Font.BOLD ,15));
        athena.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        athena.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    athena.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        athenaL = new JLabel();
        athenaL.setBounds(
                (int) (widthUnit * 7.8),
                (int) (heightUnit * 15.5),
                (int) (widthUnit * 1.6),
                heightUnit
        );
        athenaL.setText("athena");
        athenaL.setForeground(Color.WHITE);
        athenaL.setOpaque(false);
        athenaL.setFont(new Font(null,Font.BOLD ,15));
        athenaL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        athenaL.setHorizontalAlignment(JLabel.CENTER);
        athenaL.setVerticalAlignment(JLabel.CENTER);
        this.add(athenaL);
    }

    private void initCerberus() {
        cerberus.setBounds(
                (int) (widthUnit * 5.7),
                heightUnit * 12,
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        cerberus.setFont(new Font(null,Font.BOLD ,15));
        cerberus.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        cerberus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    cerberus.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        cerberusL = new JLabel();
        cerberusL.setBounds(
                (int) (widthUnit * 5.7),
                (int) (heightUnit * 15.5),
                (int) (widthUnit * 1.6),
                heightUnit
        );
        cerberusL.setText("cerberus");
        cerberusL.setForeground(Color.WHITE);
        cerberusL.setOpaque(false);
        cerberusL.setFont(new Font(null,Font.BOLD ,15));
        cerberusL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        cerberusL.setHorizontalAlignment(JLabel.CENTER);
        cerberusL.setVerticalAlignment(JLabel.CENTER);
        this.add(cerberusL);
    }

    private void initAstrape() {
        astrape.setBounds(
                (int) (widthUnit * 6.75),
                (int) (heightUnit * 6.5),
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        astrape.setFont(new Font(null,Font.BOLD ,15));
        astrape.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        astrape.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    astrape.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        astrapeL = new JLabel();
        astrapeL.setBounds(
                (int) (widthUnit * 6.75),
                heightUnit * 10,
                (int) (widthUnit * 1.6),
                heightUnit
        );
        astrapeL.setText("astrape");
        astrapeL.setForeground(Color.WHITE);
        astrapeL.setOpaque(false);
        astrapeL.setFont(new Font(null,Font.BOLD ,15));
        astrapeL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        astrapeL.setHorizontalAlignment(JLabel.CENTER);
        astrapeL.setVerticalAlignment(JLabel.CENTER);
        this.add(astrapeL);
    }

    private void initDolus() {
        dolus.setBounds(
                (int) (widthUnit * 3.6),
                heightUnit * 12,
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        dolus.setFont(new Font(null,Font.BOLD ,15));
        dolus.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        dolus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    dolus.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        dolusL = new JLabel();
        dolusL.setBounds(
                (int) (widthUnit * 3.6),
                (int) (heightUnit * 15.5),
                (int) (widthUnit * 1.6),
                heightUnit
        );
        dolusL.setText("dolus");
        dolusL.setForeground(Color.WHITE);
        dolusL.setOpaque(false);
        dolusL.setFont(new Font(null,Font.BOLD ,15));
        dolusL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        dolusL.setHorizontalAlignment(JLabel.CENTER);
        dolusL.setVerticalAlignment(JLabel.CENTER);
        this.add(dolusL);
    }

    private void initEmpusa() {
        empusa.setBounds(
                (int) (widthUnit * 3.6),
                (int) (heightUnit * 6.5),
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        empusa.setFont(new Font(null,Font.BOLD ,15));
        empusa.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        empusa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    empusa.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        empusaL = new JLabel();
        empusaL.setBounds(
                (int) (widthUnit * 3.6),
                heightUnit * 10,
                (int) (widthUnit * 1.6),
                heightUnit
        );
        empusaL.setText("empusa");
        empusaL.setForeground(Color.WHITE);
        empusaL.setOpaque(false);
        empusaL.setFont(new Font(null,Font.BOLD ,15));
        empusaL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        empusaL.setHorizontalAlignment(JLabel.CENTER);
        empusaL.setVerticalAlignment(JLabel.CENTER);
        this.add(empusaL);
    }

    private void initChiron() {
        chiron.setBounds(
                widthUnit,
                heightUnit * 12,
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        chiron.setFont(new Font(null,Font.BOLD ,15));
        chiron.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        chiron.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    chiron.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        chironL = new JLabel();
        chironL.setBounds(
                widthUnit,
                (int)(heightUnit * 15.5),
                (int) (widthUnit * 1.6),
                heightUnit
        );
        chironL.setText("chiron");
        chironL.setForeground(Color.WHITE);
        chironL.setOpaque(false);
        chironL.setFont(new Font(null,Font.BOLD ,15));
        chironL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        chironL.setHorizontalAlignment(JLabel.CENTER);
        chironL.setVerticalAlignment(JLabel.CENTER);
        this.add(chironL);
    }

    private void initMelampus() {
        melampus.setBounds(
                widthUnit,
                (int) (heightUnit * 6.5),
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        melampus.setFont(new Font(null,Font.BOLD ,15));
        melampus.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        melampus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    melampus.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        melampusL = new JLabel();
        melampusL.setBounds(
                widthUnit,
                heightUnit * 10,
                (int) (widthUnit * 1.6),
                heightUnit
        );
        melampusL.setText("melampus");
        melampusL.setForeground(Color.WHITE);
        melampusL.setOpaque(false);
        melampusL.setFont(new Font(null,Font.BOLD ,15));
        melampusL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        melampusL.setHorizontalAlignment(JLabel.CENTER);
        melampusL.setVerticalAlignment(JLabel.CENTER);
        this.add(melampusL);
    }


    private void initContainers() {
        ares = new JPanel();
        aceso = new JPanel();
        proteus = new JPanel();
        astrape = new JPanel();
        chiron = new JPanel();
        dolus = new JPanel();
        empusa = new JPanel();
        melampus = new JPanel();
        cerberus = new JPanel();
        athena = new JPanel();
        ares.setOpaque(false);
        aceso.setOpaque(false);
        proteus.setOpaque(false);
        astrape.setOpaque(false);
        chiron.setOpaque(false);
        dolus.setOpaque(false);
        empusa.setOpaque(false);
        melampus.setOpaque(false);
        cerberus.setOpaque(false);
        athena.setOpaque(false);
        this.add(ares);
        this.add(aceso);
        this.add(proteus);
        this.add(astrape);
        this.add(chiron);
        this.add(dolus);
        this.add(empusa);
        this.add(melampus);
        this.add(cerberus);
        this.add(athena);
    }

    private void initAres() {
        ares.setBounds(
                (int) (widthUnit * 6.75),
                heightUnit,
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        ares.setFont(new Font(null,Font.BOLD ,15));
        ares.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        ares.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    ares.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        aresL = new JLabel();
        aresL.setBounds(
                (int) (widthUnit * 6.75),
                (int) (heightUnit * 4.5),
                (int) (widthUnit * 1.6),
                heightUnit
        );
        aresL.setForeground(Color.WHITE);
        aresL.setOpaque(false);
        aresL.setText("ares");
        aresL.setFont(new Font(null,Font.BOLD ,15));
        aresL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        aresL.setHorizontalAlignment(JLabel.CENTER);
        aresL.setVerticalAlignment(JLabel.CENTER);
        this.add(aresL);
    }

    private void initAceso() {
        aceso.setBounds(
                widthUnit,
                heightUnit,
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        aceso.setFont(new Font(null,Font.BOLD ,15));
        aceso.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        aceso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if (GameState.xp >= 500) {
//                    GameState.xp -= 500;
//                    Controller.updateConfigs();
//                    Aceso.isAvailable ++;
//                    xp.setText("XP :" +(int) GameState.xp);
//                }
            }
        });
        acesoL = new JLabel();
        acesoL.setBounds(
                widthUnit,
                (int)(heightUnit * 4.5),
                (int) (widthUnit * 1.6),
                heightUnit
        );
        acesoL.setForeground(Color.WHITE);
        acesoL.setOpaque(false);
        acesoL.setText("aceso");
        acesoL.setFont(new Font(null,Font.BOLD ,15));
        acesoL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        acesoL.setHorizontalAlignment(JLabel.CENTER);
        acesoL.setVerticalAlignment(JLabel.CENTER);
        this.add(acesoL);
    }
    private void initProteus() {
        proteus.setBounds(
                (int) (widthUnit * 3.6),
                heightUnit,
                (int) (widthUnit * 1.6),
                heightUnit * 3
        );
        proteus.setFont(new Font(null,Font.BOLD ,15));
        proteus.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        proteus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.skillTreeBuyRequest(SkillTreeAbilityType.proteus);
            }
        });
        proteusL = new JLabel();
        proteusL.setBounds(
                (int) (widthUnit * 3.6),
                (int)(heightUnit * 4.5),
                (int) (widthUnit * 1.6),
                heightUnit
        );
        proteusL.setText("proteus");
        proteusL.setForeground(Color.WHITE);
        proteusL.setOpaque(false);
        proteusL.setFont(new Font(null,Font.BOLD ,15));
        proteusL.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        proteusL.setHorizontalAlignment(JLabel.CENTER);
        proteusL.setVerticalAlignment(JLabel.CENTER);
        this.add(proteusL);
    }

    private void initBack() {
        back = new JButton();
        back.setBounds(
                getWidth() / 3,
                (int) (heightUnit * 17.5),
                getWidth() / 3,
                heightUnit
        );
        back.setText("back");
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
        g.drawImage(Constants.aceso ,aceso.getX() ,aceso.getY() ,aceso.getWidth() ,aceso.getHeight() ,null);
        g.drawImage(Constants.aceso ,melampus.getX() ,melampus.getY() ,melampus.getWidth() ,melampus.getHeight() ,null);
        g.drawImage(Constants.aceso ,chiron.getX() ,chiron.getY() ,chiron.getWidth() ,chiron.getHeight() ,null);
        g.drawImage(Constants.aceso ,proteus.getX() ,proteus.getY() ,proteus.getWidth() ,proteus.getHeight() ,null);
        g.drawImage(Constants.aceso ,empusa.getX() ,empusa.getY() ,empusa.getWidth() ,empusa.getHeight() ,null);
        g.drawImage(Constants.aceso ,dolus.getX() ,dolus.getY() ,dolus.getWidth() ,dolus.getHeight() ,null);
        g.drawImage(Constants.aceso ,athena.getX() ,athena.getY() ,athena.getWidth() ,athena.getHeight() ,null);
        g.drawImage(Constants.aceso ,ares.getX() ,ares.getY() ,ares.getWidth() ,ares.getHeight() ,null);
        g.drawImage(Constants.aceso ,astrape.getX() ,astrape.getY() ,astrape.getWidth() ,astrape.getHeight() ,null);
        g.drawImage(Constants.aceso ,cerberus.getX() ,cerberus.getY() ,cerberus.getWidth() ,cerberus.getHeight() ,null);
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
