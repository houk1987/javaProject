package com.imService.contact;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.RosterEntry;

import java.util.List;

/**
 * Created by a on 2014/9/5.
 */
public abstract class ContactManager {

    private ImConnection imConnection;
    protected List<RosterEntry> rosterEntryList;
    public ContactManager(ImConnection imConnection) {
        this.imConnection = imConnection;
        rosterEntryList = imConnection.getAllEntries();
    }

    public abstract List<Contact> getAllContact();
}
