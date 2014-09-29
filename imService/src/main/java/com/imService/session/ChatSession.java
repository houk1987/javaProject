package com.imService.session;

import com.imService.message.SessionMessage;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by a on 2014/9/4.
 */
public class ChatSession implements Session {

    private Chat chat;

    public ChatSession(XMPPConnection xmppConnection,String jid) {
        jid = jid + "@"+xmppConnection.getServiceName();
        chat = xmppConnection.getChatManager().getThreadChat(jid );
        if (chat == null) {
            chat = xmppConnection.getChatManager().createChat(jid, null);
        }
    }

    @Override
    public void sendMessage(SessionMessage sessionMessage) {
        if(sessionMessage !=null){
            Message message = sessionMessage.getMessage();
           sendMessage(message);
        }
    }

    @Override
    public void sendMessage(Message message) {
        message.setType(Message.Type.chat);
        try {
            chat.sendMessage(message);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptMessage(Message message) {

    }
}
