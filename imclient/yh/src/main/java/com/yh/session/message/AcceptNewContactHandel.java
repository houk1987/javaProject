package com.yh.session.message;

import com.imService.message.MessageHandel;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by HK on 2014/9/8.
 */
public class AcceptNewContactHandel implements MessageHandel {

    @Override
    public void handel(Message message) {
        String messageType = message.getProperty("messageType").toString();
        if("applyNewContact".equals(messageType)){
            //提示用户
            System.out.println("asdsadsada");
        }
    }
}
