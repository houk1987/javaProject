package com.qq.ui.session;

import com.imService.message.SessionMessage;
import com.qq.sys.SysProperties;
import com.qq.ui.tree.QQContactTree;
import com.ui.button.ImageButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lenovo on 2014/9/17.
 */
public class SessionPane extends JPanel implements ActionListener {
    private String skinPath = SysProperties.sessionFrameButtonPath();
    private ImageIcon bgIcon;  //背景图片
    SessionFrame sessionFrame;
    SessionDisplayPane sessionDisplayPane; //会话信息展示面板
    private SessionEditorPane sessionEditorPane; //会话消息编辑面板
    private JButton sendButton;
    private JButton closeButton;

    public SessionPane(SessionFrame sessionFrame) {
        bgIcon = new ImageIcon("res/session/sessionFrameBg.png"); //初始化背景图片
        if (bgIcon == null) return;
        setSize(bgIcon.getIconWidth(), bgIcon.getIconHeight()); //设置为背景图片大小
        this.sessionFrame = sessionFrame;
        initLayout();
    }

    private void initLayout() {
        setLayout(null);

        //会话信息显示面板
        sessionDisplayPane = new SessionDisplayPane();
        sessionDisplayPane.setLocation(3, 82);
        add(sessionDisplayPane);

        //会话编辑面板
        sessionEditorPane = new SessionEditorPane(sessionFrame);
        sessionEditorPane.setBounds(3, 400, 440, 70);
        sessionEditorPane.requestFocus();
        add(sessionEditorPane);

        closeButton = ImageButtonFactory.createButton(skinPath, "关闭", "close.png");
        closeButton.setLocation(274, 473);
        closeButton.addActionListener(this);
        add(closeButton);

        sendButton = ImageButtonFactory.createButton(skinPath, "发送信息", "send.png");
        sendButton.setLocation(348, 474);
        sendButton.addActionListener(this);
        add(sendButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgIcon == null) return;
        Image icon = bgIcon.getImage();
        g.drawImage(icon, 0, 0, icon.getWidth(this), icon.getHeight(this), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            try {
                SessionMessage sessionMessage = sessionEditorPane.getRichEditor().getSessionMessage();
                String content = sessionEditorPane.getRichEditor().getPlainText();
                if(content.length()>0){
                    sessionFrame.sendMessage(sessionMessage);
                    sessionEditorPane.getRichEditor().clear();//清空输入文本
                    sessionFrame.insertMessageToDisplay(sessionMessage);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == closeButton) {
            sessionFrame.dispose();
        }
    }
}
