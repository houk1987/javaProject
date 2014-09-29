package com.imService.client;

import org.jivesoftware.smack.packet.Presence;

/**
 * 登陆客户端的用户
 * Created by lenovo on 2014/9/17.
 */
public class Lander {
    private String account;
    private Presence presence;

    public Lander(String account, Presence presence) {
        this.account = account;
        this.presence = presence;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }
}
