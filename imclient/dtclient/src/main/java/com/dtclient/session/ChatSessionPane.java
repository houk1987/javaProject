package com.dtclient.session;

import com.dtclient.vo.UserInfo;
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
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//设置分割线的方式为左右分割
        splitPane.setDividerSize(1);//设置分隔线宽度的大小，以pixel为计算单位。
        splitPane.setEnabled(false); //设置分割线不能移动
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
        panel.add(createJLabel("人员详细信息:",10,0,100,30));
        panel.add(createJLabel("编号:"+ StringTools.nullConverToString(user.getId()),10,30,100,30));
        panel.add(createJLabel("姓名:"+ StringTools.nullConverToString(user.getUsername()),10,60,100,30));
        //panel.add(createJLabel("性别:"+ StringTools.nullConverToString(Sex.valueOf(user.getSex()).getSexName()),10,90,100,30));
        panel.add(createJLabel("年龄:"+ StringTools.nullConverToString(user.getAge()),10,90,100,30));
        panel.add(createJLabel("出生年月:"+ StringTools.nullConverToString(user.getBirthday()),10,120,100,30));
        panel.add(createJLabel("军衔:"+ StringTools.nullConverToString(String.valueOf(user.getRank())),10,150,100,30));
        panel.add(createJLabel("职位:"+ StringTools.nullConverToString(user.getWorkPosition()),10,180,100,30));
        panel.add(createJLabel("手机号码:"+ StringTools.nullConverToString(user.getCellPhone()),10,210,100,30));
        panel.add(createJLabel("办公号码:"+ StringTools.nullConverToString(user.getWorkPhone()),10,240,100,30));
        panel.add(createJLabel("角色:"+ StringTools.nullConverToString(user.getRoleName()),10,270,100,30));
        return panel;
    }

    private static JLabel createJLabel(String text,int x,int y,int width,int height){
        JLabel label = new JLabel(text);
        label.setBounds(x,y,width,height);
        return label;
    }
}
