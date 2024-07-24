package view.menuPanels;


import constants.ImageConstants;
import constants.SizeConstants;
import controller.ViewRequestController;
import controller.configs.Configs;
import controller.enums.SkillTreeAbilityType;
import controller.manager.GameState;
import utils.Helper;
import view.objectViews.panels.MyButton;
import view.objectViews.panels.MyLabel;
import view.objectViews.panels.MyPanel;

import java.awt.*;
import java.awt.event.*;

public class SkillTreePanel extends PIG {
    private MyButton back;
    private MyPanel ares;
    private MyPanel aceso;
    private MyPanel proteus;
    private MyPanel astrape;
    private MyPanel chiron;
    private MyPanel dolus;
    private MyPanel empusa;
    private MyPanel melampus;
    private MyPanel cerberus;
    private MyPanel athena;
    private MyLabel aresL;
    private MyLabel acesoL;
    private MyLabel proteusL;
    private MyLabel astrapeL;
    private MyLabel chironL;
    private MyLabel dolusL;
    private MyLabel empusaL;
    private MyLabel melampusL;
    private MyLabel cerberusL;
    private MyLabel athenaL;
    private MyLabel xp;
    private final int widthUnit;
    private final int heightUnit;

    public SkillTreePanel(){
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
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
        initXP();
        initBack();
        initAL();
    }

    private void initXP() {
        xp = new MyLabel(
                new Point(
                        (int) (widthUnit * 8.9),
                        0
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "xp: " + Configs.GameConfigs.XP,
                this
        );
    }

    private void initAthena() {
        athena.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.athena);
                updateXP();
            }
        });
        athenaL = new MyLabel(
                new Point(
                        (int) (widthUnit * 7.8),
                        (int) (heightUnit * 15.5)
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "athena",
                this
        );
    }

    private void initCerberus() {
        cerberus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.cerberus);
                updateXP();
            }
        });
        cerberusL = new MyLabel(
                new Point(
                        widthUnit,
                        (int)(heightUnit * 15.5)
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "cerberus",
                this
        );
    }

    private void updateXP() {
        Helper.saveXP(GameState.getXp());
        xp.setText("xp: " + Configs.GameConfigs.XP);
    }

    private void initAstrape() {
        astrape.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.astrape);
                updateXP();
            }
        });
        astrapeL = new MyLabel(
                new Point(
                        widthUnit,
                        heightUnit * 10
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "astrape",
                this
        );
    }

    private void initDolus() {
        dolus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.dolus);
                updateXP();
            }
        });
        dolusL = new MyLabel(
                new Point(
                        (int) (widthUnit * 3.6),
                        (int) (heightUnit * 15.5)
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "dolus",
                this
        );
    }

    private void initEmpusa() {
        empusa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.empusa);
                updateXP();
            }
        });
        empusaL = new MyLabel(
                new Point(
                        (int) (widthUnit * 3.6),
                        heightUnit * 10
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "empusa",
                this
        );
    }

    private void initChiron() {
        chiron.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.chiron);
                updateXP();
            }
        });
        chironL = new MyLabel(
                new Point(
                        (int) (widthUnit * 5.7),
                        (int) (heightUnit * 15.5)
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "chiron",
                this
        );
    }

    private void initMelampus() {
        melampus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.melampus);
                updateXP();
            }
        });
        melampusL = new MyLabel(
                new Point(
                        (int) (widthUnit * 6.75),
                        heightUnit * 10
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "melampus",
                this
        );
    }


    private void initContainers() {
        ares = new MyPanel(
                new Point(widthUnit, heightUnit),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        aceso = new MyPanel(
                new Point((int) (widthUnit * 6.75), heightUnit),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        proteus = new MyPanel(
                new Point((int) (widthUnit * 3.6), heightUnit),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        astrape = new MyPanel(
                new Point(widthUnit, (int) (heightUnit * 6.5)),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        chiron = new MyPanel(
                new Point((int) (widthUnit * 5.7), heightUnit * 12),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        dolus = new MyPanel(
                new Point((int) (widthUnit * 3.6), heightUnit * 12),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        empusa = new MyPanel(
                new Point((int) (widthUnit * 3.6), (int) (heightUnit * 6.5)),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        melampus = new MyPanel(
                new Point((int) (widthUnit * 6.75), (int) (heightUnit * 6.5)),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        cerberus = new MyPanel(
                new Point(widthUnit, heightUnit * 12),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
        athena = new MyPanel(
                new Point((int) (widthUnit * 7.8), heightUnit * 12),
                new Dimension((int) (widthUnit * 1.6), heightUnit * 3),
                this
        );
    }

    private void initAres() {
        ares.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.ares);
                updateXP();
            }
        });
        aresL = new MyLabel(
                new Point(
                        widthUnit,
                        (int)(heightUnit * 4.5)
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "ares",
                this
        );
    }

    private void initAceso() {
        aceso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.aceso);
                updateXP();
            }
        });
        acesoL = new MyLabel(
                new Point(
                        (int) (widthUnit * 6.75),
                        (int) (heightUnit * 4.5)
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "aceso",
                this
        );
    }
    private void initProteus() {
        proteus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewRequestController.buySkillTreeRequest(SkillTreeAbilityType.proteus);
                updateXP();
            }
        });
        proteusL = new MyLabel(
                new Point(
                        (int) (widthUnit * 3.6),
                        (int)(heightUnit * 4.5)
                ),
                new Dimension(
                        (int) (widthUnit * 1.6),
                        heightUnit
                ),
                "proteus",
                this
        );
    }

    private void initBack() {
        back = new MyButton(
                new Point(getWidth() / 3, (int) (heightUnit * 17.5)),
                new Dimension(getWidth() / 3, heightUnit),
                "back",
                this
        );
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
        g.drawImage(ImageConstants.aceso ,aceso.getX() ,aceso.getY() ,aceso.getWidth() ,aceso.getHeight() ,null);
        g.drawImage(ImageConstants.melampus ,melampus.getX() ,melampus.getY() ,melampus.getWidth() ,melampus.getHeight() ,null);
        g.drawImage(ImageConstants.chiron ,chiron.getX() ,chiron.getY() ,chiron.getWidth() ,chiron.getHeight() ,null);
        g.drawImage(ImageConstants.proteus ,proteus.getX() ,proteus.getY() ,proteus.getWidth() ,proteus.getHeight() ,null);
        g.drawImage(ImageConstants.empusa ,empusa.getX() ,empusa.getY() ,empusa.getWidth() ,empusa.getHeight() ,null);
        g.drawImage(ImageConstants.dolus ,dolus.getX() ,dolus.getY() ,dolus.getWidth() ,dolus.getHeight() ,null);
        g.drawImage(ImageConstants.athena ,athena.getX() ,athena.getY() ,athena.getWidth() ,athena.getHeight() ,null);
        g.drawImage(ImageConstants.ares ,ares.getX() ,ares.getY() ,ares.getWidth() ,ares.getHeight() ,null);
        g.drawImage(ImageConstants.astrape ,astrape.getX() ,astrape.getY() ,astrape.getWidth() ,astrape.getHeight() ,null);
        g.drawImage(ImageConstants.cerberus ,cerberus.getX() ,cerberus.getY() ,cerberus.getWidth() ,cerberus.getHeight() ,null);
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void end() {
        this.setVisible(false);
    }
}
