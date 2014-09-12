package com.dtclient.main.tree;



import com.dtclient.main.MainFrame;
import com.dtclient.main.group.GroupDialog;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 自定义树的右键菜单
 * Created by a on 2014/7/10.
 */
public class CustomTreeRightMenu extends JPopupMenu implements ActionListener{

    private JMenuItem createCustomGroupItem;
    private JMenuItem modifyCustomGroupItem;
    private JMenuItem delCustomGroupItem;
    private DefaultMutableTreeNode node;
    public CustomTreeRightMenu(DefaultMutableTreeNode node) {
        super();
        this.node = node;
        this.initCustomTreeRightMenuUi();
    }

    /**
     * 初始化
     */
    private void initCustomTreeRightMenuUi(){
        if(node.isRoot()){
            createCustomGroupItem = new JMenuItem("添加自定义组...");
            createCustomGroupItem.addActionListener(this);
            this.add(createCustomGroupItem);
        }else{
            modifyCustomGroupItem = new JMenuItem("修改");
            delCustomGroupItem = new JMenuItem("删除");
            modifyCustomGroupItem.addActionListener(this);
            delCustomGroupItem.addActionListener(this);
            this.add(modifyCustomGroupItem);
            this.add(delCustomGroupItem);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createCustomGroupItem){
            GroupDialog groupDialog = new GroupDialog(MainFrame.getInstance());
            groupDialog.setVisible(true);
        }else if(e.getSource() == delCustomGroupItem){

        }else if(e.getSource() == modifyCustomGroupItem){
//            GroupDialog groupDialog = new GroupDialog((Group)node.getUserObject(),MainFrame.getMainFrame());
//            groupDialog.setVisible(true);
        }
    }
}
