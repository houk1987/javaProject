package com.imService.client;

import com.imService.presence.PresenceType;
import org.jivesoftware.smack.packet.Presence;

/**
 * Created by a on 2014/9/4.
 */
public class ClientLoginer {

    private String account;
    private String pwd;
    private Presence presence;

    public ClientLoginer(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }

    public String getAccount() {
        return account;
    }

    public String getPwd() {
        return pwd;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }
}
