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
        if (xmppConnection == null) throw new NullPointerException("��������������ʧ��!");
        try {
            if(!xmppConnection.isConnected())xmppConnection.connect();
        } catch (XMPPException e) {
            e.printStackTrace();

            throw new XMPPException("����������ʧ��!");
        }
        try {
            xmppConnection.login(account, password);
            lander = new Lander(account, new Presence(Presence.Type.available));
            addRosterListener(null);
        } catch (XMPPException e) {
            e.printStackTrace();
            xmppConnection.disconnect();
            throw new XMPPException("�ʺŻ��������!");
        }
    }

    public void loginOut() throws NullPointerException {
        if (xmppConnection == null) throw new NullPointerException("����������ʧ��!");
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
            throw new NullPointerException("���PacketListenerʧ��!");
        if(packetListener!=null){
            xmppConnection.addPacketListener(packetListener, new PacketTypeFilter(Message.class));
        }
    }

    public void addRosterListener(RosterListener rosterListener) throws NullPointerException {
        if (xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("���RosterListenerʧ��!");
        if(rosterListener != null){
            xmppConnection.getRoster().addRosterListener(rosterListener);
        }
    }

    public void changeLanderPresence(Presence presence) throws NullPointerException {
        if (presence == null || xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("�޸�״̬ʧ��!");
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
            throw new NullPointerException("�򿪻Ựʧ��!");
        return new ChatSession(xmppConnection, user);
    }

    public Session getGroupSession(String groupId, String user) {
        if (user == null || groupId == null || xmppConnection == null || !xmppConnection.isConnected())
            throw new NullPointerException("�򿪻Ựʧ��!");
        return new GroupSession(xmppConnection, groupId, user);
    }
}
