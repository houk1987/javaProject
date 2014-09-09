package com.ui.tree;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTreeUI;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by a on 2014/7/11.
 */
public class PubTree extends JTree {

    public PubTree() {
        super();
        initTreeUI();
    }

    private void initTreeUI() {
        this.putClientProperty("JTree.lineStyle", "None");//去掉连线
        this.setToggleClickCount(1); // 点击一次展开节点
        this.setRowHeight(23);//设置行高
        this.setEditable(false); //不允许编辑
        this.setBackground(Color.white);//设置背景色为白色
        this.updateMetalTreelUI(this);//去除树上句柄和连接线
    }

    /**
     * ************************
     * 去除树上句柄和连接线
     *
     * @param tree *********************
     * @author houk
     */
    private void updateMetalTreelUI(final JTree tree) {

        final MetalTreeUI customUI = new MetalTreeUI() {
            @Override
            protected boolean shouldPaintExpandControl(TreePath path, int row, boolean isExpanded, boolean wasExpanded, boolean leaf) {
                return false;
            }

            @Override
            protected void paintHorizontalLine(Graphics g, JComponent c, int y,
                                               int left, int right) {

            }

            @Override
            protected void paintVerticalLine(Graphics g, JComponent c, int x,
                                             int top, int bottom) {

            }

            @Override
            protected KeyListener createKeyListener() {//重写此方法，取消树上默认的键盘响应，（当时现象是响应C键）
                return null;
            }
        };
        if (tree != null) {
            tree.setUI(customUI);
        }
    }
}
