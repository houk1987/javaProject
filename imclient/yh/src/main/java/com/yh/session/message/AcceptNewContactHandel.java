package com.yh.session.message;

import com.imService.message.MessageHandel;
import com.yh.notify.AcceptNewContactDialog;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by HK on 2014/9/8.
 */
public class AcceptNewContactHandel implements MessageHandel {

    @Override
    public void handel(Message message) {
        String subject = message.getSubject();
        System.out.println(subject);
        if("∫√”—…Í’à".equals(subject)){
            System.out.println(subject);
            AcceptNewContactDialog acceptNewContactDialog = new AcceptNewContactDialog(message.getFrom());
            acceptNewContactDialog.showNotifyWindow();
        }
    }
}
