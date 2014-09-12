package com.dtclient.lanuch;

import com.dtclient.sys.SysProperties;
import com.imService.connection.ImConnection;
import com.pubTools.properties.PropertiesTools;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Presence;

/**
 * Created by HK on 2014/9/9.
 */
public class DtClientConnection extends ImConnection {

    @Override
    protected XMPPConnection createXMPPConnection() {
        String domain = SysProperties.domain();   //”Ú√˚
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(StartDtClient.host,StartDtClient.port,domain);
        connectionConfiguration.setSASLAuthenticationEnabled(false);
        return new XMPPConnection(connectionConfiguration);
    }

    @Override
    public void presenceChangedHandel(Presence arg0) {

    }
}
