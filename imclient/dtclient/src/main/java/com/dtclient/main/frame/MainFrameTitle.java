package com.dtclient.main.frame;

import com.dtclient.sys.SysProperties;
import com.ui.frame.Title;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/11.
 */
public class MainFrameTitle implements Title {

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
        return "即时通讯";
    }

    @Override
    public Font getFont() {
        return new Font("宋体",Font.PLAIN,12);
    }

    @Override
    public Color getColor() {
        return Color.BLACK;
    }
}
