package com.dtclient.session;



import javax.swing.*;
import java.awt.*;

/**
 * 消息信息面板
 * Created by a on 2014/7/14.
 */
 class SessionDisplayPane extends JSplitPane {
    private ChatConversationPanel chatConversationPanel;
    private SessionEditorPane sessionEditorPane;
    SessionDisplayPane(SessionFrame sessionFrame){
        super(JSplitPane.VERTICAL_SPLIT);
        this.setOpaque(false);
        setResizeWeight(1);
        setDividerSize(1);
        setEnabled(false); //设置分割线不能移动
        chatConversationPanel = new ChatConversationPanel(this);
        add(chatConversationPanel, JSplitPane.TOP);
        JPanel panel = new JPanel(new BorderLayout());
        add(panel,JSplitPane.BOTTOM);
        sessionEditorPane = new SessionEditorPane(sessionFrame);
        SessionEditorToolsBar sessionEditorToolsBar = new SessionEditorToolsBar(sessionEditorPane);
        panel.add(sessionEditorToolsBar,BorderLayout.NORTH);
        panel.add(sessionEditorPane,BorderLayout.CENTER);
    }

    public void insertMessageHtml(String html){
        chatConversationPanel.insertMessage(html);
    }
}
