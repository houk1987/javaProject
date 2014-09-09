package com.dtclient.main.tree;



import com.talk.client.vo.Unit;
import com.talk.client.vo.UserInfo;
import com.talk.client.conferenceRoom.FriendRooms;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * 组织机构树的外观
 * Created by a on 2014/7/9.
 */
public class OrgTreeCellRenderer extends DefaultTreeCellRenderer {

    public OrgTreeCellRenderer() {
        this.setFont(new Font("宋体", Font.PLAIN, 12));
        setOpenIcon(new ImageIcon("res/tree/close.png"));
        setClosedIcon(new ImageIcon("res/tree/open.png"));
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object obj = (node).getUserObject();
            if(leaf && !node.isRoot())setIcon(null);
            if (obj instanceof Unit) {
                Unit unit = (Unit) obj;
                setText(unit.getUnitName());
            } else if (obj instanceof UserInfo) {
                UserInfo user = (UserInfo) obj;
                setText(user.getUsername()+"("+user.getId()+")");
                setIcon(UserHeadImageProvider.getHeadImageIcon(user.getSex()));
            }else if(obj instanceof FriendRooms){
                FriendRooms friendRooms = (FriendRooms)obj;
                setText(friendRooms.getName());
            }
        }
        return this;
    }
}
