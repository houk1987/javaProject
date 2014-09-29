package com.dt.ui.group;


import com.dt.handel.OrgTreeHandel;
import com.dt.sys.SysProperties;
import com.dt.ui.tree.OrgTree;
import com.dt.vo.UserInfo;
import com.ui.button.ImageButtonFactory;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectMemberDialog extends JDialog implements ActionListener{
    private static final long serialVersionUID = 1L;
    private String SKIN_PATH=SysProperties.groupButtonPath();
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
        super(owner);
        init();
        setTitle("ѡ��Ⱥ��Ա");
        this.groupDialog = owner;
    }

    private void init() {
        setModal(true);
        initComponents();
        layoutComponents();
    }

    /**
     * ��ʼ�����
     */
    private void initComponents() {
        searchText = new JTextField("��������");
        searchButton = ImageButtonFactory.createButton(SKIN_PATH,"����","search.png");
        choosedButton =  ImageButtonFactory.createButton(SKIN_PATH,"ѡ��","select.png");
        removeButton = ImageButtonFactory.createButton(SKIN_PATH,"�Ƴ�","remove.png");
        cancelButton =  ImageButtonFactory.createButton(SKIN_PATH,"ȡ��","cancel.png");
        okButton = ImageButtonFactory.createButton(SKIN_PATH,"ȷ��","ok.png");

        left = new JScrollPane();
        left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        orgTree = new OrgTree(new OrgTreeHandel());

        left.setBorder(new LineBorder(new Color(170, 187, 207)));
        left.setViewportView(orgTree);

        right = new JScrollPane();
        right.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        selectedMemberTable = new GroupMemberTable();
        right.setViewportView(selectedMemberTable);
        right.setBorder(new LineBorder(new Color(170, 187, 207)));
    }

    /**
     * ���в��֣����������¼�����
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
     * ������ѡ��Ա�б�
     *
     * @param userList
     */
    public void loadSelectedUserTable(List<UserInfo> userList) {
        this.userList = userList;
        if (this.userList == null) {
            this.userList = new ArrayList<>();
        }
       selectedMemberTable.load(userList);
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
           groupDialog.initTable(userList);
        }
    }

    private void selectedMember() {
        //���ѡ��Ľڵ�
        int[] selectedRow = orgTree.getSelectionRows();
        //ѭ������ȡ�ýڵ��е�User`
        for (int i = 0; i < selectedRow.length; i++) {
            TreePath treePath = orgTree.getPathForRow(selectedRow[i]);
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
            Object userObject = selectedNode.getUserObject();
            if (userObject instanceof UserInfo) {
                UserInfo user = (UserInfo) userObject;
                if (isSelected(user)) {
                    selectedMemberTable.add(user);
                    userList.add(user);
                }
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
        for (int j = 0; j < userList.size(); j++) {
            if (user.getId().equals(userList.get(j).getId())) {
                JOptionPane.showMessageDialog(this, "�û�" + user.getUsername() + "����ӣ������ظ����!");
                return false;
            }
        }
        return true;
    }
}
