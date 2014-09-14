package com.yh.manager;

import com.imService.connection.ImConnection;
import sys.SysProperties;

/**
 * Created by HK on 2014/9/15.
 */
public class YhManager {

    private ImConnection imConnection;
    private static YhManager yhManager;
    private String loginAccount;

    public static YhManager getInstance() {
        if(yhManager == null){
            yhManager = new YhManager();
        }
        return yhManager;
    }

    private YhManager(){
        imConnection = new ImConnection(SysProperties.getHost(),SysProperties.getPort(),SysProperties.domain());
    }

    public void showMainFrame(String user){
        loginAccount = user;
    }

    public ImConnection getImConnection() {
        return imConnection;
    }

    public String getLoginAccount() {
        return loginAccount;
    }
}
