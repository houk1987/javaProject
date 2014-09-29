package com.dt.listener;

import com.dt.ui.main.MainFrame;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;
import sun.applet.Main;

import java.util.Collection;

/**
 * Created by lenovo on 2014/9/16.
 */
public class DtRosterListener implements RosterListener {
    @Override
    public void entriesAdded(Collection<String> strings) {

    }

    @Override
    public void entriesUpdated(Collection<String> strings) {

    }

    @Override
    public void entriesDeleted(Collection<String> strings) {

    }

    @Override
    public void presenceChanged(Presence presence) {
        String from = presence.getFrom().split("@")[0];
        MainFrame.getInstance().getOrgTree().changeNodePresence(from,presence);
    }
}
