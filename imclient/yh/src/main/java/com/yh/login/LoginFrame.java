package com.yh.login;

import com.yh.manager.LoginManager;
import com.yh.manager.YhManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by a on 2014/9/5.
 */
public class LoginFrame extends JFrame {

    public LoginFrame() throws HeadlessException {
        setResizable(false);
        setSize(475,840);
        setMinimumSize(getSize());
        setContentPane(new LoginPane());
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                LoginPane loginPane = (LoginPane) getContentPane();
                loginPane.setLocation();
            }
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            LoginManager.getInstance().loginOut();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
