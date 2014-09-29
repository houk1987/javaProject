package com.dt.ui.main;


import com.dt.ui.main.MainPane;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lenovo on 2014/9/11.
 */
public class DtNavigationBar extends JPanel{

    private JLabel orgLabel; //组织机构按钮 （导演才能看到）
    private JLabel firstLabel;
    private ImageIcon bg;

    public DtNavigationBar(final MainPane mainPane,int role) {
        bg = new ImageIcon("res/main/navigation/navigationBg.png");
        firstLabel = JLabelFactory.createJLabel(new ImageIcon("res/main/navigation/first.png"));
        orgLabel = JLabelFactory.createJLabel(new ImageIcon("res/main/navigation/first.png"));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(firstLabel);
        if(role == 3){
            add(orgLabel);
        }
        firstLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPane.switchCustomTree();
            }
        });

        orgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPane.switchOrgTree();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = bg.getImage();
        g.drawImage(image,0,0,this.getWidth(),image.getHeight(this),this);
    }
}
