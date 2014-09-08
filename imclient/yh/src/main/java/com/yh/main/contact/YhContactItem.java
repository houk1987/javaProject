package com.yh.main.contact;

import com.imService.contact.Contact;
import com.ui.tree.ContactItem;
import com.yh.session.SessionFrame;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by a on 2014/9/5.
 */
public class YhContactItem extends ContactItem {


    public YhContactItem(Contact contact) {
        super(contact);
    }

    @Override
    public void click() {
        SessionFrame.CreateAndShowSessionFrame(getjId(), Message.Type.chat);
    }
}
