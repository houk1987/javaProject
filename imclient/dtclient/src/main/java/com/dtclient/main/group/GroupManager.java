package com.dtclient.main.group;


import com.dtclient.manager.DtManager;
import com.dtclient.vo.FriendRooms;
import com.dtclient.vo.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imService.ConferenceRoom.ConferenceRoomManager;
import com.imService.connection.ImConnection;
import com.pubTools.toos.FileTools;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/7/24.
 */
public class GroupManager {

    public static FriendRooms createGroup(String groupName,List<UserInfo> memberList) throws XMPPException {
        //在openfire服务器上创建会议室
        ImConnection imConnection = DtManager.getInstance().getImConnection();
        MultiUserChat multiUserChat = ConferenceRoomManager.createConferenceRoom(imConnection, DtManager.getInstance().getLoginAccount()+groupName, coverToMemberNamesList(memberList));
        //刷新树节点
        FriendRooms friendRooms = new FriendRooms();
        friendRooms.setJid(DtManager.getInstance().getLoginAccount()+groupName + "@conference." + imConnection.getXMPPConnection().getServiceName());
        friendRooms.setName(groupName);
        friendRooms.setUserInfoList(memberList);
        return friendRooms;
    }

    public static void createCustomGroup(String groupName,List<UserInfo> memberList){
        try {
            FriendRooms friendRooms = createGroup(groupName,memberList);
            //保存本地的人员
            if(memberList==null)return;
            for(UserInfo user : memberList){
                user.setUnitId(friendRooms.getJid());
            }
            Gson gson = new Gson();
            String saveUserContent  = gson.toJson(memberList);
            FileTools.writeFile(new File("usr/" + DtManager.getInstance().getLoginAccount() + ".txt"), saveUserContent);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    private static List<String> coverToMemberNamesList(List<UserInfo> memberList){
        if(memberList == null)return null;
        List<String> list = new ArrayList<>();
        for (UserInfo user : memberList){
            list.add(user.getUsername());
        }
        return list;
    }

    public static List<UserInfo> getAllUserList() {
        List list = FileTools.readFileToList(new File("usr/" + DtManager.getInstance().getLoginAccount() + ".txt"));
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
}
