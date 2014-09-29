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
     * ���쵼��������
     */
    private MainFrame(){
        setTitle("�ƻ��⵼����ʱͨѶ"); //���ô��ڱ�������
        setSize(278,753);//���ô�С
        setIconImage(null);
        setContentPane(mainPane);  //�����������������
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
