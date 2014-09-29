package com.dt.lanuch;

import com.dt.ui.tree.OrgTreeCellRenderer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

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
                    UIManager.put("Tree.font",new OrgTreeCellRenderer());
                    try {
                        DTClient.getInstance().loginClient("3","1");
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
