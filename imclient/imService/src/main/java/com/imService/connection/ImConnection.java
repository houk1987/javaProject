package com.imService.connection;

import com.imService.message.ImPacketLister;


import org.jivesoftware.smack.*;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class ImConnection {
    private XMPPConnection xmppConnection;
    private String serverName;
    protected ImConnection() {
        xmppConnection =createXMPPConnection();
        serverName = "@" + xmppConnection.getServiceName();
    }

    public void connection()throws XMPPException{
        if(isConnectionNotNull()){
            try {
                xmppConnection.connect();
                xmppConnection.addPacketListener(new ImPacketLister(),new PacketTypeFilter(Message.class)); //添加消息监听
                listenerContactPresence(); //监听好友状态
            } catch (XMPPException e) {
                e.printStackTrace();
                throw new XMPPException("服务器连接失败!");

            }
        }
    }

    public void close(){
        if(isConnectionNotNull()){
            xmppConnection.disconnect();
        }
    }

    public void login(String account, String password) throws XMPPException {
        try {
            if(isConnectionNotNull()) {
                xmppConnection.login(account , password);
            }
        } catch (XMPPException e) {
            e.printStackTrace();
            throw new XMPPException("账号或密码错误!");
        }
    }

    /**
     * 返回所有用户信息 <RosterEntry>
     *
     * @return List(RosterEntry)
     */
    public List<RosterEntry> getAllEntries() {
        List<RosterEntry> EntriesList = new ArrayList<RosterEntry>();
        if(isConnectionNotNull()) {
            Roster roster = xmppConnection.getRoster();
            Collection<RosterEntry> rosterEntry = roster.getEntries();
            Iterator<RosterEntry> i = rosterEntry.iterator();
            while (i.hasNext())
                EntriesList.add(i.next());
        }
        return EntriesList;
    }

    public Presence getContactPresence(String  jid){
        Presence prsn = null;
        if(isConnectionNotNull()) {
            Roster roster = xmppConnection.getRoster();
            prsn = roster.getPresence(jid);
        }
        return prsn;
    }

    public void listenerContactPresence(){
        final Roster roster = xmppConnection.getRoster();

        roster.addRosterListener(
                new RosterListener() {

                    @Override
                    public void entriesAdded(Collection<String> arg0) {
                        // TODO Auto-generated method stub
                        System.out.println("--------EE:"+"entriesAdded");
                    }

                    @Override
                    public void entriesDeleted(Collection<String> arg0) {
                        // TODO Auto-generated method stub
                        System.out.println("--------EE:"+"entriesDeleted");
                    }

                    @Override
                    public void entriesUpdated(Collection<String> arg0) {
                        // TODO Auto-generated method stub
                        System.out.println("--------EE:"+"entriesUpdated");
                    }

                    @Override
                    public void presenceChanged(Presence arg0) {
                        presenceChangedHandel(arg0);
                    }
                });
    }

    /**
     * 由子类进行实现
     */
    public abstract void presenceChangedHandel(Presence arg0);


    public XMPPConnection getXMPPConnection(){
        return xmppConnection;
    }

    private boolean isConnectionNotNull(){
        return xmppConnection != null;
    }

    public String getServerName() {
        return serverName;
    }

    protected abstract XMPPConnection createXMPPConnection();
}
