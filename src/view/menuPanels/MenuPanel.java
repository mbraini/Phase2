package view.menuPanels;

import constants.SizeConstants;
import controller.Controller;
import view.Application;
import view.objectViews.panels.MyButton;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuPanel extends PIG {
    private MyButton exit;
    private MyButton start;
    private MyButton settings;
    private MyButton tutorial;
    private MyButton skillTree;
    public MenuPanel(){
        this.setLayout(null);
        this.setBounds(0,0, SizeConstants.GAME_WIDTH, SizeConstants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);

        initExit();
        initSettings();
        initStart();
        initSkillTree();
        initTutorial();
        initAL();
    }

    private void initAL() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_WINDOWS);
                    robot.keyPress(KeyEvent.VK_D);
                    robot.keyRelease(KeyEvent.VK_WINDOWS);
                    robot.keyRelease(KeyEvent.VK_D);
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                }
                Application.endMainFrame();
                Controller.startGame();
            }
        });
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.settingsPanel.start();
            }
        });
        skillTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.skillTreePanel.start();
            }
        });

        tutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                end();
                MainFrame.tutorial.start();
            }
        });
    }

    private void initExit(){
        exit = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 11 * 9),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "exit",
                this
        );
    }

    private void initStart(){
        start = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 11),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "start",
                this
        );
    }

    private void initSettings(){
        settings = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 11 * 3),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "settings",
                this
        );
    }

    private void initTutorial(){
        tutorial = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 11 * 7),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "tutorial",
                this
        );
    }

    private void initSkillTree(){
        skillTree = new MyButton(
                new Point(SizeConstants.GAME_WIDTH / 3 , SizeConstants.GAME_HEIGHT / 11 * 5),
                new Dimension(SizeConstants.GAME_WIDTH / 3, SizeConstants.GAME_HEIGHT / 11),
                "skillTree",
                this
        );
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
