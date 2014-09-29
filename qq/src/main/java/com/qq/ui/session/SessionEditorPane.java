package com.qq.ui.session;



import com.imService.message.SessionMessage;
import com.qq.sys.SysProperties;
import com.ui.button.ImageButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SessionEditorPane extends JPanel{
    private static final long serialVersionUID = 1L;
    private ChatWritePanel richEditor;//±à¼­Æ÷
    public SessionEditorPane(SessionFrame sessionFrame) {
        setBorder(null);
        this.setPreferredSize(new Dimension(0,150));
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        richEditor = new ChatWritePanel(this);
        this.add(richEditor, BorderLayout.CENTER);
    }
    public ChatWritePanel getRichEditor() {
        return richEditor;
    }
}

