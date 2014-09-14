package com.dtclient.lanuch;

import com.dtclient.manager.LoginManager;
import com.dtclient.sys.SysProperties;

import javax.swing.*;

/**
 * Created by HK on 2014/9/9.
 */
public class StartDtClient {


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    //接收参数
                    SysProperties.setHost("192.168.1.105");
                    SysProperties.setPort(5222);
                    SysProperties.setUser("test");
                    try {
                        LoginManager.getInstance().login("test","1");
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }
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
