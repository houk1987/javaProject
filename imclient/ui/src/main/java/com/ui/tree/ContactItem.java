package com.ui.tree;


import com.imService.contact.Contact;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by a on 2014/8/28.
 */
public class ContactItem extends JPanel {
    private JLabel statusIconLabel;
    private JLabel displayNameLabel;
    private JLabel headIconLabel;

    private ImageIcon statusIcon;
    private Contact yhContact;
    public ContactItem(){
        statusIconLabel = JLabelFactory.createJLabel(null);
        statusIconLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        headIconLabel = JLabelFactory.createJLabel(new ImageIcon("res/main/headIcon.png"));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setOpaque(false);
        displayNameLabel = JLabelFactory.createJLabel("", new Font("ËÎÌו", Font.PLAIN, 12), Color.darkGray);
        displayNameLabel.setSize(50, 23);
        displayNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        displayNameLabel.setHorizontalAlignment(JLabel.LEFT);
        displayNameLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(headIconLabel);
        add(statusIconLabel);
        add(displayNameLabel);
    }

    public ContactItem(Contact contact){
        this();
        if(contact!=null){
            this.yhContact = contact;
            statusIcon = contact.getPresenceType().getPresenceTypeIcon();
            statusIconLabel.setIcon(statusIcon);
            displayNameLabel.setText(contact.getUserName());
        }
    }

    public JLabel getStatusIconLabel() {
        return statusIconLabel;
    }

    public void setStatusIconLabel(JLabel statusIconLabel) {
        this.statusIconLabel = statusIconLabel;
    }

    public JLabel getDisplayNameLabel() {
        return displayNameLabel;
    }

    public void setDisplayNameLabel(JLabel displayNameLabel) {
        this.displayNameLabel = displayNameLabel;
    }

    public JLabel getHeadIconLabel() {
        return headIconLabel;
    }

    public void setHeadIconLabel(JLabel headIconLabel) {
        this.headIconLabel = headIconLabel;
    }


    public String getjId() {
        return yhContact.getJid();
    }

    public void setjId(String jId) {
        this.yhContact.setJid(jId);
    }

    public ImageIcon getStatusIcon() {
        return statusIcon;
    }

    public void setStatusIcon(ImageIcon statusIcon) {
        statusIconLabel.setIcon(statusIcon);
        this.statusIcon = statusIcon;
    }

    public void setUserName(String userName){
        displayNameLabel.setText(userName);
    }

    public String getUserName(){
        return displayNameLabel.getText();
    }

    public void click(){};
}
