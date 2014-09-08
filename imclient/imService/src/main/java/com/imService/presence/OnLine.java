package com.imService.presence;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;

/**
 * Created by a on 2014/9/5.
 */
public class OnLine extends PresenceType {
    private ImConnection imConnection;
    public OnLine(String presenceName,ImageIcon presenceTypeIcon,ImConnection imConnection) {
        this.presenceTypeIcon = presenceTypeIcon;
        this.presenceName = presenceName;
        this.imConnection = imConnection;
    }

    @Override
    public void changePresence(String jid) {
        Presence presence = new Presence(org.jivesoftware.smack.packet.Presence.Type.available);
        presence.setMode(org.jivesoftware.smack.packet.Presence.Mode.chat);
        presence.setStatus(presenceName);
        imConnection.getXMPPConnection().sendPacket(presence);
    }
}
