package com.imService.presence;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;

/**
 * Created by a on 2014/9/5.
 * PresenceType
 *
 *
 */
public class Busy extends PresenceType {
    private ImConnection imConnection;
    public Busy(String presenceName,ImageIcon presenceTypeIcon,ImConnection imConnection) {
        this.presenceTypeIcon = presenceTypeIcon;
        this.presenceName = presenceName;
        this.imConnection = imConnection;
    }

    @Override
    public void changePresence(String jid) {
        Presence presence = new Presence(Presence.Type.available);
        presence.setMode(Presence.Mode.dnd);
        presence.setStatus(presenceName);
        imConnection.getXMPPConnection().sendPacket(presence);
    }
}
