package com.qq.ui.session.messageHandel;

import com.imService.message.MessageHandel;
import com.imService.message.SessionMessage;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by a on 2014/9/5.
 */
public class ChatMessageHandel implements MessageHandel {

    public ChatMessageHandel() {

    }

    @Override
    public void handel(Message message) {
        if(Message.Type.chat.equals(message.getType())){  //如果是2人会话
            System.out.println(message.getFrom());
            String userId = message.getFrom().split("@")[0];
//            UserInfo userInfo = DTClient.getInstance().getUserInfo(userId);
//            SessionFrame sessionFrame = SessionFrame.openSessionFrame(userInfo);
//            SessionMessage sessionMessage = new SessionMessage(message);
//            sessionFrame.insertMessageToDisplay(sessionMessage);
        }
    }
}
