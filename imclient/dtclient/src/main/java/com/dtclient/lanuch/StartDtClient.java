package com.dtclient.lanuch;

import com.dtclient.main.MainFrame;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;

/**
 * Created by HK on 2014/9/9.
 */
public class StartDtClient {

    public static String host = "192.168.1.108";
    public static int port = 5222;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    validateId("3","1");
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

    private static void validateId(String account, String pwd) {
        try {
            DtClient.getInstance().loginClient(account,pwd);
        } catch (XMPPException e) {
            JOptionPane.showMessageDialog(null,"Æô¶¯¿Í»§¶ËÊ§°Ü!");
            e.printStackTrace();
        }
    }
}
