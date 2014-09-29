package com.dt.manager;


import com.dt.lanuch.DTClient;
import com.dt.sys.SysProperties;
import com.dt.vo.Unit;
import com.dt.vo.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.san30.pub.tools.SanHttpClient;

import java.util.HashMap;
import java.util.List;

/**
 * 同步服务器数据
 * 同步人员信息，单位信息
 * Created by a on 2014/7/9.
 */
public class SynDataManager {

    private List<UserInfo> userInfoList;
    private List<Unit> unitList;

    private static final SynDataManager SYN_DATA_MANAGER = new SynDataManager();

    public static SynDataManager getInstance(){
        return SYN_DATA_MANAGER;
    }

    private SynDataManager() {

    }

    /**
     * 同步人员数据
     */
    public  List<UserInfo> synUsers() {
        if(userInfoList!=null)return userInfoList;
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getuser");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            userInfoList = gson.fromJson(rs, new TypeToken<List<UserInfo>>() {
            }.getType());

            for(UserInfo userInfo : userInfoList){
                userInfo.setPresence(DTClient.getInstance().getPresence(userInfo.getId()));
            }
            return userInfoList;
        }
        return null;
    }

    public UserInfo getUserInfo(String userId){
        if(userInfoList==null)userInfoList =synUsers();
        if(userInfoList != null){
            for (UserInfo userInfo : userInfoList){
                if(userInfo.getId().equals(userId)){
                   return userInfo;
                }
            }
        }
        return null;
    }



    /**
     * 同步单位数据
     */
    public  List<Unit> synUnits() {
        if(unitList!=null)return unitList;
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getpartment");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            unitList = gson.fromJson(rs, new TypeToken<List<Unit>>() {
            }.getType());
            return unitList;
        }
        return null;
    }

    /**
     * 执行同步请求，返回请求的数据
     *
     * @param paramMap 请求参数的 map
     * @return rs
     */
    private  String execute(HashMap<String, String> paramMap) {
        try {
            return SanHttpClient.getDataAsString("http://" + SysProperties.getHost() +":" + 9090 + "/plugins/orgtree/orgtree", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
