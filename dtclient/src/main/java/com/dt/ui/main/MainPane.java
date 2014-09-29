package com.dt.ui.main;



import com.dt.handel.CustomTreeHandel;
import com.dt.handel.OrgTreeHandel;
import com.dt.lanuch.DTClient;
import com.dt.manager.SynDataManager;
import com.dt.ui.tree.CustomTree;
import com.dt.ui.tree.OrgTree;
import com.dt.vo.UserInfo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/16.
 */
public class MainPane extends JPanel {
    private JPanel centerPane;
    private JScrollPane jScrollPane;  //滚动面板
    private OrgTree orgTree;
    private CustomTree customTree;
    private int role = 0;
    public MainPane() {
        setLayout(new BorderLayout());
        add(new AccountInfoPane(), BorderLayout.NORTH);
        setBackground(new Color(235,239,244));
        centerPane = new JPanel(new BorderLayout());
        centerPane.setOpaque(false);
        add(centerPane, BorderLayout.CENTER);
        UserInfo userInfo = DTClient.getInstance().getLanderUserInfo();
        if(userInfo !=null){
            role = userInfo.getRoleId();
        }
        role =  userInfo.getRoleId();
        DtNavigationBar dtNavigationBar = new DtNavigationBar(this,role);
        centerPane.add(dtNavigationBar, BorderLayout.NORTH);
        initJScrollPane();
        initTree();
        if(role==3){
            switchOrgTree();
        }else {
            switchCustomTree();
        }
    }

    /**
     * 初始化滚动条面板
     */
    private void initJScrollPane(){
        this.jScrollPane = new JScrollPane();
        this.jScrollPane.setBorder(null);
        centerPane.add(jScrollPane, BorderLayout.CENTER);
    }

    /**
     * 初始化树
     */
    private void initTree(){
        customTree = new CustomTree(new CustomTreeHandel(role));
        orgTree = new OrgTree(new OrgTreeHandel());
    }

    public void switchOrgTree(){
        if(orgTree==null)return;
        jScrollPane.setViewportView(orgTree);
    }

    public void switchCustomTree(){
        if(orgTree==null)return;
        jScrollPane.setViewportView(customTree);
    }

    public OrgTree getOrgTree() {
        return orgTree;
    }

    public CustomTree getCustomTree() {
        return customTree;
    }
}
