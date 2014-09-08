package com.yh.session;

import javax.swing.*;
import java.awt.*;

/**
 * Created by a on 2014/8/20.
 */
public class MenuPane extends JPanel {

    private String [] menuName;

    public MenuPane(String[] menuName) {
        this.menuName = menuName;
        layoutComponent();
    }

    private void layoutComponent(){
        setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
        setOpaque(false);
        if(this.menuName!=null && this.menuName.length>0){
            for(int i=0;i<this.menuName.length;i++){
               JLabel menuNameLabel = new JLabel(this.menuName[i]);
               menuNameLabel.setForeground(Color.WHITE);
               add(menuNameLabel);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Label.font", new Font("Dialog", Font.PLAIN, 12));
            System.setProperty("java.awt.im.style", "over-the-spot");
            JFrame jFrame = new JFrame();
            String [] menuName = {"訊息(C)","編輯(E)","檢視(V)","功能(A)","說明(H)"};
            jFrame.setContentPane(new MenuPane(menuName));
            jFrame.pack();
            jFrame.setVisible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }
}
