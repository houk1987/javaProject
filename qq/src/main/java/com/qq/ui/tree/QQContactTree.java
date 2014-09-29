package com.qq.ui.tree;


import com.imService.contact.Contact;
import com.qq.lanuch.QQClient;
import com.ui.tree.ContactItem;
import com.ui.tree.TreePane;
import org.jivesoftware.smack.packet.Presence;

import java.awt.*;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class QQContactTree extends TreePane {
    private QQContactGroup friends;   //我的好友

    public QQContactTree() {
        super();
        mainPanel.setBackground(new Color(230,238,244));
    }

    @Override
    protected void loadData() {
        friends = new QQContactGroup("我的好友");
        List<Contact> contactList = QQClient.getInstance().getContacts();
        if(contactList!=null && contactList.size()>0) {
            for (Contact contact : contactList) {
                friends.addContactItem(new QQContactItem(contact));
            }
        }
        mainPanel.add(friends);
    }

    public void changeContactItemPresence(Presence presence){
        List<ContactItem> contactItemList = friends.getAllContactItem();
        final String jid = presence.getFrom().split("/")[0];
        for (ContactItem contactItem : contactItemList){
            if(jid.equals(contactItem.getjId())){
                //contactItem.setStatusIcon(YhPresence.getPresenceTypeIcon(presence));
            }
        }
        friends.updateUI();
    }
}
