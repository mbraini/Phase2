package view.menuPanels;


import controller.configs.Configs;
import constants.Constants;
import view.objectViews.panels.MyButton;
import view.objectViews.panels.MyLabel;
import view.soundEffects.Sound;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsPanel extends PIG {

    private JSlider soundSlider;
    private JSlider sensitivitySlider;
    private MyLabel soundGuide;
    private MyLabel sensitivityGuide;
    private MyButton back;

    public SettingsPanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSoundSlider();
        initSensitivitySlider();
        initGuides();
        initBack();
        initChangeListeners();
        initAL();
    }

    private void initGuides() {
        soundGuide = new MyLabel(
                new Point(Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 7),
                new Dimension(Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 7),
                "Sound",
                this
        );

        sensitivityGuide = new MyLabel(
                new Point(Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 7 * 3),
                new Dimension(Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 7),
                "Sensitivity",
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

    private void initChangeListeners() {
        soundSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (Configs.ViewConfigs.SOUND == 0){
                    Sound.volumeMute();
                }
                if (soundSlider.getValue() - Configs.ViewConfigs.SOUND > 0)
                    Sound.volumeUp();
                else if (soundSlider.getValue() - Configs.ViewConfigs.SOUND < 0)
                    Sound.volumeDown();
                Configs.ViewConfigs.SOUND = soundSlider.getValue();
                if (Configs.ViewConfigs.SOUND == 0){
                    Sound.volumeMute();
                }
            }
        });
        sensitivitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Configs.SENSITIVITY = sensitivitySlider.getValue();
                if (Configs.SENSITIVITY == 3){
                    Configs.GameConfigs.EPSILON_ACCELERATION = 0.001;
                    Configs.GameConfigs.EPSILON_DECELERATION_TIME = 50;
                }
                else if (Configs.SENSITIVITY == 2){
                    Configs.GameConfigs.EPSILON_ACCELERATION = 0.0006;
                    Configs.GameConfigs.EPSILON_DECELERATION_TIME = 200;
                }
                else if (Configs.SENSITIVITY == 1){
                    Configs.GameConfigs.EPSILON_ACCELERATION = 0.0001;
                    Configs.GameConfigs.EPSILON_DECELERATION_TIME = 500;
                }
            }
        });
    }

    private void initBack() {
        back = new MyButton(
                new Point(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 7 * 5),
                new Dimension(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 7),
                "back",
                this
        );
    }

    private void initSensitivitySlider() {
        sensitivitySlider = new JSlider(1 ,3);
        sensitivitySlider.setBounds(Constants.GAME_WIDTH / 8 * 3 ,Constants.GAME_HEIGHT / 7 * 3 ,Constants.GAME_WIDTH / 8 * 4 ,Constants.GAME_HEIGHT / 7);
        sensitivitySlider.setPaintTicks(true);
        sensitivitySlider.setMinorTickSpacing(1);
        sensitivitySlider.setPaintTrack(true);
        sensitivitySlider.setMajorTickSpacing(1);
        sensitivitySlider.setPaintLabels(true);
        sensitivitySlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));
        sensitivitySlider.setValue(Configs.SENSITIVITY);

        this.add(sensitivitySlider);
    }

    private void initSoundSlider() {
        soundSlider = new JSlider(0 ,10);
        soundSlider.setBounds(Constants.GAME_WIDTH / 8 * 3 ,Constants.GAME_HEIGHT / 7 ,Constants.GAME_WIDTH / 8 * 4 ,Constants.GAME_HEIGHT / 7);
        soundSlider.setPaintTicks(true);
        soundSlider.setMinorTickSpacing(1);
        soundSlider.setPaintTrack(true);
        soundSlider.setMajorTickSpacing(1);
        soundSlider.setPaintLabels(true);
        soundSlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));
        soundSlider.setValue(Configs.ViewConfigs.SOUND);
        this.add(soundSlider);
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
