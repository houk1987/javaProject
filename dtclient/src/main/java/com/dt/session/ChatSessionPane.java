package com.dt.session;

import com.dt.vo.UserInfo;
import com.pubTools.toos.StringTools;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/11.
 */
public class ChatSessionPane extends JPanel {
    private JSplitPane splitPane;
    SessionDisplayPane sessionDisplayPane;
    private SessionFrame sessionFrame;

    ChatSessionPane(SessionFrame sessionFrame) {
        this.sessionFrame = sessionFrame;
        setLayout(new BorderLayout());
        initComponent();
    }

    private void initComponent() {
        splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//���÷ָ��ߵķ�ʽΪ���ҷָ�
        splitPane.setDividerSize(1);//���÷ָ��߿�ȵĴ�С����pixelΪ���㵥λ��
        splitPane.setEnabled(false); //���÷ָ��߲����ƶ�
        splitPane.setResizeWeight(0.1);
        splitPane.setOpaque(false);
        splitPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        sessionDisplayPane = new SessionDisplayPane(sessionFrame);
        splitPane.setLeftComponent(sessionDisplayPane);
        splitPane.setRightComponent(userInfoPane(sessionFrame.userInfo));
        this.add(splitPane, BorderLayout.CENTER);
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }


    private JPanel userInfoPane(UserInfo user){
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.white);
        panel.add(createJLabel("��Ա��ϸ��Ϣ:",10,0,100,30));
        panel.add(createJLabel("���:"+ StringTools.nullConverToString(user.getId()),10,30,100,30));
        panel.add(createJLabel("����:"+ StringTools.nullConverToString(user.getUsername()),10,60,100,30));
        //panel.add(createJLabel("�Ա�:"+ StringTools.nullConverToString(Sex.valueOf(user.getSex()).getSexName()),10,90,100,30));
        panel.add(createJLabel("����:"+ StringTools.nullConverToString(user.getAge()),10,90,100,30));
        panel.add(createJLabel("��������:"+ StringTools.nullConverToString(user.getBirthday()),10,120,100,30));
        panel.add(createJLabel("����:"+ StringTools.nullConverToString(String.valueOf(user.getRank())),10,150,100,30));
        panel.add(createJLabel("ְλ:"+ StringTools.nullConverToString(user.getWorkPosition()),10,180,100,30));
        panel.add(createJLabel("�ֻ�����:"+ StringTools.nullConverToString(user.getCellPhone()),10,210,100,30));
        panel.add(createJLabel("�칫����:"+ StringTools.nullConverToString(user.getWorkPhone()),10,240,100,30));
        panel.add(createJLabel("��ɫ:"+ StringTools.nullConverToString(user.getRoleName()),10,270,100,30));
        return panel;
    }

    private static JLabel createJLabel(String text,int x,int y,int width,int height){
        JLabel label = new JLabel(text);
        label.setBounds(x,y,width,height);
        return label;
    }
}
