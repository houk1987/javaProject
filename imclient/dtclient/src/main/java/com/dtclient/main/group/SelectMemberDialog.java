package com.dtclient.main.group;

import com.dtclient.main.tree.OrgTree;
import com.dtclient.sys.SysProperties;
import com.dtclient.vo.UserInfo;
import com.ui.button.ImageButtonFactory;
import com.ui.frame.PubDialog;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectMemberDialog extends PubDialog implements ActionListener{
    private static final long serialVersionUID = 1L;
    private String SKIN_PATH=SysProperties.frameButtonPath();
    private JTextField searchText;
    private JScrollPane left;
    private JScrollPane right;
    private JPanel contentPane = null;
    private JButton choosedButton;
    private JButton searchButton;
    private JButton removeButton;
    private JButton cancelButton;
    private JButton okButton;
    private GroupDialog groupDialog;
    private GroupMemberTable selectedMemberTable;
    private OrgTree orgTree;
    private List<UserInfo> userList = new ArrayList<>();

    public SelectMemberDialog(GroupDialog owner) {
        super(owner,new GroupDialogTitle("选择群成员"));
        init();
        this.setLocationRelativeTo(owner);
        this.groupDialog = owner;
    }

    private void init() {
        setImagePath(SysProperties.framePath());
        setModal(true);
       initComponents();
       layoutComponents();
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        searchText = new JTextField("键入名称");
        searchButton = ImageButtonFactory.createButton(SKIN_PATH,"搜索","search.png");
        choosedButton =  ImageButtonFactory.createButton(SKIN_PATH,"选择","select.png");
        removeButton = ImageButtonFactory.createButton(SKIN_PATH,"移除","remove.png");
        cancelButton =  ImageButtonFactory.createButton(SKIN_PATH,"取消","cancel.png");
        okButton = ImageButtonFactory.createButton(SKIN_PATH,"确定","ok.png");

        left = new JScrollPane();
        left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        orgTree = new OrgTree();
        orgTree.loadData();
        left.setBorder(new LineBorder(new Color(170, 187, 207)));
        left.setViewportView(orgTree);

        right = new JScrollPane();
        right.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        selectedMemberTable = new GroupMemberTable();
        right.setViewportView(selectedMemberTable);
        right.setBorder(new LineBorder(new Color(170, 187, 207)));
    }

    /**
     * 进行布局，给组件添加事件监听
     */
    private void layoutComponents() {
        JPanel contentPane = new JPanel(null);
        contentPane.setOpaque(false);
        contentPane.setBackground(new Color(249, 251, 254));
        add(contentPane, BorderLayout.CENTER);

        left.setBounds(23, 71, 220, 280);
        right.setBounds(326, 71, 220, 280);
        searchText.setBounds(23, 43, 150, 23);
        searchButton.setLocation(179, 42);
        choosedButton.setLocation(251, 144);
        removeButton.setLocation(251, 222);
        contentPane.add(choosedButton);
        contentPane.add(searchButton);
        contentPane.add(removeButton);
        contentPane.add(searchText);
        contentPane.add(left);
        contentPane.add(right);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        removeButton.addActionListener(this);
        searchButton.addActionListener(this);
        choosedButton.addActionListener(this);
    }

    @Override
    protected void dialogInit() {
        super.dialogInit();
        setSize(585, 445);
    }

    /**
     * 加载已选人员列表
     *
     * @param userList
     */
    public void loadSelectedUserTable(List<UserInfo> userList) {
        this.userList = userList;
        if (this.userList == null) {
            this.userList = new ArrayList<>();
        }
      // if(!userList.contains()userList.add(Global.getLoginUser());
       // selectedMemberTable.load(userList);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            //searchButtonPressed();
        }

        if (e.getSource() == choosedButton) {
            selectedMember();
        }

        if (e.getSource() == removeButton) {
            delete();
        }

        if (e.getSource() == cancelButton) {
            this.dispose();
        }

        if (e.getSource() == okButton) {
            dispose();
           // groupDialog.initTable(userList);
        }
    }

    private void selectedMember() {
        //获得选择的节点
        int[] selectedRow = orgTree.getSelectionRows();
        //循环依次取得节点中的User`
        for (int i = 0; i < selectedRow.length; i++) {
            TreePath treePath = orgTree.getPathForRow(selectedRow[i]);
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
            Object userObject = selectedNode.getUserObject();
            if (userObject instanceof UserInfo) {
                UserInfo user = (UserInfo) userObject;
//                if (isSelected(user)) {
//                    selectedMemberTable.add(user);
//                    userList.add(user);
//                }
            }
        }
    }

    private void delete() {
        int[] rows = selectedMemberTable.getSelectedRows();
        int offset = 0;
        for (int i = 0; i < rows.length; i++) {
            selectedMemberTable.delete(rows[i - offset]);
            userList.remove(i - offset);
            offset++;
        }
    }

    private boolean isSelected(UserInfo user) {
//        for (int j = 0; j < userList.size(); j++) {
//            if (user.getId().equals(userList.get(j).getId())) {
//                JOptionPane.showMessageDialog(this, "用户" + user.getUsername() + "已添加，不能重复添加!");
//                return false;
//            }
//        }
        return true;
    }
}
