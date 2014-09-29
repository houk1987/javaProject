package com.dt.session;



import com.dt.lanuch.DTClient;
import com.dt.session.messageHandel.ChatMessageContentHtml;
import com.dt.sys.SysProperties;
import com.dt.vo.FriendRooms;
import com.dt.vo.UserInfo;
import com.imService.message.SessionMessage;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import java.awt.*;

import java.sql.Timestamp;
import java.util.HashMap;


/**
 * Created by a on 2014/7/21.
 */
public class SessionFrame extends JFrame{

    public static HashMap<String,SessionFrame> hashMap = new HashMap<>();
    private JPanel contentPane;
    UserInfo userInfo;
    FriendRooms friendRooms;
    String jid;
    Message.Type type;

    private SessionFrame(UserInfo userInfo) {
       this(userInfo.getUsername());
       this.userInfo = userInfo;
       this.jid = userInfo.getId();
       type = Message.Type.chat;
       contentPane = new ChatSessionPane(this);
       add(contentPane, BorderLayout.CENTER);
    }

    private SessionFrame(FriendRooms friendRooms) {
        this(friendRooms.getName());
        this.friendRooms = friendRooms;
        this.jid = friendRooms.getJid();
        type = Message.Type.groupchat;
        contentPane = new GroupSessionPane(this);
        add(contentPane, BorderLayout.CENTER);
    }

    private SessionFrame(String titleTxt){
        super();
        setTitle(titleTxt);
        setSize(580, 500);
    }

    public static SessionFrame openSessionFrame(Object obj){
        if(obj!= null && obj instanceof UserInfo){
           return SessionFrame.openChatSessionFrame((UserInfo)obj);//打开聊天界面
        }else if(obj!= null && obj instanceof FriendRooms){
            return openGroupSessionFrame((FriendRooms)obj);
        }
        return null;
    }


    public static SessionFrame openChatSessionFrame(UserInfo userInfo){
        SessionFrame sessionFrame = hashMap.get(userInfo.getId());
        if(sessionFrame == null){
            sessionFrame = new SessionFrame(userInfo);
            hashMap.put(userInfo.getId(),sessionFrame);
        }
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    public static SessionFrame openGroupSessionFrame(FriendRooms friendRooms){
        SessionFrame sessionFrame = hashMap.get(friendRooms.getJid());
        if(sessionFrame == null){
            sessionFrame = new SessionFrame(friendRooms);
            hashMap.put(friendRooms.getJid(),sessionFrame);
        }
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    public void insertMessageToDisplay(SessionMessage sessionMessage){
       String html = new ChatMessageContentHtml(sessionMessage).getContentHtml();
        if(contentPane instanceof ChatSessionPane){
            ((ChatSessionPane)contentPane).sessionDisplayPane.insertMessageHtml(html);
        }else if(contentPane instanceof GroupSessionPane){
            ((GroupSessionPane)contentPane).sessionDisplayPane.insertMessageHtml(html);
        }
    }

    public void sendMessage(SessionMessage sessionMessage){
        sessionMessage.setTo(jid);
        sessionMessage.setFrom(SysProperties.getUser());
        sessionMessage.setSendTime(new Timestamp(System.currentTimeMillis()));
        Message message = new Message();
        message.setFrom(sessionMessage.getFrom());
        message.setTo(sessionMessage.getTo());
        message.setBody(sessionMessage.getContent());
        message.setProperty("sendTime",sessionMessage.getSendTime());
        if(type.equals(Message.Type.chat)){
            DTClient.getInstance().getChatSession(jid).sendMessage(message);
        }else if(type.equals(Message.Type.groupchat)){
            DTClient.getInstance().getGroupSession(jid,DTClient.getInstance().getLander().getAccount()).sendMessage(message);
        }
    }
}
