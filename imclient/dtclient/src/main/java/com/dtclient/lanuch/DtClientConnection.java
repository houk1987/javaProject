package com.dtclient.lanuch;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Presence;

/**
 * Created by HK on 2014/9/9.
 */
public class DtClientConnection extends ImConnection {



    @Override
    protected XMPPConnection createXMPPConnection() {
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(StartDtClient.host,StartDtClient.port,StartDtClient.resource);
        return new XMPPConnection(connectionConfiguration);
    }

    @Override
    public void presenceChangedHandel(Presence arg0) {

    }
}
