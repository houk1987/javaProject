package com.dt.ui.tree;


import com.dt.handel.TreeHandel;
import com.dt.manager.SynDataManager;
import com.dt.session.SessionFrame;
import com.dt.vo.FriendRooms;
import com.dt.vo.UserInfo;
import com.ui.tree.PubTree;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import javax.swing.plaf.TreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.List;


/**
 * CustomTree
 * Created by a on 2014/7/10.
 */
public class CustomTree extends PubTree {

    private DefaultTreeModel treeModel;
    public  DefaultMutableTreeNode root = new DefaultMutableTreeNode("��ϵ��");
    private TreeHandel treeHandel;



    /**
     * ����
     */
    public CustomTree(final TreeHandel treeHandel) {
        this.setCellRenderer(new OrgTreeCellRenderer());
        treeModel = (DefaultTreeModel) this.getModel();
        treeModel.setRoot(root);
        this.treeHandel = treeHandel;
        this.treeHandel.loadTreeData(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath path = getPathForLocation(e.getX(), e.getY());
                if (null != path) {
                    Object object = path.getLastPathComponent();
                    if (object instanceof DefaultMutableTreeNode) {
                        DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) object;
                        if(e.getButton() == 3){
                            if (selectNode.isRoot()) {
                                CustomTreeRightMenu customTreeRightMenu = new CustomTreeRightMenu(selectNode);
                                customTreeRightMenu.show(CustomTree.this, e.getX() + 5, e.getY() + 5);
                            }
                        }else if(e.getButton()==1 && e.getClickCount()==2){
                            treeHandel.clickNode(selectNode);
                        }
                    }
                }
            }
        });
    }

    public void addNode(DefaultMutableTreeNode newNode) {
        //��ѡ��λ�ò����½ڵ�
        treeModel.insertNodeInto(newNode, root, root.getChildCount());
        //--------�������ʵ����ʾ�½ڵ㣨�Զ�չ�����ڵ㣩-------
        //��ȡ�Ӹ��ڵ㵽�½ڵ�����нڵ�
        TreeNode[] nodes = treeModel.getPathToRoot(newNode);
        //ʹ��ָ���Ľڵ�����������TreePath
        TreePath path = new TreePath(nodes);
        //��ʾָ��TreePath
        scrollPathToVisible(path);
    }

    // չ���������нڵ�ķ���
    public void expandAll(JTree tree, TreePath parent, boolean expand) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }
}
