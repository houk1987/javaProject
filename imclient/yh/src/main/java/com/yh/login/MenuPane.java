package com.yh.login;

import com.ui.font.FontFactory;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;


/**
 * Created by 356 on 14-8-30.
 */
public class MenuPane extends JPanel {

    private final static String SKIN_PATH = "res/menu/";
    private ImageIcon bgImageIcon = new ImageIcon(SKIN_PATH+"menuBg.png");
    public MenuPane(String[] menuNames) {
        if (menuNames == null || menuNames.length == 0) return;
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
        setOpaque(false);
        Font font = FontFactory.createFont("ËÎÌו", 13);
        for (int i = 0; i < menuNames.length; i++) {
            JLabel label = JLabelFactory.createJLabel(menuNames[i], font, Color.WHITE);
            if (i == 0) label.setBorder(new EmptyBorder(0, 0, 0, 10));
            if (i != 0) label.setBorder(new EmptyBorder(0, 0, 0, 10));
            add(label);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        Image image = bgImageIcon.getImage();
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
