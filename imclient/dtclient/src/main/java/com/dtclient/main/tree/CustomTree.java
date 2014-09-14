package com.dtclient.main.tree;


import com.dtclient.manager.DtManager;
import com.dtclient.main.frame.MainFrame;
import com.dtclient.main.group.GroupManager;
import com.dtclient.session.SessionFrame;
import com.dtclient.vo.FriendRooms;
import com.dtclient.vo.UserInfo;
import com.ui.tree.PubTree;
import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;


/**
 * CustomTree
 * Created by a on 2014/7/10.
 */
public class CustomTree extends PubTree implements MouseListener {
    private CustomTreeRightMenu customTreeRightMenu;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("自定义组");

    /**
     * 构造
     */
    public CustomTree() {
        super();
        treeModel = (DefaultTreeModel) this.getModel();
        treeModel.setRoot(root);
        this.setCellRenderer(new OrgTreeCellRenderer());
        this.addMouseListener(this);
    }


    /**
     * 在受训人员时 只加载导调一个人
     */
    public void loadDtUserInfo() {
        for (UserInfo userInfo : DtManager.getInstance().getUserInfoList()) {
            if (userInfo.getRoleId() == 3) {  //导演一人
                DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userInfo);
                root.add(userNode);
            }
        }
    }

    /**
     * 导调人员  评估人员 训练管理员
     * 加载默认显示的组
     */
    public void loadDefaultGroup() {
        try {
            List<UserInfo> defaultUserInfoList = new ArrayList<>();
            for (UserInfo userInfo : DtManager.getInstance().getUserInfoList()) {
                if (userInfo.getRoleId() != 2) {
                    defaultUserInfoList.add(userInfo);
                }
            }
            FriendRooms friendRooms = GroupManager.createGroup("默认组",defaultUserInfoList);
            DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(friendRooms);
            root.add(userNode);
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导演新增的自定义组
     *
     * @param role
     */
    public void loadCustomTreeData(int role) {
//        try {
//        HashMap<FriendRooms,List<UserInfo>> allGroupInfo = GroupManager.getAllUserList();
//            if(allGroupInfo !=null){
//                Iterator iter = allGroupInfo.entrySet().iterator();
//                while (iter.hasNext()) {
//                    Map.Entry entry = (Map.Entry) iter.next();
//                    FriendRooms friendRooms = (FriendRooms)entry.getKey();
//                    List<UserInfo> userInfoList = (List<UserInfo>)entry.getValue();
//                    DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode(friendRooms);
//                    root.add(roomNode);
//                    if(userInfoList!=null&&userInfoList.size()>0) {
//                        for (UserInfo userInfo : userInfoList) {
//                            DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userInfo);
//                            roomNode.add(userNode);
//                        }
//                    }
//                 }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public void loadCustomTreeData(List<UserInfo> userInfos){
        for(UserInfo userInfo : userInfos){
            DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userInfo);
            root.add(userNode);
        }
    }



    public void addNode(DefaultMutableTreeNode node) {
        root.add(node);
        this.removeAll();
        treeModel.reload();
    }

    public void setRootNodeName(Object o) {
        root.setUserObject(o);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = this.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            Object component = path.getLastPathComponent();
            if (component instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) component;
                if (e.getButton() == 3 && this.isPathSelected(path)) {
                    if(DtManager.getInstance().getUserInfo().getRoleId() == 3){
                        this.customTreeRightMenu = new CustomTreeRightMenu(node);
                        this.customTreeRightMenu.show(this, e.getX() + 5, e.getY() + 5);
                    }
                } else if (e.getButton() == 1) {
                    this.setSelectionPath(path);
                    if (e.getClickCount() == 2) {
                        try {
                            DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                            Object obj = selectNode.getUserObject();
                            if (obj instanceof UserInfo) {
                                UserInfo userInfo = (UserInfo) obj;
                                SessionFrame.openChatSessionFrame(userInfo);
                            } else if (obj instanceof FriendRooms) {
                                FriendRooms friendRooms = (FriendRooms) obj;
                                SessionFrame.openGroupSessionFrame(friendRooms);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(MainFrame.getInstance(), "打开会话窗口失败!", "提示", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
