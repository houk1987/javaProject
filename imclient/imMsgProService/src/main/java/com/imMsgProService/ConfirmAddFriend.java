package com.imMsgProService;

import com.pubTools.http.HttpTools;

import java.util.HashMap;

/**
 * Created by a on 2014/9/4.
 * 确认加为好友
 * 通过访问服务器，在服务器进行添加
 */
public class ConfirmAddFriend {

    public enum ResponseRS{
        SERVERERROR,//服务器错误
        SUCCESS,   //添加成功
        FAILURE;  //添加失败
    }



    public static String requestConfirmAddFriend(String host,int port,String url,HashMap<String,String> requestParam,boolean is_ssl){
        String requestUrl = getRequestUrl(host,port,url,is_ssl);
        String responseRs = HttpTools.executeRequest(requestUrl,requestParam);
        return "-1";
    }



    private static String getRequestUrl(String host,int port,String url,boolean is_ssl){
        StringBuffer requestUrl = new StringBuffer("");
        requestUrl.append(is_ssl?"https":"http");
        requestUrl.append("://");
        requestUrl.append(host);
        requestUrl.append(":");
        requestUrl.append(port);
        requestUrl.append("/");
        requestUrl.append(url);
        return requestUrl.toString();
    }
}
