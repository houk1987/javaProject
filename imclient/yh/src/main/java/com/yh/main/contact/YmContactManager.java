package com.yh.main.contact;

import com.imService.connection.ImConnection;
import com.imService.contact.Contact;
import com.imService.contact.ContactManager;
import com.imService.contact.Group;
import com.imService.presence.OnLine;
import com.imService.presence.PresenceType;
import com.yh.lanuch.YhClient;
import com.yh.presence.YhPresence;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class YmContactManager extends ContactManager {

    public YmContactManager(ImConnection imConnection) {
        super(imConnection);
    }

    /**
     * 胡群殴的所以的好友
     * @return
     */
    @Override
    public List<Contact> getAllContact() {
        List<Contact> contacts = new ArrayList<>();
        ImageIcon headIcon = new ImageIcon("res/main/headItem.png");
        //默认添加自己

        YhContact m = new YhContact(YhClient.getInstance().getLoginAccount(),YhClient.getInstance().getLoginAccount(),headIcon);
        Presence ownerpresence = YhClient.getInstance().getImConnection().getContactPresence(YhClient.getInstance().getLoginAccount());
        m.setPresenceType(new OnLine("",YhPresence.getPresenceTypeIcon(ownerpresence),YhClient.getInstance().getImConnection()));
        contacts.add(m);
        for(RosterEntry entry : rosterEntryList){
            YhContact yhContact = new YhContact(entry.getUser(),entry.getUser(),headIcon);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Presence presence = YhClient.getInstance().getImConnection().getContactPresence(entry.getUser());
            yhContact.setPresenceType(new OnLine("", YhPresence.getPresenceTypeIcon(presence), YhClient.getInstance().getImConnection()));
            contacts.add(yhContact);
        }
        return contacts;
    }

    @Override
    public List<Group> getAllGroup() {
        return null;
    }

    public void  applyNewContact(String applyAccount){
        Message message = new Message();
        message.setFrom(YhClient.getInstance().getLoginAccount());
        message.setTo(applyAccount);
        message.setBody(YhClient.getInstance().getLoginAccount()+"申请您为好友!");
        message.setProperty("messageType","applyNewContact");
        YhClient.getInstance().getSession(applyAccount, Message.Type.chat).sendMessage(message);
    }
}
