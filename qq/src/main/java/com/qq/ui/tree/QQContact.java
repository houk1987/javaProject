package com.qq.ui.tree;

import com.imService.contact.Contact;
import com.imService.presence.PresenceType;

import javax.swing.*;

/**
 * Created by a on 2014/9/5.
 */
public class QQContact extends Contact {

    public QQContact(String jid, String userName, ImageIcon headIcon) {
        super(jid,userName,headIcon);
    }

    @Override
    public void setPresenceType(PresenceType presenceType) {
        super.setPresenceType(presenceType);
    }
}
