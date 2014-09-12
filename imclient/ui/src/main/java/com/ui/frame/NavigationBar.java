package com.ui.frame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/11.
 */
public abstract class NavigationBar extends JPanel {
    protected ImageIcon bg;
    public NavigationBar() {
        setLayout(new BorderLayout());
        setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg.getImage(),0,0,this.getWidth(),bg.getIconHeight(),this);
    }
}
