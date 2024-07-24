package view.objectViews.panels;

import controller.Controller;
import controller.enums.InGameAbilityType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyPanel extends JPanel {

    public MyPanel(Point position, Dimension size ,JPanel panel) {
        super();
        setOpaque(false);
        setBounds(
                position.x,
                position.y,
                size.width,
                size.height
        );
        setFont(new Font(null,Font.BOLD ,15));
        setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setFont(new Font(null,Font.BOLD ,15));
                setBorder(BorderFactory.createLineBorder(Color.RED,2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setFont(new Font(null,Font.BOLD ,15));
                setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
            }
        });
        panel.add(this);
    }

}
