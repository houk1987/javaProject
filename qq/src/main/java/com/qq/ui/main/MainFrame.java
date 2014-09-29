package com.qq.ui.main;

import com.qq.ui.pub.PubFrame;

import java.awt.*;

/**
 * Created by lenovo on 2014/9/17.
 */
public class MainFrame extends PubFrame {
    private static MainFrame mainFrame;
    private MainPane mainPane;

    public static MainFrame getInstance() {
        if(mainFrame == null){
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }


    private MainFrame() throws HeadlessException {
        mainPane = new MainPane();
        setContentPane(mainPane);    //设置内容面板
        setSize(mainPane.getSize());//大小为内容面板大小
        setLocationRelativeTo(null); //居右显示
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
