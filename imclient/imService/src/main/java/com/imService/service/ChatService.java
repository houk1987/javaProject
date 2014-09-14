package com.imService.service;


import com.imService.connection.ImConnection;
import com.imService.session.ChatSession;
import com.imService.session.GroupSession;
import com.imService.session.Session;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by HK on 2014/9/15.
 */
public class ChatService {
    private ImConnection connection;

    public Session createChat(String jid) {
        return new ChatSession(connection, jid);

    }

    public Session createGroupChat(String jid,String user){
        return new GroupSession(connection,jid,user);
    }
}
