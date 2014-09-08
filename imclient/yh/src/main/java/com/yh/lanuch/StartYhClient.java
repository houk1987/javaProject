package com.yh.lanuch;

import com.yh.login.LoginFrame;

import javax.swing.*;

/**
 * Created by 356 on 14-8-30.
 */
public class StartYhClient {

    public static void main(String[] args) {

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                        LoginFrame loginFrame = new LoginFrame();
                        loginFrame.setVisible(true);
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
            });

    }
}
