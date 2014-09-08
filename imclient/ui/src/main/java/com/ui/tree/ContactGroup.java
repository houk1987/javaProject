package com.ui.tree;


import com.imService.contact.Contact;
import com.ui.tree.panes.CollapsiblePane;
import com.ui.tree.renderer.JContactItemRenderer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2014/8/28.
 */
public class ContactGroup extends CollapsiblePane {

    private String groupName;
    private DefaultListModel model;
    private JList contactItemList;
    private JPanel listPanel;
    private List<ContactItem> contactItems;


    public ContactGroup(String groupName){
        contactItems = new ArrayList<>();
        this.groupName = groupName;
        model = new DefaultListModel();
        contactItemList = new JList(model);
        contactItemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==1&&e.getClickCount()==2){
                    ContactItem item = (ContactItem)contactItemList.getSelectedValue();
                    item.click();

                }
            }
        });


        contactItemList.setCellRenderer(new JContactItemRenderer());
        setTitle(getGroupTitle(groupName));

        listPanel = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP, 0, 0, true, false));
        listPanel.add(contactItemList, listPanel);
        this.setContentPane(listPanel);

    }

    public void addContactItem(ContactItem item){
        model.addElement(item);
        contactItems.add(item);
    }

    public List<ContactItem> getAllContactItem(){
        return contactItems;
    }


    /**
     * Returns the "pretty" title of the ContactGroup.
     *
     * @param title the title.
     * @return the new title.
     */
    public String getGroupTitle(String title) {
        int lastIndex = title.lastIndexOf("::");
        if (lastIndex != -1) {
            title = title.substring(lastIndex + 2);
        }

        return title;
    }
}
