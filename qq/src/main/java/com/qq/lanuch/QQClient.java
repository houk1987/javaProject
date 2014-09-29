package com.qq.lanuch;

import com.imService.client.Client;
import com.imService.contact.Contact;
import com.imService.contact.RosterManager;
import com.imService.presence.Away;
import com.imService.presence.OffLine;
import com.imService.presence.OnLine;
import com.imService.presence.PresenceType;
import com.qq.sys.SysProperties;
import com.qq.ui.login.LoginDialog;
import com.qq.ui.main.MainFrame;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2014/9/17.
 */
public class QQClient extends Client {

    private static QQClient qqClient = new QQClient();
    private LoginDialog loginDialog;

    public static QQClient getInstance() {
        return qqClient;
    }

    private QQClient() {
        initXMPPConnection(SysProperties.getHost(), SysProperties.getPort(), SysProperties.domain());
    }

    public void showLoginDialog() {
        if (loginDialog == null) {
            loginDialog = new LoginDialog();
        }
        loginDialog.setVisible(true);
    }

    public void showMainFrame() {
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public void loginClient(String account, String password) throws NullPointerException, XMPPException {
        super.loginClient(account, password);
        if (loginDialog != null) {
            loginDialog.dispose();
            loginDialog = null;
        }
        showMainFrame();
    }

    @Override
    public void loginOut() throws NullPointerException {
        super.loginOut();
    }

    @Override
    public List<PresenceType> getPresenceTypes() {
        presenceTypes = new ArrayList<>();
        presenceTypes.add(new OnLine("����", new ImageIcon("res/status/online.png")));
        presenceTypes.add(new Away("�뿪", new ImageIcon("res/status/away.png")));
        presenceTypes.add(new OffLine("����", new ImageIcon("res/status/offline.png")));
        return presenceTypes;
    }

    /**
     * ��ȡ�û����к���
     *
     * @return ������ϵ���б�
     */
    public List<Contact> getContacts() {
        List<RosterEntry> rosterEntryList = RosterManager.getAllEntries(xmppConnection.getRoster());
        if (rosterEntryList != null && rosterEntryList.size() > 0) {
            List<Contact> contactList = new ArrayList<>();
            for (RosterEntry rosterEntry : rosterEntryList) {
                Contact contact = new Contact(rosterEntry.getUser(),rosterEntry.getName());
                contactList.add(contact);
            }
            return contactList;
        }
        return null;
    }
}
