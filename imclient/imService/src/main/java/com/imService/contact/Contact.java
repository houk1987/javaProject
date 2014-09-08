package com.imService.contact;

import com.imService.presence.PresenceType;

import javax.swing.*;

/**
 * Created by a on 2014/9/5.
 * ÁªÏµÈË
 */
public class Contact {
    protected String jid;
    protected String userName;
    protected ImageIcon HeadIcon;
    protected PresenceType presenceType;

    public Contact(){

    }

    protected Contact(String jid, String userName, ImageIcon headIcon) {
        this.jid = jid;
        this.userName = userName;
        HeadIcon = headIcon;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public ImageIcon getHeadIcon() {
        return HeadIcon;
    }

    public void setHeadIcon(ImageIcon headIcon) {
        HeadIcon = headIcon;
    }

    public PresenceType getPresenceType() {
        return presenceType;
    }

    public void setPresenceType(PresenceType presenceType) {
        this.presenceType = presenceType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
