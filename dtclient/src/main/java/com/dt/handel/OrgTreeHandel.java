package com.dt.handel;

import com.dt.manager.SynDataManager;
import com.dt.session.SessionFrame;
import com.dt.vo.Unit;
import com.dt.vo.UserInfo;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2014/9/16.
 */
public class OrgTreeHandel implements TreeHandel {

    private List<DefaultMutableTreeNode> nodeList = new ArrayList<>();

    @Override
    public void clickNode(DefaultMutableTreeNode node) {
        SessionFrame.openSessionFrame(node.getUserObject());
    }

    @Override
    public void loadTreeData(JTree jTree) {
        final List<UserInfo> userList =  SynDataManager.getInstance().synUsers();
        final List<Unit> unitList =  SynDataManager.getInstance().synUnits();
        DefaultTreeModel treeModel = (DefaultTreeModel)jTree.getModel();
        if (unitList != null && unitList.size() > 0) {
            for (Unit unit : unitList) {
                DefaultMutableTreeNode unitNode = new DefaultMutableTreeNode(unit);
                if (unit.getParentUnitId() == null || "".equals(unit.getParentUnitId())) { //û�и��ڵ㲿�ţ�����Ϊ�����ڵ�
                    treeModel.setRoot(unitNode);
                }
                loadChildUnitNode(unit,unitNode,unitList,userList); //���ظĲ����µ��ӽڵ�
                loadUnitUserNode(userList,unit,unitNode); //���ظò����µ���Ա�ڵ�
            }
        }
    }

    private void loadUnitUserNode(List<UserInfo> userList, Unit unit, DefaultMutableTreeNode unitNode) {
        if (userList != null && userList.size() > 0) {
            for (UserInfo user : userList) {
                if (unit.getUnitId().equals(user.getUnitId())) {
                    DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(user);
                    unitNode.add(userNode);
                    nodeList.add(userNode);
                }
            }
        }
    }

    private void loadChildUnitNode(Unit parentUnit,DefaultMutableTreeNode parentNode,List<Unit> unitList,List<UserInfo> userList) {
        if (unitList != null && unitList.size() > 0) {
            for (Unit unit : unitList) {
                if(parentUnit.getUnitId().equals(unit.getParentUnitId())){
                    DefaultMutableTreeNode childUnitNode = new DefaultMutableTreeNode(unit);
                    loadChildUnitNode(unit,childUnitNode,unitList,userList);//�������ظ��ӽڵ��µĽڵ�
                    loadUnitUserNode(userList,unit,childUnitNode); //���ظò����µ���Ա�ڵ�
                    parentNode.add(childUnitNode); //��ӵ����ڵ���
                    nodeList.add(childUnitNode);
                }
            }
        }
    }

    @Override
    public List<DefaultMutableTreeNode> getAllNode() {
        return nodeList;
    }
}
