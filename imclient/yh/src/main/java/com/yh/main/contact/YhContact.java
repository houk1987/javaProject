package com.yh.main.contact;

import com.imService.contact.Contact;
import com.imService.presence.PresenceType;

import javax.swing.*;

/**
 * Created by a on 2014/9/5.
 */
public class YhContact extends Contact {

    public YhContact(String jid,String userName,ImageIcon headIcon) {
        super(jid,userName,headIcon);
    }

    @Override
    public void setPresenceType(PresenceType presenceType) {

        super.setPresenceType(presenceType);
    }
}
