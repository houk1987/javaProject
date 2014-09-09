package com.dtclient.lanuch;

import com.dtclient.main.MainFrame;
import com.imService.client.Client;
import com.imService.connection.ImConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * Created by HK on 2014/9/9.
 */
public class DtClient extends Client {

    private static DtClient dtClient;

    public static DtClient getInstance() {
        if(dtClient==null)dtClient = new DtClient(new DtClientConnection());
        return dtClient;
    }

    public DtClient(ImConnection imConnection) {
        super(imConnection);
    }

    @Override
    protected void loginClient(String account, String pwd) throws XMPPException {
        super.loginClient(account, pwd);
        MainFrame.getInstance().setVisible(true);
    }
}
