package com.dtclient.manager;

import com.dtclient.main.frame.MainFrame;
import com.dtclient.sys.SysProperties;
import com.imService.connection.ImConnection;

/**
 * Created by HK on 2014/9/9.
 */
public class DtManager {
    private static DtManager dtManager;
    private ImConnection dtClientConnection;
    public static DtManager getInstance() {
        if(dtManager ==null) dtManager = new DtManager();
        return dtManager;
    }

    private DtManager() {
        this.dtClientConnection = new ImConnection(SysProperties.getHost(),SysProperties.getPort(),SysProperties.domain());

    }

    public void showMainFrame(){
        SynDataManager.getInstance().synUnits();
        SynDataManager.getInstance().synUsers();
        MainFrame.getInstance().setVisible(true);
    }

    public ImConnection getDtClientConnection() {
        return dtClientConnection;
    }
}
