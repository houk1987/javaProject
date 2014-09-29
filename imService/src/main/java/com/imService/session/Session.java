package com.imService.session;

import com.imService.message.SessionMessage;
import org.jivesoftware.smack.packet.Message;

/**
 * Created by a on 2014/9/4.
 */
public interface Session {

    void sendMessage(SessionMessage sessionMessage);

    void sendMessage(Message message);

    void acceptMessage(Message message);
}
