package view.menuPanels;


import controller.configs.Configs;
import constants.Constants;
import view.soundEffects.Sound;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsPanel extends PIG {

    JSlider soundSlider;
    JSlider difficultySlider;
    JSlider sensitivitySlider;
    JLabel soundGuide;
    JLabel difficultyGuide;
    JLabel sensitivityGuide;
    JButton back;

    public SettingsPanel(){
        this.setLayout(null);
        this.setBounds(0,0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setBackground(Color.BLACK);
        this.setVisible(false);

        initSoundSlider();
        initDifficultySlider();
        initSensitivitySlider();
        initGuides();
        initBack();
        initChangeListeners();
        initAL();
    }

    private void initGuides() {
        soundGuide = new JLabel();
        soundGuide.setBounds(Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 9 ,Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 9);
        soundGuide.setText("Sound");
        soundGuide.setBackground(Color.WHITE);
        soundGuide.setOpaque(true);
        soundGuide.setFont(new Font(null,Font.BOLD ,15));
        soundGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        soundGuide.setHorizontalAlignment(JLabel.CENTER);
        soundGuide.setVerticalAlignment(JLabel.CENTER);
        this.add(soundGuide);

        difficultyGuide = new JLabel();
        difficultyGuide.setBounds(Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 9 * 3 ,Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 9);
        difficultyGuide.setText("Difficulty");
        difficultyGuide.setBackground(Color.WHITE);
        difficultyGuide.setOpaque(true);
        difficultyGuide.setFont(new Font(null,Font.BOLD ,15));
        difficultyGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        difficultyGuide.setHorizontalAlignment(JLabel.CENTER);
        difficultyGuide.setVerticalAlignment(JLabel.CENTER);
        this.add(difficultyGuide);

        sensitivityGuide = new JLabel();
        sensitivityGuide.setBounds(Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 9 * 5 ,Constants.GAME_WIDTH / 8 ,Constants.GAME_HEIGHT / 9);
        sensitivityGuide.setText("Sensitivity");
        sensitivityGuide.setBackground(Color.WHITE);
        sensitivityGuide.setOpaque(true);
        sensitivityGuide.setFont(new Font(null,Font.BOLD ,15));
        sensitivityGuide.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        sensitivityGuide.setHorizontalAlignment(JLabel.CENTER);
        sensitivityGuide.setVerticalAlignment(JLabel.CENTER);
        sensitivitySlider.setValue(3);
        this.add(sensitivityGuide);
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
                if (Configs.SOUND == 0){
                    Sound.volumeMute();
                }
                if (soundSlider.getValue() - Configs.SOUND > 0)
                    Sound.volumeUp();
                else if (soundSlider.getValue() - Configs.SOUND < 0)
                    Sound.volumeDown();
                Configs.SOUND = soundSlider.getValue();
                if (Configs.SOUND == 0){
                    Sound.volumeMute();
                }
            }
        });
        difficultySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Configs.DIFFICULTY = difficultySlider.getValue();
            }
        });
        sensitivitySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Configs.SENSITIVITY = sensitivitySlider.getValue();
                if (Configs.SENSITIVITY == 3){
                    Configs.EPSILON_ACCELERATION = 0.001;
                    Configs.EPSILON_DECELERATION_TIME = 50;
                }
                else if (Configs.SENSITIVITY == 2){
                    Configs.EPSILON_ACCELERATION = 0.0006;
                    Configs.EPSILON_DECELERATION_TIME = 200;
                }
                else if (Configs.SENSITIVITY == 1){
                    Configs.EPSILON_ACCELERATION = 0.0003;
                    Configs.EPSILON_DECELERATION_TIME = 400;
                }
            }
        });
    }

    private void initBack() {
        back = new JButton();
        back.setBounds(Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 9 * 7 ,Constants.GAME_WIDTH / 3 ,Constants.GAME_HEIGHT / 9);
        back.setFont(new Font(null,Font.BOLD ,15));
        back.setText("back");
        back.setBackground(Color.WHITE);
        back.setOpaque(true);
        back.setHorizontalTextPosition(JLabel.RIGHT);
        back.setVerticalTextPosition(JLabel.TOP);
        back.setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        back.setFocusable(false);
        this.add(back);
    }

    private void initSensitivitySlider() {
        sensitivitySlider = new JSlider(1 ,3);
        sensitivitySlider.setBounds(Constants.GAME_WIDTH / 8 * 3 ,Constants.GAME_HEIGHT / 9 * 5 ,Constants.GAME_WIDTH / 8 * 4 ,Constants.GAME_HEIGHT / 9);
        sensitivitySlider.setPaintTicks(true);
        sensitivitySlider.setMinorTickSpacing(1);
        sensitivitySlider.setPaintTrack(true);
        sensitivitySlider.setMajorTickSpacing(1);
        sensitivitySlider.setPaintLabels(true);
        sensitivitySlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));
        sensitivitySlider.setValue(Configs.SENSITIVITY);

        this.add(sensitivitySlider);
    }

    private void initDifficultySlider() {
        difficultySlider = new JSlider(1 ,3);
        difficultySlider.setBounds(Constants.GAME_WIDTH / 8 * 3 ,Constants.GAME_HEIGHT / 9 * 3 ,Constants.GAME_WIDTH / 8 * 4 ,Constants.GAME_HEIGHT / 9);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setMinorTickSpacing(1);
        difficultySlider.setPaintTrack(true);
        difficultySlider.setMajorTickSpacing(1);
        difficultySlider.setPaintLabels(true);
        difficultySlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));
        difficultySlider.setValue(Configs.DIFFICULTY);

        this.add(difficultySlider);
    }

    private void initSoundSlider() {
        soundSlider = new JSlider(0 ,10);
        soundSlider.setBounds(Constants.GAME_WIDTH / 8 * 3 ,Constants.GAME_HEIGHT / 9 ,Constants.GAME_WIDTH / 8 * 4 ,Constants.GAME_HEIGHT / 9);
        soundSlider.setPaintTicks(true);
        soundSlider.setMinorTickSpacing(1);
        soundSlider.setPaintTrack(true);
        soundSlider.setMajorTickSpacing(1);
        soundSlider.setPaintLabels(true);
        soundSlider.setFont(new Font("MV Boli" ,Font.PLAIN ,15));
        soundSlider.setValue(Configs.SOUND);
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
