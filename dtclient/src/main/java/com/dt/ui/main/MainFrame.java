package com.dt.ui.main;

import com.dt.ui.tree.CustomTree;
import com.dt.ui.tree.OrgTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2014/9/16.
 */
public class MainFrame extends JFrame {
    private static MainFrame mainFrame;
    private MainPane mainPane = new MainPane();

    public static MainFrame getInstance() {
        if(mainFrame ==null){
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }
    /**
     * 构造导调主界面
     */
    private MainFrame(){
        setTitle("计划外导调即时通讯"); //设置窗口标题文字
        setSize(278,753);//设置大小
        setIconImage(null);
        setContentPane(mainPane);  //设置主界面内容面板
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public OrgTree getOrgTree() {
        return mainPane.getOrgTree();
    }

    public CustomTree getCustomTree(){return mainPane.getCustomTree();}
}
