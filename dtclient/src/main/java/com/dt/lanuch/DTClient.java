package com.dt.lanuch;

import com.dt.listener.DtPacketListener;
import com.dt.listener.DtRosterListener;
import com.dt.manager.SynDataManager;
import com.dt.sys.SysProperties;
import com.dt.ui.main.MainFrame;
import com.dt.vo.FriendRooms;
import com.dt.vo.UserInfo;
import com.google.gson.Gson;
import com.imService.ConferenceRoom.ConferenceRoomManager;
import com.imService.client.Client;
import com.imService.presence.Away;
import com.imService.presence.OffLine;
import com.imService.presence.OnLine;
import com.imService.presence.PresenceType;
import com.pubTools.toos.FileTools;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2014/9/17.
 */
public class DTClient extends Client {

    private  static  DTClient dtClient = new DTClient();

    public static DTClient getInstance() {
        return dtClient;
    }

    private DTClient() {
        initXMPPConnection(SysProperties.getHost(), SysProperties.getPort(),SysProperties.domain());
    }

    @Override
    public void loginClient(String account, String password) throws NullPointerException, XMPPException {
        super.loginClient(account, password);
        SysProperties.setUser(account);
        showMainFrame();
        addPacketListener(new DtPacketListener());
    }

    public void showMainFrame(){
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public void loginOut() throws NullPointerException {
        super.loginOut();
    }

    @Override
    public List<PresenceType> getPresenceTypes() {
        presenceTypes = new ArrayList<>();
        presenceTypes.add(new OnLine("在线",new ImageIcon("res/status/online.png")));
        presenceTypes.add(new Away("离开", new ImageIcon("res/status/away.png")));
        presenceTypes.add(new OffLine("离线",new ImageIcon("res/status/offline.png")));
        return presenceTypes;
    }

    @Override
    public void addRosterListener(RosterListener rosterListener) throws NullPointerException {
        super.addRosterListener(new DtRosterListener());
    }


    /**
     *
     * @param groupName 群名称
     * @param membersList 群成员
     * @param isSaveLocal 是否保存在本地
     * @return FriendRooms 群节点对象
     * @throws XMPPException
     */
    public FriendRooms createNewGroup(String groupName,List<UserInfo> membersList,boolean isSaveLocal)throws XMPPException{
        try {
            /*
            * 在服务器上创建房间
            * */
            List<String> membersNamesList = new ArrayList<>(); //成员名称
            for (UserInfo user : membersList){
                membersNamesList.add(user.getUsername());
                user.setUnitId(groupName);
                user.setUnitName(groupName);
            }
            ConferenceRoomManager.createConferenceRoom(this.xmppConnection,getLander().getAccount()+groupName,membersNamesList); //在服务器上创建

            //是否保存在本地
            if(isSaveLocal) {
                /**
                 * 以json 字符串的方式保存到本地
                 */
                Gson gson = new Gson();
                String saveUserContent = gson.toJson(membersList);
                FileTools.writeFile(new File("user/" + DTClient.getInstance().getLander().getAccount() + ".txt"), saveUserContent);
            }

            /**
             * 创建树节点对象
             */
            FriendRooms friendRooms = new FriendRooms();
            friendRooms.setJid(groupName);
            friendRooms.setName(groupName);
            friendRooms.setUserInfoList(membersList);
            return friendRooms;
        } catch (XMPPException e) {
            e.printStackTrace();
            throw new XMPPException("创建群组失败");
        }
    }

    public UserInfo getLanderUserInfo(){
        return SynDataManager.getInstance().getUserInfo(getLander().getAccount());
    }

    public UserInfo getUserInfo(String userId){
        return SynDataManager.getInstance().getUserInfo(userId);
    }
}
