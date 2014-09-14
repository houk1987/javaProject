package com.imService.imService;

import com.imService.connection.ImConnection;
import com.imService.service.LoginService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImLoginServiceTest {

    private static String host = "192.168.1.105";
    private static int port = 5222;
    private static String domain="30san.com";
    private static LoginService imLoginService;

    @BeforeClass
    public static void  beforeClass(){
        ImConnection imConnection = new ImConnection(host,port,domain);
        imLoginService = LoginService.getInstance(imConnection);
    }

    @Test
    public void testLogin() throws Exception {
        imLoginService.login("test","1");
    }

    @Test
    public void testLoginOut() throws Exception {
        imLoginService.loginOut();
    }
}