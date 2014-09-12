package com.dtclient.main.group;

import com.dtclient.main.MainFrame;
import com.dtclient.main.tree.CustomTree;
import com.dtclient.sys.SysProperties;
import com.dtclient.vo.FriendRooms;
import com.san30.sim.pub.imagewindow.ImageDialog;
import com.ui.button.ImageButtonFactory;
import com.ui.frame.PubDialog;
import com.ui.frame.PubFrame;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * createOrModify group
 * Created by a on 2014/7/10.
 */
public class GroupDialog extends PubDialog implements ActionListener {
    private final String SKIN_PATH= SysProperties.mainFrameButtonPath();
    private JLabel groupNameLabel;
    private JLabel groupMemberNameLabel;
    private JButton cancelButton;
    private JButton okButton;
    private JButton addMemberButton;
    private JButton delMemberButton;
    private GroupMemberTable groupMemberTable;
    private JTextField groupNameJTextField;
    private List<UserInfo> userList = new ArrayList<>();
    private FriendRooms friendRooms;

    public GroupDialog(Frame owner) {
        super(owner,new GroupDialogTitle());
        setImagePath(SysProperties.framePath());
        this.setLocationRelativeTo(owner);
       initComponents();
        layoutComponents();
        //initTable(this.userList);
    }

    public GroupDialog(FriendRooms friendRooms, Frame owner) {
        this(owner);
        this.friendRooms = friendRooms;
        //userList = GroupManager.getAllUserListWithGroupId(friendRooms.getJid());
        initGroupData();
      //  initTable(this.userList);
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        groupNameLabel = new JLabel("自定义群组名称：");
        groupMemberNameLabel = new JLabel("自定义组成员：");
        groupNameJTextField = new JTextField();
        groupNameJTextField.requestFocus();
        cancelButton = ImageButtonFactory.createButton(SKIN_PATH,"取消","cancel.png");
        okButton = ImageButtonFactory.createButton(SKIN_PATH, "确定", "ok.png");
      //  addMemberButton = ButtonFactoryManager.getCommonButtonFactory().createAddButton();
      //  delMemberButton = ButtonFactoryManager.getCommonButtonFactory().createDelButton();
      //  groupMemberTable = new GroupMemberTable(userList);
    }

    /**
     * 进行布局，给组件添加事件监听
     */
    private void layoutComponents() {
        //添加中间面板
        JPanel centerPane = new JPanel(null);
        centerPane.setOpaque(false);
        add(centerPane, BorderLayout.CENTER);

        groupNameLabel.setBounds(15, 15, 120, 24);
        groupNameJTextField.setBounds(15, 45, 230, 24);
        groupMemberNameLabel.setBounds(15, 75, 120, 24);
//        addMemberButton.setLocation(260, 115);
//        delMemberButton.setLocation(260, 145);

        //添加已在群组成员表格
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(15, 105, 230, 220);
        jScrollPane.setViewportView(groupMemberTable);

        centerPane.add(groupNameLabel); // 添加群组名称
        centerPane.add(groupNameJTextField);//群组名称输入框
        centerPane.add(groupMemberNameLabel);//群组人员名称
        centerPane.add(jScrollPane);//添加右边表格
//        centerPane.add(addMemberButton);//添加添加人员按钮
//        centerPane.add(delMemberButton);//删除人员按钮

        //底部确定，取消按钮所在面板
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);
        //添加监听
//        addMemberButton.addActionListener(this);
//        delMemberButton.addActionListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    /**
     * 初始化界面数据
     */
    private void initGroupData() {
        if (friendRooms == null) {
            return;
        }
        groupNameJTextField.setText(friendRooms.getName());
    }

    @Override
    protected void dialogInit() {
        super.dialogInit();
        setSize(350, 400);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.okButton) {
            ok();
        } else if (e.getSource() == this.cancelButton) {
            this.dispose();
        } else if (e.getSource() == this.addMemberButton) {
//            SelectMemberDialog selectMemberDialog = new SelectMemberDialog(this);
//            selectMemberDialog.loadSelectedUserTable(userList);
//            selectMemberDialog.setVisible(true);
        } else if (e.getSource() == this.delMemberButton) {
            delete();
        }
    }


//    public void initTable(List<User> users) {
//        this.userList = users;
//        groupMemberTable.load(userList);
//    }

    private void delete() {
//        int[] rows = groupMemberTable.getSelectedRows();
//        int offset = 0;
//        for (int i = 0; i < rows.length; i++) {
//            Object objects =groupMemberTable.getModel().getValueAt(rows[i - offset],1);
//            if(objects.toString().equals(Global.getLoginUser().getId()))return;
//            groupMemberTable.delete(rows[i - offset]);
//            userList.remove(i - offset);
//            offset++;
//        }
    }


    public void ok() {
        String groupName = this.groupNameJTextField.getText();
        if("".equals(groupName)){
            JOptionPane.showMessageDialog(this,"群组名称不能为空!","提示",JOptionPane.INFORMATION_MESSAGE);
            this.groupNameJTextField.requestFocus();
            return;
        }
        try{
            GroupManager.createGroup(this.groupNameJTextField.getText(), userList);
            CustomTree customTree = MainFrame.getInstance().getCustomTree();
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(friendRooms);
            customTree.addNode(node);
            this.dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,"创建群组失败!","提示",JOptionPane.ERROR_MESSAGE);
        }
    }
}
