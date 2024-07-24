package view.gamePanels;

import constants.SizeConstants;
import controller.listeners.PanelKeyListener;
import view.ViewData;
import view.abilities.AbilityView;
import view.objectViews.FrameView;
import view.objectViews.ObjectView;
import view.objectViews.effectView.EffectView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImaginaryPanel extends JPanel {
    private ArrayList<ObjectView> epsilons = new ArrayList<>();
    private ArrayList<ObjectView> otherViews = new ArrayList<>();
    private ArrayList<ObjectView> archmires = new ArrayList<>();
    private ArrayList<ObjectView> cerberuses = new ArrayList<>();
    private FrameView epsilonFrame;
    private ArrayList<EffectView> effectViews = new ArrayList<>();
    private ArrayList<AbilityView> abilityViews = new ArrayList<>();
    private String id;
    public ImaginaryPanel(String id){
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBounds(
                -SizeConstants.SCREEN_SIZE.width ,
                -SizeConstants.SCREEN_SIZE.height ,
                SizeConstants.SCREEN_SIZE.width * 3 ,
                SizeConstants.SCREEN_SIZE.height * 3
        );
        initKeyListener();
        setVisible(true);
    }

    private void initKeyListener() {
        this.addKeyListener(new PanelKeyListener());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (effectViews == null)
            return;

        Graphics2D g2d = (Graphics2D) g;
        if (epsilonFrame != null) {
            paintInfos(g);
        }
        for (AbilityView abilityView : abilityViews) {
            abilityView.setUp();
            abilityView.draw(g2d);
        }
        for (int i = 0; i < effectViews.size() ;i++){
            if (effectViews.get(i) == null) {
                System.out.println("NULL IN PAINTER :(");
            }
            effectViews.get(i).setEffect();
            effectViews.get(i).draw(g2d);
        }

        for (int i = 0 ;i < cerberuses.size() ;i++) {
            cerberuses.get(i).draw(g2d);
        }

        for (int i = 0 ;i < archmires.size() ;i++) {
            archmires.get(i).draw(g2d);
        }

        for (int i = 0 ;i < otherViews.size() ;i++){
            otherViews.get(i).draw(g2d);
        }

        for (int i = 0 ;i < epsilons.size() ;i++) {
            epsilons.get(i).draw(g2d);
        }

    }

    private void paintInfos(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.setFont(new Font(null, Font.BOLD, 10));
        g.drawString("xp: " + (int) ViewData.getXp(),
                3 + epsilonFrame.getX() + SizeConstants.SCREEN_SIZE.width,
                20 + epsilonFrame.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g.setColor(Color.GREEN);
        g.drawString("hp: " + (int) ViewData.getHp(),
                58 + epsilonFrame.getX() + SizeConstants.SCREEN_SIZE.width,
                20 + epsilonFrame.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g.setColor(Color.RED);
        g.drawString("wave: " + ViewData.getWave(),
                103 + epsilonFrame.getX() + SizeConstants.SCREEN_SIZE.width,
                20 + epsilonFrame.getY() + SizeConstants.SCREEN_SIZE.height
        );
        g.setColor(Color.WHITE);
        g.drawString("time: " + (int) ViewData.getTime() / 1000,
                151 + epsilonFrame.getX() + SizeConstants.SCREEN_SIZE.width,
                20 + epsilonFrame.getY() + SizeConstants.SCREEN_SIZE.height
        );
    }

    public void setOtherViews(ArrayList<ObjectView> otherViews) {
        this.otherViews = otherViews;
    }

    public void setVariables() {
        FrameView epsilonFrame = ViewData.getEpsilonFrame();
        this.epsilonFrame = epsilonFrame;
    }

    public void setEffects(ArrayList<EffectView> effectViews){
        this.effectViews = effectViews;
    }

    public void setAbilityViews(ArrayList<AbilityView> abilityViews) {
        this.abilityViews = abilityViews;
    }

    public void setEpsilons(ArrayList<ObjectView> epsilons) {
        this.epsilons = epsilons;
    }

    public void setArchmires(ArrayList<ObjectView> archmires) {
        this.archmires = archmires;
    }

    public void setCerberuses(ArrayList<ObjectView> cerberuses) {
        this.cerberuses = cerberuses;
    }
}
