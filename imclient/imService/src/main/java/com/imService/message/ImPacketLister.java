package com.imService.message;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public class ImPacketLister implements PacketListener {

    private static List<MessageHandel> messageHandels = new ArrayList<>();

    @Override
    public void processPacket(Packet packet) {
        if(packet instanceof Message){
            Message message = (Message)packet;
            for(MessageHandel messageHandel : messageHandels){
                messageHandel.handel(message);
            }
        }
    }

    public static void addMessageHandel(MessageHandel messageHandel){
        messageHandels.add(messageHandel);
    }
}
