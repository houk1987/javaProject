package com.dtclient.main.group;

import com.dtclient.sys.SysProperties;
import com.ui.frame.Title;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/12.
 */
public class GroupDialogTitle implements Title{
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
        return "自定义群组";
    }

    @Override
    public Font getFont() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }
}
