package com.dt.handel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

/**
 * Created by lenovo on 2014/9/16.
 */
public interface TreeHandel {

    void clickNode(DefaultMutableTreeNode node);

    void loadTreeData(JTree jTree);

    List<DefaultMutableTreeNode> getAllNode();
}
