package com.imService.ConferenceRoom;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.muc.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by a on 2014/7/24.
 */
public class ConferenceRoomManager {

    public static MultiUserChat createConferenceRoom(ImConnection talkConnection, String roomName, List<String> memberList) throws XMPPException {
        try {
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            if (muc != null) {
                // 创建聊天室
                muc.join(talkConnection.getXMPPConnection().getUser());
//                muc.create(roomName); // roomName房间的名字
                // 获得聊天室的配置表单
                Form form = muc.getConfigurationForm();
                // 根据原始表单创建一个要提交的新表单。
                Form submitForm = form.createAnswerForm();
                // 向要提交的表单添加默认答复
                for (Iterator<FormField> fields = form.getFields(); fields
                        .hasNext(); ) {
                    FormField field = (FormField) fields.next();
                    if (!FormField.TYPE_HIDDEN.equals(field.getType())
                            && field.getVariable() != null) {
                        // 设置默认值作为答复
                        submitForm.setDefaultAnswer(field.getVariable());
                    }
                }
                // 设置聊天室的新拥有者
                List<String> owners = new ArrayList<String>();
                owners.add(talkConnection.getXMPPConnection().getUser());// 用户JID
                submitForm.setAnswer("muc#roomconfig_roomowners", owners);
                // 设置聊天室是持久聊天室，即将要被保存下来
                submitForm.setAnswer("muc#roomconfig_persistentroom", true);
                // 房间仅对成员开放
                submitForm.setAnswer("muc#roomconfig_membersonly", false);
                // 允许占有者邀请其他人
                submitForm.setAnswer("muc#roomconfig_allowinvites", true);
                // 登录房间对话
                submitForm.setAnswer("muc#roomconfig_enablelogging", true);
                // 仅允许注册的昵称登录
                submitForm.setAnswer("x-muc#roomconfig_reservednick", true);
                // 允许使用者修改昵称
                submitForm.setAnswer("x-muc#roomconfig_canchangenick", false);
                // 允许用户注册房间
                submitForm.setAnswer("x-muc#roomconfig_registration", false);
                // 发送已完成的表单（有默认值）到服务器来配置聊天室
                submitForm.setAnswer("muc#roomconfig_passwordprotectedroom", true);
                //添加群成员
                for (String tempMember : memberList) {
                    muc.grantMembership(tempMember);
                }
                // 发送已完成的表单（有默认值）到服务器来配置聊天室
                muc.sendConfigurationForm(submitForm);

            }
            return muc;
        } catch (XMPPException e) {
            e.printStackTrace();
            throw new XMPPException("");
        }
    }

    /**
     * 修改会议室
     * 当前该函数只能修改会议室名称和成员
     *
     * @param roomName
     * @param memberList
     */
    public static boolean modifyRoom(ImConnection talkConnection, String roomName, String newRoomName, List<String> memberList) {
        try {
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            if (muc != null) {
                muc.join(talkConnection.getXMPPConnection().getUser());
                // 获得聊天室的配置表单
                Form form = muc.getConfigurationForm();
                // 根据原始表单创建一个要提交的新表单。
                Form submitForm = form.createAnswerForm();
                submitForm.setAnswer("muc#roomconfig_roomname", newRoomName); //设置房间的新名称
                //form.setAnswer();
                //先删除所以的群成员
                List<Affiliate> delMembers = (List<Affiliate>)muc.getMembers();
                if(delMembers!=null && delMembers.size()>0){
                    for(Affiliate affiliate : delMembers){
                        muc.revokeMembership(affiliate.getJid());
                    }
                }

                if(memberList!= null && memberList.size()>0){
                    for (String member: memberList){
                        muc.grantMembership(member);
                    }
                }

                muc.sendConfigurationForm(submitForm);
            }
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除会议室
     *
     * @param roomName       会议室名称
     * @param talkConnection 服务器链接对象
     * @return true 删除成功  false 删除失败
     */
    public static boolean deleteRoom(String roomName, ImConnection talkConnection) {
        try {
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            if (muc != null) {
                muc.join(talkConnection.getXMPPConnection().getUser());
                muc.destroy("test", null);
                return true;
            }
        } catch (XMPPException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static MultiUserChat joinRoom(ImConnection talkConnection, String roomName, String user) {
        try {
            // 使用XMPPConnection创建一个MultiUserChat窗口
            MultiUserChat muc = getMultiUserChat(talkConnection, roomName);
            // 聊天室服务将会决定要接受的历史记录数量
            DiscussionHistory history = new DiscussionHistory();
            history.setMaxStanzas(0);
            // 用户加入聊天室
            muc.join(user);
            System.out.println("会议室加入成功........");
            return muc;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("会议室加入失败........");
            return null;
        }
    }

    /**
     * 获取服务器上所有会议室
     *
     * @return
     * @throws org.jivesoftware.smack.XMPPException
     */
    public static List<FriendRooms> getConferenceRoom(ImConnection talkConnection) throws XMPPException {
        if (talkConnection == null) return null;
        XMPPConnection connection = talkConnection.getXMPPConnection();
        if (connection == null) return null;
        List<FriendRooms> list = new ArrayList<FriendRooms>();
        new ServiceDiscoveryManager(connection);
        if (!MultiUserChat.getHostedRooms(connection,
                connection.getServiceName()).isEmpty()) {
            for (HostedRoom k : MultiUserChat.getHostedRooms(connection,
                    connection.getServiceName())) {

                for (HostedRoom j : MultiUserChat.getHostedRooms(connection,
                        k.getJid())) {
                    RoomInfo info2 = MultiUserChat.getRoomInfo(connection,
                            j.getJid());
                    if (j.getJid().indexOf("@") > 0) {
                        FriendRooms friendrooms = new FriendRooms();
                        friendrooms.setName(j.getName());//聊天室的名称
                        friendrooms.setJid(j.getJid());//聊天室JID
                        friendrooms.setOccupants(info2.getOccupantsCount());//聊天室中占有者数量
                        friendrooms.setDescription(info2.getDescription());//聊天室的描述
                        friendrooms.setSubject(info2.getSubject());//聊天室的主题
                        list.add(friendrooms);
                    }
                }
            }
        }
        return list;
    }

    private static MultiUserChat getMultiUserChat(ImConnection talkConnection, String roomName) throws XMPPException {
        if (talkConnection == null) return null;
        XMPPConnection connection = talkConnection.getXMPPConnection();
        if (connection == null) return null;
        MultiUserChat muc = new MultiUserChat(connection, roomName + "@conference." + connection.getServiceName());
        return muc;
    }
}
