package com.qq.ui.session;

import com.imService.message.SessionMessage;
import com.qq.lanuch.QQClient;
import com.qq.ui.pub.PubFrame;
import com.qq.ui.session.messageHandel.ChatMessageContentHtml;
import org.jivesoftware.smack.packet.Message;

import java.awt.*;
import java.sql.Timestamp;
import java.util.HashMap;

/**
 * Created by lenovo on 2014/9/17.
 */
public class SessionFrame extends PubFrame {
    private SessionPane sessionPane;
    private String jid;
    public static HashMap<String,SessionFrame> hashMap = new HashMap<>();

    private SessionFrame(String jid) throws HeadlessException {
        this.jid = jid;
        sessionPane = new SessionPane(this);
        setContentPane(sessionPane);    //设置内容面板
        setSize(sessionPane.getSize());//大小为内容面板大小
        setLocationRelativeTo(null); //居右显示

    }

    public static SessionFrame openChatSessionFrame(String jid){
        SessionFrame sessionFrame = hashMap.get(jid);
        if(sessionFrame == null){
            sessionFrame = new SessionFrame(jid);
            hashMap.put(jid,sessionFrame);
        }
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    public void sendMessage(SessionMessage sessionMessage){
        sessionMessage.setTo(jid);
        sessionMessage.setFrom(QQClient.getInstance().getLander().getAccount());
        sessionMessage.setSendTime(new Timestamp(System.currentTimeMillis()));
        Message message = new Message();
        message.setFrom(sessionMessage.getFrom());
        message.setTo(sessionMessage.getTo());
        message.setBody(sessionMessage.getContent());
        message.setProperty("sendTime",sessionMessage.getSendTime());
        QQClient.getInstance().getChatSession(jid).sendMessage(message);
    }

    public void insertMessageToDisplay(SessionMessage sessionMessage){
        String html = new ChatMessageContentHtml(sessionMessage).getContentHtml();
        sessionPane.sessionDisplayPane.insertMessageHtml(html);
    }
}
