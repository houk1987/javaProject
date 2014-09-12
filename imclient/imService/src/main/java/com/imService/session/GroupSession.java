package com.imService.session;

import com.imService.connection.ImConnection;
import com.imService.message.SessionMessage;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 * Created by lenovo on 2014/9/12.
 */
public class GroupSession  implements Session{

    private MultiUserChat muc;
    public GroupSession(ImConnection imConnection,String jid,String nickName) {
        try {
            muc = new MultiUserChat(imConnection.getXMPPConnection(),jid);
            muc.join(nickName);
        } catch (XMPPException e) {
            e.printStackTrace();
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
        message.setType(Message.Type.groupchat);
        try {
            muc.sendMessage(message);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptMessage(Message message) {

    }
}
