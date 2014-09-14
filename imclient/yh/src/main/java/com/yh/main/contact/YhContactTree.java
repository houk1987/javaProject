package com.yh.main.contact;


import com.imService.contact.Contact;
import com.ui.tree.ContactGroup;
import com.ui.tree.ContactItem;
import com.ui.tree.TreePane;
import com.yh.manager.YhManager;
import com.yh.presence.YhPresence;
import org.jivesoftware.smack.packet.Presence;

import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class YhContactTree extends TreePane {

    private ContactGroup recentContacts;
    private ContactGroup friends;
    private ContactGroup address;

    @Override
    protected void loadData() {
        recentContacts  = new ContactGroup("最近聯繫人");
        friends = new ContactGroup("friends");
        address = new ContactGroup("通訊錄");
        //获得所有的好友
        YmContactManager ymContactManager = new YmContactManager(YhManager.getInstance().getImConnection());
        List<Contact> contactList = ymContactManager.getAllContact();
        //循环添加friends 好友
        for(Contact contact : contactList){
            YhContactItem contactItem = new YhContactItem(contact);
            friends.addContactItem(contactItem);
        }

        mainPanel.add(recentContacts);
        mainPanel.add(friends);
        mainPanel.add(address);
    }

    public void changeContactItemPresence(Presence presence){
        List<ContactItem> contactItemList = friends.getAllContactItem();
        final String jid = presence.getFrom().split("/")[0];
        for (ContactItem contactItem : contactItemList){
            if(jid.equals(contactItem.getjId())){
                contactItem.setStatusIcon(YhPresence.getPresenceTypeIcon(presence));
            }
        }
        friends.updateUI();
    }
}
