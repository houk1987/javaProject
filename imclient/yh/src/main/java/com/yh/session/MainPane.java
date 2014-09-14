package com.yh.session;

import com.imService.message.FontStyle;
import com.imService.message.SessionMessage;

import com.imService.session.Session;
import com.ui.jlabel.JLabelFactory;
import com.ui.session.ChatDisplayPane;
import com.ui.session.ChatWritePane;
import com.yh.button.CustomButtonFactory;
import com.yh.manager.LoginManager;
import com.yh.manager.YhManager;
import com.yh.session.message.ChatMessageContentHtml;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a on 2014/8/20.
 */
public class MainPane extends JPanel {

    private MenuPane _menuPanePane;
    private JSplitPane jSplitPane;
    ChatWritePane chatWritePane;
    ChatDisplayPane chatDisplayPane;
    private SessionFrame sessionFrame;
    private JLabel statusIcon;
    private JLabel chatLabel;
    public MainPane(SessionFrame sessionFrame) {
        this.sessionFrame = sessionFrame;
        initComponent();
        layoutComponent();
    }

    private void initComponent(){
        Presence type =YhManager.getInstance().getImConnection().getContactPresence(sessionFrame.getTo());
        if(type.isAvailable()){
            statusIcon = JLabelFactory.createJLabel(new ImageIcon("res/status/online.png"));
        }else{
            statusIcon = JLabelFactory.createJLabel(new ImageIcon("res/status/offline.png"));
        }

        chatLabel = new JLabel(sessionFrame.getTo());
        chatLabel.setForeground(Color.WHITE);
        String [] menuName = {"訊息(C)","編輯(E)","檢視(V)","功能(A)","說明(H)"};
        _menuPanePane = new MenuPane(menuName);
        jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setBorder(null);
        jSplitPane.setOneTouchExpandable(false);
        jSplitPane.setOpaque(false);
        jSplitPane.setResizeWeight(0.7D);
        jSplitPane.setDividerSize(2);//设置分隔条大小
        chatDisplayPane = new ChatDisplayPane();
        jSplitPane.add(chatDisplayPane,JSplitPane.TOP);
        JPanel panel = new JPanel(new BorderLayout());
        EditorbarPane editorbarPane = new EditorbarPane();
        panel.add(editorbarPane,BorderLayout.NORTH);

        SessionMessage sessionMessage = new SessionMessage(YhManager.getInstance().getLoginAccount(),"","",new FontStyle());
        chatWritePane = new ChatWritePane(sessionMessage);
        panel.add(chatWritePane,BorderLayout.CENTER);
        JPanel jPanel = new JPanel(new BorderLayout());

        jPanel.setBackground(Color.white);
        JButton jButton = CustomButtonFactory.createSendButton();
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionMessage sessionMessage = chatWritePane.getSessionMessage();
//                Session session = YhManager.getInstance().getSession(sessionFrame.getTo(),sessionFrame.getTypes());
//                session.sendMessage(sessionMessage);
//                String html = new ChatMessageContentHtml(sessionMessage).getContentHtml();
//                chatDisplayPane.insertMessage(html);
            }
        });
        jPanel.add(jButton,BorderLayout.CENTER);
        panel.add(jPanel,BorderLayout.EAST);
        jSplitPane.add(panel,JSplitPane.BOTTOM);
    }

    private void layoutComponent(){
        setLayout(new BorderLayout());
        setBackground(new Color(118,36,110));
        JPanel northPane = new JPanel(new GridLayout(3,0));

        northPane.setBackground(new Color(118,36,110));
        northPane.add(_menuPanePane);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        northPane.add(panel);
        JPanel statusPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPane.setOpaque(false);
        statusPane.add(statusIcon);
        statusPane.add(chatLabel);
        northPane.add(statusPane);
        add(northPane, BorderLayout.NORTH);
        add(jSplitPane,BorderLayout.CENTER);
    }



}
