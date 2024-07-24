package view.objectViews.panels;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {

    public MyLabel(Point position ,Dimension size ,String text ,JPanel panel) {
        super();
        setBounds(
                position.x,
                position.y,
                size.width,
                size.height
        );
        setForeground(Color.WHITE);
        setOpaque(false);
        setText(text);
        setFont(new Font(null,Font.BOLD ,15));
        setBorder(BorderFactory.createLineBorder(Color.CYAN,2));
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        panel.add(this);
    }

}
