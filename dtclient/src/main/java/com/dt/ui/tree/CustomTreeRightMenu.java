package com.dt.ui.tree;


import com.dt.ui.group.GroupDialog;
import com.dt.ui.main.MainFrame;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * �Զ��������Ҽ��˵�
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
     * ��ʼ��
     */
    private void initCustomTreeRightMenuUi(){
        if(node.isRoot()){
            createCustomGroupItem = new JMenuItem("����Զ�����...");
            createCustomGroupItem.addActionListener(this);
            this.add(createCustomGroupItem);
        }else{
            modifyCustomGroupItem = new JMenuItem("�޸�");
            delCustomGroupItem = new JMenuItem("ɾ��");
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
