package com.qq.ui.tree;

import com.ui.tree.ContactGroup;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lenovo on 2014/9/19.
 */
public class QQContactGroup extends ContactGroup {
    public QQContactGroup(String groupName) {
        super(groupName);
        contactItemList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contactItemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });


    }
}
