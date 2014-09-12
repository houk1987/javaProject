package com.dtclient.main;

import com.dtclient.main.tree.CustomTree;
import com.dtclient.sys.SysProperties;
import com.pubTools.properties.PropertiesTools;
import com.san30.sim.pub.imagewindow.ImageFrame;
import com.ui.frame.PubFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/9/9.
 */
public class MainFrame extends PubFrame{
    private static MainFrame mainFrame;
    private MainFrameContentPane mainFrameContentPane;
    public static MainFrame getInstance() {
        if (mainFrame == null)mainFrame = new MainFrame();
        return mainFrame;
    }

    private MainFrame(){
        super(new MainFrameTitle());
        setImagePath(SysProperties.framePath());
        mainFrameContentPane = new MainFrameContentPane();
        add(mainFrameContentPane, BorderLayout.CENTER);
        setSize(278,753);
    }


    public CustomTree getCustomTree(){
        return mainFrameContentPane.getCustomTree();
    }

}
