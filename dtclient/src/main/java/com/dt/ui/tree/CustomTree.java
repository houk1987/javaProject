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
    public  DefaultMutableTreeNode root = new DefaultMutableTreeNode("联系人");
    private TreeHandel treeHandel;



    /**
     * 构造
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
        //在选中位置插入新节点
        treeModel.insertNodeInto(newNode, root, root.getChildCount());
        //--------下面代码实现显示新节点（自动展开父节点）-------
        //获取从根节点到新节点的所有节点
        TreeNode[] nodes = treeModel.getPathToRoot(newNode);
        //使用指定的节点数组来创建TreePath
        TreePath path = new TreePath(nodes);
        //显示指定TreePath
        scrollPathToVisible(path);
    }

    // 展开树的所有节点的方法
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
