package com.dtclient.lanuch;

import com.dtclient.main.MainFrame;
import com.dtclient.main.tree.SynDataService;
import com.dtclient.session.messageHandel.ChatMessageHandel;
import com.dtclient.vo.UserInfo;
import com.imService.client.Client;
import com.imService.connection.ImConnection;
import com.imService.message.ImPacketLister;
import org.jivesoftware.smack.XMPPException;

import java.util.List;

/**
 * Created by HK on 2014/9/9.
 */
public class DtClient extends Client {

    private static DtClient dtClient;
    private List<UserInfo> userInfoList;
    private UserInfo userInfo;

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
        userInfoList = SynDataService.getInstance().synUsers();
        for(UserInfo userInfo: userInfoList){
            if(userInfo.getId().equals(DtClient.getInstance().getLoginAccount())){
                DtClient.getInstance().setUserInfo(userInfo);
            }
        }
        ImPacketLister.addMessageHandel(new ChatMessageHandel());
        MainFrame.getInstance().setVisible(true);


    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }
}
