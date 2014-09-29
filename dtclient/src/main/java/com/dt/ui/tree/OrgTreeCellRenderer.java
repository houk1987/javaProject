package com.dt.ui.tree;




import com.dt.vo.FriendRooms;
import com.dt.vo.Unit;
import com.dt.vo.UserInfo;
import org.jivesoftware.smack.packet.Presence;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * 组织机构树的外观
 * Created by a on 2014/7/9.
 */
public class OrgTreeCellRenderer extends  DefaultTreeCellRenderer {
   private ImageIcon onLineStatus;
   private ImageIcon offLineStatus;
    public OrgTreeCellRenderer() {
        this.setFont(new Font("宋体", Font.PLAIN, 12));
        setOpenIcon(new ImageIcon("res/main/tree/close.png"));
        setClosedIcon(new ImageIcon("res/main/tree/open.png"));
        onLineStatus = new ImageIcon("res/status/online.png");
        offLineStatus = new ImageIcon("res/status/offline.png");
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object obj = (node).getUserObject();
            if(leaf && !node.isRoot())setIcon(null);
            if (obj instanceof Unit) {
                Unit unit = (Unit) obj;
                setText(unit.getUnitName());
            }
            if (obj instanceof UserInfo) {
                UserInfo user = (UserInfo) obj;
                setText(user.getUsername()+"("+user.getId()+")");
                if(user.getPresence().getType().equals(Presence.Type.unavailable) || user.getPresence().getType().equals(Presence.Type.error)){
                    setIcon(offLineStatus);
                }else if(user.getPresence().getType().equals(Presence.Type.available)){
                    setIcon(onLineStatus);
                }
            }
            if(obj instanceof FriendRooms){
                FriendRooms friendRooms = (FriendRooms)obj;
                setText(friendRooms.getName());
            }
        return this;
    }
}
