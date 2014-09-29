package com.yh.lanuch;

import com.imService.client.Client;
import com.imService.contact.Contact;
import com.imService.contact.RosterManager;
import com.imService.presence.Away;
import com.imService.presence.OffLine;
import com.imService.presence.OnLine;
import com.imService.presence.PresenceType;
import com.yh.listener.YhPacketListener;
import com.yh.listener.YhRosterListener;
import com.yh.login.LoginFrame;
import com.yh.main.MainFrame;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import sys.SysProperties;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2014/9/22.
 */
public class YhClient extends Client {

    private static YhClient yhClient;
    private LoginFrame loginFrame;

    public static YhClient getInstance() {
        if (yhClient == null) {
            yhClient = new YhClient();
        }
        return yhClient;
    }

    private YhClient() {
        initXMPPConnection(SysProperties.getHost(), SysProperties.getPort(), SysProperties.domain());
    }

    @Override
    public void loginClient(String account, String password) throws NullPointerException, XMPPException {
        super.loginClient(account, password);
        closeLoginFrame();
        showMainFrame();
        addPacketListener(new YhPacketListener());
        addRosterListener(new YhRosterListener());
    }

    public void showMainFrame() {
        MainFrame.getInstance().setVisible(true);
    }

    public void closeLoginFrame() {
        if (loginFrame != null) {
            loginFrame.dispose();
            loginFrame = null;
        }
    }


    @Override
    public void loginOut() throws NullPointerException {
        super.loginOut();
    }

    @Override
    public List<PresenceType> getPresenceTypes() {
        if (presenceTypes == null) {
            presenceTypes = new ArrayList<>();
            presenceTypes.add(new OnLine("我有空", new ImageIcon("res/status/online.png")));
            presenceTypes.add(new Away("忙碌中", new ImageIcon("res/status/busy.png")));
            presenceTypes.add(new OffLine("對所有人隐藏", new ImageIcon("res/status/offline.png")));
        }
        return presenceTypes;
    }

    public void showLoginFrame() {
        if (loginFrame == null) {
            loginFrame = new LoginFrame();
        }
        loginFrame.setVisible(true);
    }

    /**
     * 获取用户所有好友
     *
     * @return 好友联系人列表
     */
    public List<Contact> getContacts() {
        xmppConnection.getRoster().reload();
        List<RosterEntry> rosterEntryList = RosterManager.getAllEntries(xmppConnection.getRoster());
        if (rosterEntryList != null && rosterEntryList.size() > 0) {
            List<Contact> contactList = new ArrayList<>();
            for (RosterEntry rosterEntry : rosterEntryList) {
                contactList.add(initContact(rosterEntry));
            }
            return contactList;
        }
        return null;
    }

    public Contact getContact(String from) {
        xmppConnection.getRoster().reload();

        return initContact( xmppConnection.getRoster().getEntry(from));
    }

    private Contact initContact(RosterEntry rosterEntry){
        if(rosterEntry!=null){
            Contact contact = new Contact(rosterEntry.getUser().split("@")[0], rosterEntry.getName());
            contact.setPresenceType(getPresenceType(getPresence(rosterEntry.getUser())));
            return contact;
        }
        return null;
    }

    public PresenceType getPresenceType(Presence presence) {
        if (presence.getType().equals(Presence.Type.available)) {
            return new OnLine("我有空", new ImageIcon("res/status/online.png"));
        } else if (presence.getType().equals(Presence.Type.unavailable) || presence.getType().equals(Presence.Type.error)) {
            return new OffLine("對所有人隐藏", new ImageIcon("res/status/offline.png"));
        }
        return null;
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
}
