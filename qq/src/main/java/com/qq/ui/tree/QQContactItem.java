package com.qq.ui.tree;

import com.imService.contact.Contact;
import com.qq.ui.session.SessionFrame;
import com.ui.tree.ContactItem;

/**
 * Created by lenovo on 2014/9/19.
 */
public class QQContactItem extends ContactItem {

    public QQContactItem(Contact contact) {
        super(contact);
    }

    @Override
    public void click() {
        SessionFrame.openChatSessionFrame(getjId());
    }
}
