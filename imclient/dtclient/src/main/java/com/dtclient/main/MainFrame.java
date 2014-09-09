package com.dtclient.main;

import javax.swing.*;

/**
 * Created by HK on 2014/9/9.
 */
public class MainFrame extends JFrame{

    private static MainFrame mainFrame;
    private MainFrameContentPane mainFrameContentPane;
    public static MainFrame getInstance() {
        if (mainFrame == null)mainFrame = new MainFrame();
        return mainFrame;
    }

    private MainFrame(){
        mainFrameContentPane = new MainFrameContentPane();
        setContentPane(mainFrameContentPane);
        setSize(278,753);
        setResizable(false);
    }
}
