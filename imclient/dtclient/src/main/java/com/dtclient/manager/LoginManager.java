package com.dtclient.manager;

import com.imService.imService.ImLoginService;
import com.imService.service.LoginService;

/**
 * Created by HK on 2014/9/14.
 */
public class LoginManager{

   private LoginService imLoginService; //登录通讯服务器

   private static LoginManager loginManager;

    public static LoginManager getInstance() {
        if(loginManager == null){
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    public LoginManager() {
        imLoginService = new ImLoginService(DtManager.getInstance().getDtClientConnection());
    }

    public void login(String user, String password) throws Exception {
        if(imLoginService == null)throw new NullPointerException("服务器连接失败!");
        imLoginService.login(user,password);
        DtManager.getInstance().showMainFrame();
    }

    public void loginOut() throws Exception {
        if(imLoginService == null)throw new NullPointerException("服务器连接失败!");
        imLoginService.loginOut();
    }
}
