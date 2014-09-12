package com.dtclient.session;

import com.dtclient.sys.SysProperties;
import com.ui.frame.Title;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/11.
 */
public class SessionTitle implements Title {

    private String chatName;
    public SessionTitle(String userName) {
        this.chatName = userName;
    }

    @Override
    public String getBtnImagePath() {
        return SysProperties.frameButtonPath();
    }

    @Override
    public ImageIcon getTitleIcon() {
        return null;
    }

    @Override
    public String getTxt() {
        return chatName;
    }

    @Override
    public Font getFont() {
        return new Font("ËÎÌו",Font.PLAIN,12);
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }
}
