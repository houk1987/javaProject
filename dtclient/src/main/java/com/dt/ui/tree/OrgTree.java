package com.dt.ui.tree;

import com.dt.handel.TreeHandel;
import com.dt.vo.UserInfo;
import com.ui.tree.PubTree;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;

public class OrgTree extends PubTree {
    private TreeHandel treeHandel;

    public OrgTree(final TreeHandel treeHandel) {
        super();
        if (treeHandel == null) return;
        this.treeHandel = treeHandel;
        treeHandel.loadTreeData(this);
        setCellRenderer(new OrgTreeCellRenderer());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath path = getPathForLocation(e.getX(), e.getY());
                if (null != path) {
                    if (e.getButton() == 1 && e.getClickCount() == 2) {
                        Object object = path.getLastPathComponent();
                        if (object instanceof DefaultMutableTreeNode) {
                            DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) object;
                            treeHandel.clickNode(selectNode);
                        }
                    }
                }
            }
        });
    }

    public void changeNodePresence(final String jid, final Presence presence) {
        List<DefaultMutableTreeNode> nodeList = treeHandel.getAllNode();

        for (DefaultMutableTreeNode node : nodeList) {
            Object obj = node.getUserObject();
            if (obj instanceof UserInfo) {
                UserInfo userInfo = (UserInfo) obj;
                if (userInfo != null && userInfo.getId().equals(jid)) {
                    userInfo.setPresence(presence);
                    node.setUserObject(userInfo);
                    validate();
                    repaint();
                }
            }
        }
    }
}
