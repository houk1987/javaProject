package com.yh.lanuch;

import com.imService.connection.ImConnection;
import com.imService.presence.PresenceType;
import com.pubTools.properties.PropertiesTools;
import com.yh.main.MainFrame;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Presence;

/**
 * Created by a on 2014/9/4.
 */
public class YhImConnection extends ImConnection {


    @Override
    protected XMPPConnection createXMPPConnection() {
        PropertiesTools propertiesTools = new PropertiesTools();
        String filePath = "user/sys.properties";
        String host = propertiesTools.readValue(filePath,"host");
        int port = Integer.valueOf(propertiesTools.readValue(filePath, "port"));
        String resource = propertiesTools.readValue(filePath,"resource");
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(host,port,resource);
        connectionConfiguration.setSASLAuthenticationEnabled(false);
        return new XMPPConnection(connectionConfiguration);
    }

    @Override
    public void presenceChangedHandel(Presence presence) {
        MainFrame.getInstance().refreshContactTreePresence(presence);
    }
}
