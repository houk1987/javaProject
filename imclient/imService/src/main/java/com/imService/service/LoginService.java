package com.imService.service;

import com.imService.connection.ImConnection;

/**
 * 登录客户端服务接口
 * Created by HK on 2014/9/14.
 */
public class LoginService {

    private static LoginService loginService;
    private ImConnection imConnection;

    public static LoginService getInstance(ImConnection imConnection) {
        if(null == loginService){
            loginService = new LoginService(imConnection);
        }
        return loginService;
    }

    public LoginService(ImConnection imConnection) {
        this.imConnection = imConnection;
    }


    public void login(String user, String password) throws Exception{
        if(imConnection == null)throw new NullPointerException("服务器连接异常!");
        this.imConnection.connection();
        this.imConnection.login(user,password);
    }


    public void loginOut() throws Exception{
        if(imConnection == null)throw new NullPointerException("服务器连接异常!");
        imConnection.close();  //关闭连接
    }
}
