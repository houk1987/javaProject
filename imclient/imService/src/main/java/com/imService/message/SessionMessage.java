package com.imService.message;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.packet.Time;

import java.sql.Timestamp;

/**
 * Created by a on 2014/9/4.
 */
public class SessionMessage {
    private String content;
    private FontStyle fontStyle;
    private String from;
    private String to;
    private Timestamp sendTime;
    public SessionMessage(String from,String to,String content, FontStyle fontStyle) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.fontStyle = fontStyle;
    }

    public SessionMessage(Message message){
       this.from = message.getFrom();
       this.to  = message.getTo();
       this.content = message.getBody();
       this.fontStyle =(FontStyle)message.getProperty("fontStyle");
       this.sendTime = (Timestamp)message.getProperty("sendTime");
    }



    public Message getMessage(){
        Message message = new Message();
        message.setBody(content);
        message.setTo(to);
        message.setFrom(from);
        message.setProperty("fontStyle",fontStyle);
        return message;
    }


    public String getContent() {
        return content;
    }

    public FontStyle getFontStyle() {
        return fontStyle;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }
}
