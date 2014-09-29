package com.imService.contact;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lenovo on 2014/9/19.
 */
public class RosterManager {

    /**
     * 返回所有用户信息 <RosterEntry>
     *
     * @return List(RosterEntry)
     */
    public static List<RosterEntry> getAllEntries(Roster roster) {
        List<RosterEntry> EntriesList = new ArrayList<RosterEntry>();
        Collection<RosterEntry> rosterEntry = roster.getEntries();
        Iterator<RosterEntry> i = rosterEntry.iterator();
        while (i.hasNext())
            EntriesList.add(i.next());
        return EntriesList;
    }


    /**
     * 返回所有组信息 <RosterGroup>
     *
     * @return List(RosterGroup)
     */
    public static List<RosterGroup> getGroups(Roster roster) {
        List<RosterGroup> groupsList = new ArrayList<>();
        Collection<RosterGroup> rosterGroup = roster.getGroups();
        Iterator<RosterGroup> i = rosterGroup.iterator();
        while (i.hasNext())
            groupsList.add(i.next());
        return groupsList;
    }

    /**
     * 返回相应(groupName)组里的所有用户<RosterEntry>
     *
     * @return List(RosterEntry)
     */
    public static List<RosterEntry> getEntriesByGroup(Roster roster,
                                                      String groupName) {
        List<RosterEntry> EntriesList = new ArrayList<>();
        RosterGroup rosterGroup = roster.getGroup(groupName);
        Collection<RosterEntry> rosterEntry = rosterGroup.getEntries();
        Iterator<RosterEntry> i = rosterEntry.iterator();
        while (i.hasNext())
            EntriesList.add(i.next());
        return EntriesList;
    }


}
