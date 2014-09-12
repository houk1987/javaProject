package com.yh.session.message;

import com.imService.message.MessageHandel;
import com.imService.message.SessionMessage;
import com.yh.session.SessionFrame;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by a on 2014/9/5.
 */
public class ChatMessageHandel implements MessageHandel {

    public ChatMessageHandel() {

    }

    @Override
    public void handel(Message message) {
        if(Message.Type.chat.equals(message.getType()) && !"好友申請".equals(message.getSubject())){  //如果是2人会话
            //打开聊天窗口
            SessionFrame sessionFrame =SessionFrame.CreateAndShowSessionFrame(message.getFrom(), Message.Type.chat);
            SessionMessage sessionMessage = new SessionMessage(message);
            sessionFrame.insertMessageToDisplay(sessionMessage);
        }
    }
}
