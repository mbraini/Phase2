package controller.listeners;

import controller.Controller;
import controller.configs.KeyConfigs;
import controller.enums.SkillTreeAbilityType;
import model.GameState;
import view.gamePanels.Shop;
import view.gamePanels.ShopFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelKeyListener extends KeyAdapter {

    public static char SHOP_KEY = 'p';
    public static char ARES_KEY = 'q';
    public static char ASTRAPE_KEY = 'a';
    public static char CERBERUS_KEY = 'z';
    public static char ACESO_KEY = 'w';
    public static char MELAMPUS_KEY = 's';
    public static char CHIRON_KEY = 'x';
    public static char PROTEUS_KEY = 'e';
    public static char EMPUSA_KEY = 'd';
    public static char DOLUS_KEY = 'c';

    public PanelKeyListener(){
        SHOP_KEY = KeyConfigs.SHOP_KEY;
        ARES_KEY = KeyConfigs.ARES_KEY;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == SHOP_KEY && !GameState.isPause()){
            Controller.pause();
            new Shop(new ShopFrame());
        }
        if (e.getKeyChar() == ARES_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.ares);
        }
        if (e.getKeyChar() == ASTRAPE_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.astrape);
        }
        if (e.getKeyChar() == CERBERUS_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.cerberus);
        }
        if (e.getKeyChar() == ACESO_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.aceso);
        }
        if (e.getKeyChar() == MELAMPUS_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.melampus);
        }
        if (e.getKeyChar() == CHIRON_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.chiron);
        }
        if (e.getKeyChar() == PROTEUS_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.proteus);
        }
        if (e.getKeyChar() == EMPUSA_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.empusa);
        }
        if (e.getKeyChar() == DOLUS_KEY) {
            Controller.skillTreeAbilityRequest(SkillTreeAbilityType.dolus);
        }
    }
}
