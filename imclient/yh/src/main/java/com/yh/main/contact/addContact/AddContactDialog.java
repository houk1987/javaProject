package com.yh.main.contact.addContact;

import com.yh.main.MainFrame;

import javax.swing.*;

/**
 * Created by a on 2014/9/9.
 */
public class AddContactDialog extends JDialog {

    public AddContactDialog(JFrame jFrame) {
        super(jFrame);
        setContentPane(new ContactPane(this));
        setSize(426,426);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(jFrame);
        setTitle("º”»Î≈Û”—√˚ÜŒ");
    }
}
