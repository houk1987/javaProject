package com.yh.main;

import com.yh.lanuch.YhClient;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.awt.*;

/**
 * Created by a on 2014/9/5.
 */
public class MainFrame extends JFrame {

    private static MainFrame mainFrame;
    private static MainPane mainPane;

    public static MainFrame getInstance(){
        if(mainFrame  == null){
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }


    private MainFrame() throws HeadlessException {
        mainPane = new MainPane();
        setContentPane(mainPane);
        setSize(475,840);
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void refreshContactTreePresence(Presence presence){
        mainPane.refreshTreePresence(presence);
    }


    @Override
    public void dispose() {
        super.dispose();
        YhClient.getInstance().closeClient();
    }
}
