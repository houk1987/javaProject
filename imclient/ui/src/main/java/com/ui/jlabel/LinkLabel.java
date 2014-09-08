package com.ui.jlabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 356 on 14-8-31.
 */
public class LinkLabel extends JLabel {
    private String text, url;
    private boolean isSupported;
    private String color;
    public LinkLabel(String text, String color,String url) {
        this.text = text;
        this.url = url;
        this.color = color;
        try {
            this.isSupported = Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        setText(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setText(isSupported);
                if (isSupported)
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                setText(false);
            }
            public void mouseClicked(MouseEvent e) {
                try {
                    if(LinkLabel.this.url==null)return;
                    Desktop.getDesktop().browse(
                            new java.net.URI(LinkLabel.this.url));
                } catch (Exception ex) {
                }
            }
        });
    }
    private void setText(boolean b) {
        if (!b)
            setText("<html><font style=\"color:"+color+"\">" + text);
        else
            setText("<html><font style=\"color:"+color+"\"><u>" + text);
    }
    }

