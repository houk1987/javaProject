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
            System.out.print("��½ʧ�ܣ�");
        }
    }

    /**
     * ��ȡ���к����û��б�
     */
    @Test
    public void getAllEntriesTest(){
        List<RosterEntry> list = RosterManager.getAllEntries(xmppConnection.getRoster());
        for(RosterEntry entry : list){
            System.out.println("�û��˺�:"+entry.getUser());
            System.out.println("�û�����:"+ entry.getName());
            System.out.println("�û�״̬:"+ entry.getStatus());
        }
    }

    /**
     * ��ȡ�û�����Ⱥ�����Ϣ
     */
    @Test
    public void getAllGroupsTest(){
        List<RosterGroup> groups = RosterManager.getGroups(xmppConnection.getRoster());
        System.out.println("Ⱥ�����"+groups.size());
//        if(groups.size()==0)return;
//        for (RosterGroup rosterGroup : groups){
//            System.out.println("Ⱥ����Ա����:"+rosterGroup.getEntryCount());
//            System.out.println("Ⱥ������:"+ rosterGroup.getName());
////            System.out.println("�û�״̬:"+ rosterGroup.);
//        }
    }

    @Test
    public void getEntriesByGroupTest(){
//        List<RosterEntry> groupEntrys = RosterManager.getEntriesByGroup(xmppConnection.getRoster(),"Friends");
//        for(RosterEntry entry : groupEntrys){
//            System.out.println("�û��˺�:"+entry.getUser());
//            System.out.println("�û�����:"+ entry.getName());
//            System.out.println("�û�״̬:"+ entry.getStatus());
//        }
    }
}