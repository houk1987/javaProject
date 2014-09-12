package com.imService.client;

import com.imService.connection.ImConnection;
import com.imService.session.ChatSession;
import com.imService.session.GroupSession;
import com.imService.session.Session;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 * Created by a on 2014/9/4.
 */
public abstract class Client {

    private ImConnection imConnection;
    private ClientLoginer clientLoginer;
    public Client(ImConnection imConnection) {
        this.imConnection = imConnection;
    }

    protected void loginClient(String account,String pwd) throws XMPPException {
        if(this.imConnection !=null){
            this.imConnection.connection();
            this.imConnection.login(account,pwd);
            clientLoginer = new ClientLoginer(account,pwd);
            clientLoginer.setPresence(imConnection.getContactPresence(account));
        }
    }

    protected void closeClient(){
        if(this.imConnection !=null){
            this.imConnection.close();
            clientLoginer = null;
        }
    }

    public void loginOut(){
        if(this.imConnection !=null){
            this.imConnection.close();
        }
    }

    public ClientLoginer getClientLoginer() {
        return clientLoginer;
    }

    public ImConnection getImConnection() {
        return imConnection;
    }

    public Session getSession(String jid,Message.Type type){
        Session session = null;
        if(type.equals(Message.Type.chat)){
            session = new ChatSession(imConnection,jid);
        }else if(type.equals(Message.Type.groupchat)){
            session = new GroupSession(imConnection,jid,clientLoginer.getAccount());
        }
        return session;
    }

    public String getLoginAccount(){
        return clientLoginer.getAccount();
    }
}
