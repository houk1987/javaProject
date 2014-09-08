package com.imService.presence;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;

public class Hide extends PresenceType {
    private ImConnection imConnection;
    public Hide(String presenceName,ImageIcon presenceTypeIcon,ImConnection imConnection) {
        this.presenceTypeIcon = presenceTypeIcon;
        this.presenceName = presenceName;
        this.imConnection = imConnection;
    }

    @Override
    public void changePresence(String jid) {
        Presence presence = new Presence(Presence.Type.unavailable);
        presence.setStatus(presenceName);
        imConnection.getXMPPConnection().sendPacket(presence);
    }
}
