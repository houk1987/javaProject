package com.imService.session;

import com.imService.connection.ImConnection;
import com.imService.message.SessionMessage;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by a on 2014/9/4.
 */
public class ChatSession implements Session {

    private Chat chat;

    public ChatSession(ImConnection imConnection,String jid) {
        chat = imConnection.getXMPPConnection().getChatManager().getThreadChat(jid + imConnection.getServerName());
        if (chat == null) {
            chat = imConnection.getXMPPConnection().getChatManager().createChat(jid + imConnection.getServerName(), null);
        }
    }

    @Override
    public void sendMessage(SessionMessage sessionMessage) {
        if(sessionMessage !=null){
            Message message = sessionMessage.getMessage();
            message.setType(Message.Type.chat);
            try {
                chat.sendMessage(message);
            } catch (XMPPException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void acceptMessage(Message message) {

    }
}
