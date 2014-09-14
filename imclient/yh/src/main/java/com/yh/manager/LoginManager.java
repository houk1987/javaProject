package com.yh.manager;

import com.imService.imService.ImLoginService;
import com.imService.service.LoginService;
import com.yh.login.LoginFrame;

/**
 * Created by HK on 2014/9/15.
 */
public class LoginManager {

    private static LoginManager loginManager;
    private LoginService loginService;

    private LoginFrame loginFrame;

    public static LoginManager getInstance() {
        if(loginManager == null) {
            loginManager = new LoginManager();
        }
        return loginManager;
    }

    private LoginManager(){
        loginService = new ImLoginService(YhManager.getInstance().getImConnection());
    }

    public void showLoginFrame(){
        loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }

    public void closeLoginFrame(){
        if(loginFrame!=null) {
            loginFrame.dispose();
            loginFrame =null;
        }
    }

    public void login(String user,String password) throws Exception{
        if(loginService == null)throw new NullPointerException("服务器连接失败!");
        loginService.login(user,password);
    }

    public void loginOut() throws Exception {
        if(loginService == null)throw new NullPointerException("服务器连接失败!");
        loginService.loginOut();
    }



    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
}
