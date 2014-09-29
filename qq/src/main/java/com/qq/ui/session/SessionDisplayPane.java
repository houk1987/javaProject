package com.qq.ui.session;



import javax.swing.*;
import java.awt.*;

/**
 * 消息信息面板
 * Created by a on 2014/7/14.
 */
 class SessionDisplayPane extends JSplitPane {
    private ChatConversationPanel chatConversationPanel;

    SessionDisplayPane(){
        super(JSplitPane.VERTICAL_SPLIT);
        setBorder(null);
        setResizeWeight(1);
        setDividerSize(1);
        setEnabled(false); //设置分割线不能移动
       chatConversationPanel = new ChatConversationPanel(this);
       add(chatConversationPanel, JSplitPane.TOP);
        setSize(440,290);
    }

    public void insertMessageHtml(String html){
        chatConversationPanel.insertMessage(html);
    }


}
