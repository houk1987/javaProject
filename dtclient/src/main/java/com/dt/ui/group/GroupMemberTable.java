package com.dt.ui.group;




import com.dt.vo.UserInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Ⱥ���Ա���
 * Created by a on 2014/7/10.
 */
public class GroupMemberTable extends JTable{

    private final String header[] = {"�û���","���"};
    private DefaultTableModel tableModel;

    public GroupMemberTable() {
       tableModel = new DefaultTableModel(header, 0); // ��ʼ����ͷ
       this.setModel(tableModel);
    }

    public GroupMemberTable(List<UserInfo> users) {
        this();
        load(users);
    }

    public void add(UserInfo user){
        Object[] objects = {user.getUsername(),user.getId()};
        tableModel.addRow(objects);
    }

    public void load(List<UserInfo> users){
        if(users!=null){
            for (UserInfo user : users){
                add(user);
            }
        }
    }

    public void delete(int row){
        tableModel.removeRow(row);
    }


}
