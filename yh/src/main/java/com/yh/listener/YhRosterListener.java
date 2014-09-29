package com.yh.listener;


import com.yh.main.MainFrame;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.packet.Presence;

import java.util.Collection;

/**
 * Created by lenovo on 2014/9/16.
 */
public class YhRosterListener implements RosterListener {
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
        MainFrame.getInstance().refreshContactTreePresence(presence);
    }
}
