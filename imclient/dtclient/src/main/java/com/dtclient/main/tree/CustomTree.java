package com.dtclient.main.tree;



import com.talk.client.vo.UserInfo;
import com.talk.client.conferenceRoom.FriendRooms;
import com.talk.client.service.GroupService;
import pub.ui.PubTree;

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

    public void loadCustomTreeData() {
        try {
           HashMap<FriendRooms,List<UserInfo>> allGroupInfo = GroupService.getAllGroupInfo();
            if(allGroupInfo !=null){
                Iterator iter = allGroupInfo.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    FriendRooms friendRooms = (FriendRooms)entry.getKey();
                    List<UserInfo> userInfoList = (List<UserInfo>)entry.getValue();
                    DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode(friendRooms);
                    root.add(roomNode);
                    if(userInfoList!=null&&userInfoList.size()>0) {
                        for (UserInfo userInfo : userInfoList) {
                            DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(userInfo);
                            roomNode.add(userNode);
                        }
                    }
                 }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addNode(DefaultMutableTreeNode node){
        root.add(node);
        this.removeAll();
        treeModel.reload();
    }

    public void setRootNodeName(Object o){
        root.setUserObject(o);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TreePath path = this.getPathForLocation(e.getX(), e.getY());
        if (path != null) {
            Object component = path.getLastPathComponent();
            if (component instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) component;
                if(e.getButton()==3 && this.isPathSelected(path)){
                    this.customTreeRightMenu = new CustomTreeRightMenu(node);
                    this.customTreeRightMenu.show(this, e.getX() + 5, e.getY() + 5);
                }else if(e.getButton() == 1){
                    this.setSelectionPath(path);
                    if(e.getClickCount()==2){
                        try{

                        }catch (Exception e1){

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
