package com.imService.contact;

import com.imService.client.Client;
import org.jivesoftware.smack.*;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static org.junit.Assert.*;

public class RosterManagerTest {

    private static XMPPConnection xmppConnection;
    private final static String host = "192.168.1.108";
    private final static int port= 5222;


    @BeforeClass
    public static  void beforeClass(){
        ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(host,port,"30san");
        connectionConfiguration.setSASLAuthenticationEnabled(true);
        xmppConnection = new XMPPConnection(connectionConfiguration);
        try {
            xmppConnection.connect();
            xmppConnection.login("1","1");
        } catch (XMPPException e) {
            e.printStackTrace();
            System.out.print("登陆失败！");
        }
    }

    /**
     * 获取所有好友用户列表
     */
    @Test
    public void getAllEntriesTest(){
        List<RosterEntry> list = RosterManager.getAllEntries(xmppConnection.getRoster());
        for(RosterEntry entry : list){
            System.out.println("用户账号:"+entry.getUser());
            System.out.println("用户姓名:"+ entry.getName());
            System.out.println("用户状态:"+ entry.getStatus());
        }
    }

    /**
     * 获取用户所在群组的信息
     */
    @Test
    public void getAllGroupsTest(){
        List<RosterGroup> groups = RosterManager.getGroups(xmppConnection.getRoster());
        System.out.println("群组个数"+groups.size());
//        if(groups.size()==0)return;
//        for (RosterGroup rosterGroup : groups){
//            System.out.println("群组人员数量:"+rosterGroup.getEntryCount());
//            System.out.println("群组名称:"+ rosterGroup.getName());
////            System.out.println("用户状态:"+ rosterGroup.);
//        }
    }

    @Test
    public void getEntriesByGroupTest(){
//        List<RosterEntry> groupEntrys = RosterManager.getEntriesByGroup(xmppConnection.getRoster(),"Friends");
//        for(RosterEntry entry : groupEntrys){
//            System.out.println("用户账号:"+entry.getUser());
//            System.out.println("用户姓名:"+ entry.getName());
//            System.out.println("用户状态:"+ entry.getStatus());
//        }
    }
}