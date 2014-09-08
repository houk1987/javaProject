package com.yh.session;

import com.imService.message.SessionMessage;
import com.yh.session.message.ChatMessageContentHtml;
import org.jivesoftware.smack.packet.Message;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by a on 2014/8/22.
 */
public class SessionFrame extends JFrame {
    private MainPane mainPane;
    private Message.Type type;
    private String to;
    private static final HashMap<String,SessionFrame> sessionFrameHashMap = new HashMap<>();

    /**
     *
     * @param to
     * @param type
     */
    public static SessionFrame CreateAndShowSessionFrame(String to,Message.Type type){
        SessionFrame sessionFrame = sessionFrameHashMap.get(to);
        if(sessionFrame == null){
            sessionFrame = new SessionFrame(to,type);
            sessionFrameHashMap.put(to,sessionFrame);
        }
        sessionFrame.setVisible(true);
        return sessionFrame;
    }

    private SessionFrame(String to,Message.Type type) throws HeadlessException {
        this.type = type;
        this.to = to;
        mainPane = new MainPane(this);
        setContentPane(mainPane);
        setSize(500, 500);
        setTitle(to);
    }

    public Message.Type getTypes() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public void insertMessageToDisplay(SessionMessage sessionMessage){
        String html = new ChatMessageContentHtml(sessionMessage).getContentHtml();
        System.out.println(html);
        mainPane.chatDisplayPane.insertMessage(html);
    }
}
