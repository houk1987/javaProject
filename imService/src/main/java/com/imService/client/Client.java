package com.imService.client;

import com.imService.presence.PresenceType;
import com.imService.session.ChatSession;
import com.imService.session.GroupSession;
import com.imService.session.Session;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import java.util.List;

/**
 * Created by lenovo on 2014/9/17.
 */
public class Client {
    protected XMPPConnection xmppConnection;
    private Lander lander;
    protected List<PresenceType> presenceTypes;

    public Client(XMPPConnection xmppConnection) {
        this.xmppConnection = xmppConnection;
    }

    public Client() {

    }

    public void loginClient(String account, String password) throws NullPointerException, XMPPException {
        if (xmppConnection == null) throw new NullPointerException("创建服务器链接失败!");
        try {
            if(!xmppConnection.isConnected())xmppConnection.connect();
        } catch (XMPPException e) {
            e.printStackTrace();

            throw new XMPPException("服务器链接失败!");
        }
        try {
            xmppConnection.login(account, password);
            lander = new Lander(account, new Presence(Presence.Type.available));
            addRosterListener(null);
        } catch (XMPPException e) {
            e.printStackTrace();
            xmppConnection.disconnect();
            throw new XMPPException("帐号或密码错误!");
        }
    }

    public void loginOut() throws NullPointerException {
        if (xmppConnection == null) throw new NullPointerException("服务器链接失败!");
        xmppConnection.disconnect();
    }

    public Lander getLander() {
        return lander;
    }

    public void initXMPPConnection(String host, int port, String domain) {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(host, port, domain);
        this.xmppConnection = new XMPPConnection(connectionConfiguration);

    }

    public void addPacketListener(PacketListener packetListener) throws NullPointerException {
        if (xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("添加PacketListener失败!");
        if(packetListener!=null){
            xmppConnection.addPacketListener(packetListener, new PacketTypeFilter(Message.class));
        }
    }

    public void addRosterListener(RosterListener rosterListener) throws NullPointerException {
        if (xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("添加RosterListener失败!");
        if(rosterListener != null){
            xmppConnection.getRoster().addRosterListener(rosterListener);
        }
    }

    public void changeLanderPresence(Presence presence) throws NullPointerException {
        if (presence == null || xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("修改状态失败!");
        xmppConnection.sendPacket(presence);
        if (lander != null) {
            lander.setPresence(presence);
        }
    }

    public List<PresenceType> getPresenceTypes() {
        return presenceTypes;
    }

    public Presence getPresence(String jid) {
        if (jid == null || xmppConnection == null || !xmppConnection.isConnected())
            return new Presence(Presence.Type.unavailable);
        Presence presence = xmppConnection.getRoster().getPresence(jid);
        return presence;
    }

    public Session getChatSession(String user) {
        if (user == null || xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("打开会话失败!");
        return new ChatSession(xmppConnection, user);
    }

    public Session getGroupSession(String groupId, String user) {
        if (user == null || groupId == null || xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("打开会话失败!");
        return new GroupSession(xmppConnection, groupId, user);
    }
}
