package com.dt.handel;

import com.dt.lanuch.DTClient;
import com.dt.manager.SynDataManager;
import com.dt.session.SessionFrame;
import com.dt.sys.SysProperties;
import com.dt.ui.tree.CustomTree;
import com.dt.vo.FriendRooms;
import com.dt.vo.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pubTools.toos.FileTools;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.*;

/**
 * Created by lenovo on 2014/9/16.
 */
public class CustomTreeHandel implements TreeHandel {

    private int role;
    private FriendRooms friendRooms;
    private List<DefaultMutableTreeNode> nodeList = new ArrayList<>();
    private CustomTree customTree;

    public CustomTreeHandel(int role) {
        this.role = role;
    }

    public CustomTreeHandel(FriendRooms friendRooms) {
        this.friendRooms = friendRooms;
    }

    @Override
    public void clickNode(DefaultMutableTreeNode node) {
        SessionFrame.openSessionFrame(node.getUserObject());
    }

    @Override
    public void loadTreeData(JTree jTree) {
        customTree = (CustomTree) jTree;
        /**
         * ��Ⱥ��ػ��򣬼���Ⱥ����Ա��
         */
        if (friendRooms != null) {
            loadGroup();
            return;
        }
        /**
         * ������ѵ��Ա��ϵ����
         */
        if (role == 2) {
            loadDtUserInfo();
        } else {
            loadDefaultGroup();
        }

        /**
         * ���ص�����Ա�Զ������ڵ�
         */
        if (role == 3) {
            loadCustomTreeData();
        }
    }

    /**
     * ����ѵ��Աʱ ֻ���ص���һ����
     */
    private void loadDtUserInfo() {
        for (UserInfo userInfo : SynDataManager.getInstance().synUsers()) {
            if (userInfo.getRoleId() == 3) {  //����һ��
                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userInfo);
                customTree.addNode(userNode);
            }
        }
    }

    /**
     * ������Ա  ������Ա ѵ������Ա
     * ����Ĭ����ʾ����
     */
    private void loadDefaultGroup() {
        try {
            List<UserInfo> defaultUserInfoList = new ArrayList<>();
            List<UserInfo> synUsers = SynDataManager.getInstance().synUsers();
            if (synUsers != null && synUsers.size() > 0) {
                for (UserInfo userInfo : synUsers) {
                    if (userInfo.getRoleId() != 2) {
                        defaultUserInfoList.add((UserInfo) userInfo.clone());
                    }
                }
            }
            FriendRooms friendRooms = DTClient.getInstance().createNewGroup("������", defaultUserInfoList, false);
            DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(friendRooms);
            customTree.addNode(groupNode);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    private void loadGroup() {
        if (friendRooms == null) return;
        for (UserInfo userInfo : friendRooms.getUserInfoList()) {
            DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userInfo);
            customTree.addNode(userNode);
        }
    }

    /**
     * �����������Զ�����
     *
     * @param
     */
    public void loadCustomTreeData() {
        try {
            List<UserInfo> userInfoList = getUserList();
            HashMap<String, List<UserInfo>> groupMap = new HashMap<>();
            if (userInfoList != null && userInfoList.size() > 0) {
                for (UserInfo userInfo : userInfoList) {

                    String key = userInfo.getUnitId() + "," + userInfo.getUnitName();
                    List<UserInfo> membersList = groupMap.get(key);
                    if (membersList == null) {
                        membersList = new ArrayList<>();
                        groupMap.put(key, membersList);
                    }
                    membersList.add(userInfo);
                }
            }

            Iterator iter = groupMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = String.valueOf(entry.getKey());
                FriendRooms friendRooms = new FriendRooms();
                friendRooms.setJid(key.split(",")[0]);
                friendRooms.setName(key.split(",")[1]);
                friendRooms.setUserInfoList((List<UserInfo>)entry.getValue());
                DefaultMutableTreeNode friendRoomsNode = new DefaultMutableTreeNode(friendRooms);
                customTree.addNode(friendRoomsNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<UserInfo> getUserList() {
        List list = FileTools.readFileToList(new File("user/" + SysProperties.getUser() + ".txt"));
        if (list != null && list.size() > 0) {
            List<UserInfo> userList = new ArrayList<>();
            Gson gson = new Gson();
            for (int i = 0; i < list.size(); i++) {
                Object ojb = list.get(i);
                try {
                    UserInfo user = gson.fromJson(ojb.toString(), UserInfo.class);
                    userList.add(user);
                } catch (Exception e) {
                    List<UserInfo> temp = gson.fromJson(ojb.toString(), new TypeToken<List<UserInfo>>() {
                    }.getType());
                    if (temp != null) {
                        userList.addAll(temp);
                    }
                }
            }
            return userList;
        }
        return null;
    }

    @Override
    public List<DefaultMutableTreeNode> getAllNode() {
        return nodeList;
    }
}
