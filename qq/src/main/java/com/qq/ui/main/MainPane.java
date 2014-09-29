package com.qq.ui.main;

import com.qq.ui.tree.QQContactTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/17.
 */
public class MainPane extends JPanel {

    private ImageIcon bgIcon;  //±≥æ∞Õº∆¨
    private QQContactTree qqContactTree;

    public MainPane() {
        bgIcon = new ImageIcon("res/main/mainFrameBg.png"); //≥ı ºªØ±≥æ∞Õº∆¨
        if (bgIcon == null) return;
        setSize(bgIcon.getIconWidth(), bgIcon.getIconHeight()); //…Ë÷√Œ™±≥æ∞Õº∆¨¥Û–°
        initLayout();
    }

    private void initLayout(){
        setLayout(null);
        qqContactTree = new QQContactTree();
        qqContactTree.setSize(452, 440);
        qqContactTree.setLocation(0, 200);
        add(qqContactTree);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgIcon == null) return;
        Image icon = bgIcon.getImage();
        g.drawImage(icon, 0, 0, icon.getWidth(this), icon.getHeight(this), this);
    }
}
