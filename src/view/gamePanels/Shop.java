package view.gamePanels;


import constants.ImageConstants;
import constants.SizeConstants;
import controller.Controller;
import controller.configs.Configs;
import controller.enums.InGameAbilityType;
import controller.manager.GameState;
import utils.Helper;
import view.ViewData;
import view.menuPanels.PIG;
import view.objectViews.panels.MyButton;
import view.objectViews.panels.MyLabel;
import view.objectViews.panels.MyPanel;

import java.awt.*;
import java.awt.event.*;

public class Shop extends PIG {
    private ShopFrame shopFrame;
    private MyButton back;
    private MyPanel banish;
    private MyPanel empower;
    private MyPanel heal;
    private MyPanel dismay;
    private MyPanel slumber;
    private MyPanel slaughter;
    private MyLabel xp;
    private MyLabel healL;
    private MyLabel banishL;
    private MyLabel empowerL;
    private MyLabel dismayL;
    private MyLabel slumberL;
    private MyLabel slaughterL;

    public Shop(ShopFrame shopFrame){
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
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
        this.shopFrame.add(this);
        this.grabFocus();
        this.setFocusable(true);
        initBack();
        this.setVisible(true);
    }

    private void initSlaughter() {
        slaughter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.inGameAbilityRequest(InGameAbilityType.slaughter);
                updateXP();
            }
        });
    }

    private void initSlumber() {
        slumber.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.inGameAbilityRequest(InGameAbilityType.slumber);
                updateXP();
            }
        });
    }

    private void initDismay() {
        dismay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.inGameAbilityRequest(InGameAbilityType.dismay);
                updateXP();
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
        slaughterL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 7 , SizeConstants.GAME_HEIGHT / 16 * 12),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "slaughter",
                this
        );
    }

    private void initSlumberL() {
        slumberL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 4 , SizeConstants.GAME_HEIGHT / 16 * 12),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "slumber",
                this
        );
    }

    private void initDismayL() {
        dismayL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 , SizeConstants.GAME_HEIGHT / 16 * 12),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "dismay",
                this
        );
    }

    private void initXPL() {
        xp = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 4 , SizeConstants.GAME_HEIGHT / 16),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "XP :" + GameState.getXp(),
                this
        );
    }

    private void initHealL() {
        healL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 ,(int) (SizeConstants.GAME_HEIGHT / 16 * 6.5)),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "heal",
                this
        );
    }

    private void initBanishL() {
        banishL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 4 ,(int) (SizeConstants.GAME_HEIGHT / 16 * 6.5)),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "empower",
                this
        );
    }

    private void initEmpowerL() {
        empowerL = new MyLabel(
                new Point(SizeConstants.GAME_WIDTH / 10 * 7 ,(int) (SizeConstants.GAME_HEIGHT / 16 * 6.5)),
                new Dimension(SizeConstants.GAME_WIDTH / 10 * 2 , SizeConstants.GAME_HEIGHT / 16),
                "banish",
                this
        );
    }

    private void initContainers() {
        banish = new MyPanel(
                new Point(getWidth() / 10 * 7 ,getHeight() / 16 * 3),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        empower = new MyPanel(
                new Point(getWidth() / 10 * 4 ,getHeight() / 16 * 3),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        heal = new MyPanel(
                new Point(getWidth() / 10 ,getHeight() / 16 * 3),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        dismay = new MyPanel(
                new Point(getWidth() / 10 ,(int)(getHeight() / 16 * 8.5)),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        slumber = new MyPanel(
                new Point(getWidth() / 10 * 4 ,(int)(getHeight() / 16 * 8.5)),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
        slaughter = new MyPanel(
                new Point(getWidth() / 10 * 7 ,(int)(getHeight() / 16 * 8.5)),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16 * 3),
                this
        );
    }

    private void initHeal() {
        heal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.inGameAbilityRequest(InGameAbilityType.heal);
                updateXP();
            }
        });
    }

    private void initEmpower() {
        empower.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.inGameAbilityRequest(InGameAbilityType.empower);
                updateXP();
            }
        });
    }

    private void initBanish() {
        banish.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller.inGameAbilityRequest(InGameAbilityType.banish);
                updateXP();
            }
        });
    }

    void initBack(){
        back = new MyButton(
                new Point(getWidth() / 10 * 4 ,getHeight() / 16 * 14),
                new Dimension(getWidth() / 10 * 2 ,getHeight() / 16),
                "Back",
                this
        );

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
        g.drawImage(ImageConstants.heal ,heal.getX() ,heal.getY() ,heal.getWidth() ,heal.getHeight() ,null);
        g.drawImage(ImageConstants.empower ,empower.getX() ,empower.getY() ,empower.getWidth() ,empower.getHeight() ,null);
        g.drawImage(ImageConstants.banish ,banish.getX() ,banish.getY() ,banish.getWidth() ,banish.getHeight() ,null);
        g.drawImage(ImageConstants.slumber ,slumber.getX() ,slumber.getY() ,slumber.getWidth() ,slumber.getHeight() ,null);
        g.drawImage(ImageConstants.slaughter ,slaughter.getX() ,slaughter.getY() ,slaughter.getWidth() ,slaughter.getHeight() ,null);
        g.drawImage(ImageConstants.dismay ,dismay.getX() ,dismay.getY() ,dismay.getWidth() ,dismay.getHeight() ,null);
    }

    @Override
    public void start() {
        setVisible(true);
    }

    private void updateXP() {
        Helper.saveXP(GameState.getXp());
        xp.setText("xp: " + Configs.GameConfigs.XP);
    }

    @Override
    public void end() {
        Controller.resume();
        shopFrame.dispose();
    }
}
