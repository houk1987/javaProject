package com.imService.contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/9/5.
 * Èº×é
 */
public abstract class Group {
    private String groupId;
    private String groupName;
    private List<Contact> groupMembers = new ArrayList<>();

    protected Group(String groupName) {
        this.groupName = groupName;
        loadMembers();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Contact> getGroupMembers() {
        return groupMembers;
    }

    public int getMembersNum(){
        return groupMembers.size();
    }

    protected abstract void loadMembers();
}
