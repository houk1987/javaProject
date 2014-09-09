package com.dtclient.main.tree;





import com.dtclient.vo.Unit;
import com.dtclient.vo.UserInfo;

import java.util.HashMap;
import java.util.List;

/**
 * 同步服务器数据
 * 同步人员信息，单位信息
 * Created by a on 2014/7/9.
 */
public class SynDataService {

    private static final SynDataService synDataService = new SynDataService();

    public static  SynDataService getInstance(){
        return synDataService;
    }

    private SynDataService() {

    }

    /**
     * 同步人员数据
     */
    public  List<UserInfo> synUsers() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getuser");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            List<UserInfo> users = gson.fromJson(rs, new TypeToken<List<UserInfo>>() {
            }.getType());
            return users;
        }
        return null;
    }

    /**
     * 同步单位数据
     */
    public  List<Unit> synUnits() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("type", "getpartment");
        String rs = execute(paramMap);
        if (!"-1".equals(rs)) {
            Gson gson = new Gson();
            List<Unit> partments = gson.fromJson(rs, new TypeToken<List<Unit>>() {
            }.getType());
            return partments;
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
            return SanHttpClient.getDataAsString("http://"+ +":"+9090+"/plugins/orgtree/orgtree", paramMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
