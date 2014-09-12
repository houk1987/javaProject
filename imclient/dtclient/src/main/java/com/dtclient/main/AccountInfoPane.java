package com.dtclient.main;

import com.dtclient.lanuch.DtClient;
import com.dtclient.main.presence.PresenceSelectButton;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/11.
 */
public class AccountInfoPane extends JPanel {
    PresenceSelectButton presenceSelectButton =  new PresenceSelectButton();
    JLabel accountName = new JLabel(DtClient.getInstance().getLoginAccount());
    public AccountInfoPane() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        JLabel head = JLabelFactory.createJLabel(new ImageIcon("res/main/head.png"));
        head.setLocation(15,5);
        head.setSize(40, 40);
        panel.add(head);
        presenceSelectButton.setLocation(20, 5);
        presenceSelectButton.setSize(30,30);
        panel.add(presenceSelectButton);
        accountName.setBounds(25,5,300,24);
        panel.add(accountName);
        add(panel, BorderLayout.WEST);
        this.setOpaque(false);
    }
}
