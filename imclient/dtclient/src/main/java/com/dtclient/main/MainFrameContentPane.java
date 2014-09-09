package com.dtclient.main;

import com.dtclient.button.CustomButtonFactory;
import com.dtclient.main.presence.PresenceSelectButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by HK on 2014/9/9.
 */
public class MainFrameContentPane extends JPanel {

    private String skinPath = "res/main/";
    private ImageIcon bgIcon;
    private JButton tabLeftBtn;
    private JButton tabRightBtn;
    public MainFrameContentPane() {
        bgIcon = new ImageIcon(skinPath+"/bg/mainframewindow.png");
        setLayout(null);
        addPresenceSelectBtn();
        addTabLeftBtn();
        addTabRightBtn();
    }


    /**
     * Ìí¼Ó×´Ì¬Ñ¡Ôñ°´Å¥
     */
    private void addPresenceSelectBtn(){
        PresenceSelectButton presenceSelectButton  = new PresenceSelectButton();
        presenceSelectButton.setLocation(10,10);
        add(presenceSelectButton);
    }

    /**
     * Ìí¼Ó×ó±ßTab°´Å¥
     */
    private void addTabLeftBtn(){
        tabLeftBtn = CustomButtonFactory.createTabLeftBtn();
        tabLeftBtn.setSelectedIcon(new ImageIcon(skinPath+"tab/TabLeftSelected.png"));
        tabLeftBtn.setLocation(5,80);
        add(tabLeftBtn);
        tabLeftBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabLeftBtn.setSelected(true);
                tabRightBtn.setSelected(false);
            }
        });
    }

    /**
     * Ìí¼Ó×ó±ßTab°´Å¥
     */
    private void addTabRightBtn(){
        tabRightBtn = CustomButtonFactory.createTabRightBtn();
        tabRightBtn.setSelectedIcon(new ImageIcon(skinPath+"tab/TabRightSelected.png"));
        tabRightBtn.setLocation(135,80);
        tabRightBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabLeftBtn.setSelected(false);
                tabRightBtn.setSelected(true);
            }
        });
        add(tabRightBtn);
    }


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Image image = bgIcon.getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
