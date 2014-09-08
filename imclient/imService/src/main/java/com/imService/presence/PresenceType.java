package com.imService.presence;

import javax.swing.*;

/**
 * Created by a on 2014/9/5.
 */
public abstract class PresenceType {

    protected ImageIcon presenceTypeIcon;
    protected String presenceName;
    public abstract  void changePresence(String jid);

    public ImageIcon getPresenceTypeIcon() {
        return presenceTypeIcon;
    }

    public String getPresenceName() {
        return presenceName;
    }
}
