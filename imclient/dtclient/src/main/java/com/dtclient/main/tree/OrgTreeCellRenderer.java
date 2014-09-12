package com.dtclient.main.tree;




import com.dtclient.vo.FriendRooms;
import com.dtclient.vo.Unit;
import com.dtclient.vo.UserInfo;

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
        setOpenIcon(new ImageIcon("res/main/tree/close.png"));
        setClosedIcon(new ImageIcon("res/main/tree/open.png"));
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
