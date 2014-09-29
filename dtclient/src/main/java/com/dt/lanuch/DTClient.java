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
        presenceTypes.add(new OnLine("����",new ImageIcon("res/status/online.png")));
        presenceTypes.add(new Away("�뿪", new ImageIcon("res/status/away.png")));
        presenceTypes.add(new OffLine("����",new ImageIcon("res/status/offline.png")));
        return presenceTypes;
    }

    @Override
    public void addRosterListener(RosterListener rosterListener) throws NullPointerException {
        super.addRosterListener(new DtRosterListener());
    }


    /**
     *
     * @param groupName Ⱥ����
     * @param membersList Ⱥ��Ա
     * @param isSaveLocal �Ƿ񱣴��ڱ���
     * @return FriendRooms Ⱥ�ڵ����
     * @throws XMPPException
     */
    public FriendRooms createNewGroup(String groupName,List<UserInfo> membersList,boolean isSaveLocal)throws XMPPException{
        try {
            /*
            * �ڷ������ϴ�������
            * */
            List<String> membersNamesList = new ArrayList<>(); //��Ա����
            for (UserInfo user : membersList){
                membersNamesList.add(user.getUsername());
                user.setUnitId(groupName);
                user.setUnitName(groupName);
            }
            ConferenceRoomManager.createConferenceRoom(this.xmppConnection,getLander().getAccount()+groupName,membersNamesList); //�ڷ������ϴ���

            //�Ƿ񱣴��ڱ���
            if(isSaveLocal) {
                /**
                 * ��json �ַ����ķ�ʽ���浽����
                 */
                Gson gson = new Gson();
                String saveUserContent = gson.toJson(membersList);
                FileTools.writeFile(new File("user/" + DTClient.getInstance().getLander().getAccount() + ".txt"), saveUserContent);
            }

            /**
             * �������ڵ����
             */
            FriendRooms friendRooms = new FriendRooms();
            friendRooms.setJid(groupName);
            friendRooms.setName(groupName);
            friendRooms.setUserInfoList(membersList);
            return friendRooms;
        } catch (XMPPException e) {
            e.printStackTrace();
            throw new XMPPException("����Ⱥ��ʧ��");
        }
    }

    public UserInfo getLanderUserInfo(){
        return SynDataManager.getInstance().getUserInfo(getLander().getAccount());
    }

    public UserInfo getUserInfo(String userId){
        return SynDataManager.getInstance().getUserInfo(userId);
    }
}
