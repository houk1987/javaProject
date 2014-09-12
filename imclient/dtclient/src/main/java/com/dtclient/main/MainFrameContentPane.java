package com.dtclient.main;

import com.dtclient.lanuch.DtClient;
import com.dtclient.main.tree.CustomTree;
import com.dtclient.main.tree.OrgTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HK on 2014/9/9.
 */
public class MainFrameContentPane extends JPanel {
    private JScrollPane jScrollPane;  //滚动面板
     OrgTree orgTree;  //组织机构树
     CustomTree customTree; //自定义树

    /**
     *
     */
    public MainFrameContentPane() {
        setOpaque(false);
        setLayout(new BorderLayout());
        add(new AccountInfoPane(), BorderLayout.NORTH);
        JPanel centerPane = new JPanel(new BorderLayout());
        int role = DtClient.getInstance().getUserInfo().getRoleId();
        DtNavigationBar dtNavigationBar = new DtNavigationBar(this,role);
        centerPane.add(dtNavigationBar, BorderLayout.NORTH);
        this.jScrollPane = new JScrollPane();
        this.jScrollPane.setBorder(null);
        centerPane.add(jScrollPane, BorderLayout.CENTER);
        add(centerPane, BorderLayout.CENTER);
        switch (role) {
            case 1:  //训练管理人员
                customTree = new CustomTree();
                customTree.loadDefaultGroup();
                changeView(customTree);
                break;
            case 2: //受训人员
                customTree = new CustomTree();
                customTree.loadDtUserInfo();
                changeView(customTree);
                break;
            case 3:  //导调人员
                customTree = new CustomTree();
                customTree.loadDefaultGroup();
                orgTree = new OrgTree();
                orgTree.loadData();
                changeView(customTree);
                break;
            case 4: //评估人员
                customTree = new CustomTree();
                customTree.loadDefaultGroup();
                changeView(customTree);
                break;
        }
    }

    public void changeView(JComponent jComponent) {
        jScrollPane.setViewportView(jComponent);
    }

    public CustomTree getCustomTree() {
        return customTree;
    }
}
