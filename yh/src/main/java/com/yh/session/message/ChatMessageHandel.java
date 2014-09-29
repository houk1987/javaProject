package com.yh.session.message;
import com.imService.contact.Contact;
import com.imService.message.MessageHandel;
import com.imService.message.SessionMessage;
import com.yh.lanuch.YhClient;
import com.yh.main.MainFrame;
import com.yh.notify.AcceptNewContactDialog;
import com.yh.session.SessionFrame;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import sys.SysProperties;

/**
 * Created by a on 2014/9/5.
 */
public class ChatMessageHandel implements MessageHandel {

    public ChatMessageHandel() {

    }

    @Override
    public void handel(Message message) {
        String userId = message.getFrom().split("/")[0];
        String subject = message.getSubject();
        if(Message.Type.chat.equals(message.getType())){  //如果是2人会话
                Contact contact = YhClient.getInstance().getContact(userId);
                SessionFrame sessionFrame = SessionFrame.CreateAndShowSessionFrame(contact, Message.Type.chat);
                SessionMessage sessionMessage = new SessionMessage(message);
                sessionFrame.insertMessageToDisplay(sessionMessage);
        }else if(Message.Type.normal.equals(message.getType()) &&"好友申请".equals(subject)){
            AcceptNewContactDialog acceptNewContactDialog = new AcceptNewContactDialog(userId);
            acceptNewContactDialog.setVisible(true);
        }else{
            Contact contact = YhClient.getInstance().getContact(message.getTo());
            if(contact==null){
                contact = new Contact(message.getTo().split("@")[0],message.getTo().split("@")[0]);
                Presence presence = new Presence(Presence.Type.available);
                contact.setPresenceType(YhClient.getInstance().getPresenceType(presence));
            }
           MainFrame.getInstance().getYhContactTree().addFriends(contact);
        }
    }
}
