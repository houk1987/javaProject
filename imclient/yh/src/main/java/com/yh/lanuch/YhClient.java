package com.yh.lanuch;

import com.imService.client.Client;
import com.imService.connection.ImConnection;
import com.imService.message.ImPacketLister;
import com.yh.session.message.AcceptNewContactHandel;
import com.yh.session.message.ChatMessageHandel;
import org.jivesoftware.smack.XMPPException;

/**
 * Created by a on 2014/9/4.
 */
public class YhClient extends Client{

    private static YhClient yhClient;

    public static YhClient getInstance(){
        if(yhClient == null){
            yhClient = new YhClient(new YhImConnection());
        }
        return yhClient;
    }

    private YhClient(ImConnection imConnection) {
        super(imConnection);
    }

    @Override
    public void loginClient(String account, String pwd) throws XMPPException {
        super.loginClient(account, pwd);
        ImPacketLister.addMessageHandel(new ChatMessageHandel());
        ImPacketLister.addMessageHandel(new AcceptNewContactHandel());
    }


    @Override
    public void closeClient() {
        //询问用户是否确定退出 TODO
        super.closeClient();
        System.exit(0);
    }


}
