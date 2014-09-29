package com.dt.listener;

import com.dt.session.messageHandel.ChatMessageHandel;
import com.imService.message.MessageHandel;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * Created by lenovo on 2014/9/18.
 */
public class DtPacketListener implements PacketListener {

    private static MessageHandel chatMessageHandel = new ChatMessageHandel();

    @Override
    public void processPacket(Packet packet) {
        if(packet instanceof Message){
            Message message = (Message)packet;
            chatMessageHandel.handel(message);
        }
    }
}
