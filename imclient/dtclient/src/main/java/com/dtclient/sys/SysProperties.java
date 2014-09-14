package com.dtclient.sys;

import com.pubTools.properties.PropertiesTools;
import com.pubTools.toos.StringTools;

/**
 * Created by lenovo on 2014/9/12.
 */
public class SysProperties {

    private final static String FILE_PATH="user/sys.properties";

    public static void setHost(String host){PropertiesTools.writeValue(FILE_PATH,"host",host);}

    public static void setPort(int port){PropertiesTools.writeValue(FILE_PATH,"port",String.valueOf(port));}

    public static void setUser(String user){PropertiesTools.writeValue(FILE_PATH,"user",user);}

    public static String getUser(){return PropertiesTools.readValue(FILE_PATH,"user");}

    public static String getHost(){
        return PropertiesTools.readValue(FILE_PATH,"host");
    }

    public static int getPort(){
        String value = PropertiesTools.readValue(FILE_PATH,"port");
        if(value!=null)return Integer.valueOf(value);
        return -1;
    };

    public static String domain(){
        return PropertiesTools.readValue(FILE_PATH,"domain");
    }

    public static String framePath(){
        return PropertiesTools.readValue(FILE_PATH,"framePath");
    }

    public static String frameButtonPath(){
        return PropertiesTools.readValue(FILE_PATH,"frameButtonPath");
    }

    public static String sessionFrameButtonPath(){
        return PropertiesTools.readValue(FILE_PATH,"sessionFrameButtonPath");
    }

    public static String sessionFramePath(){
        return PropertiesTools.readValue(FILE_PATH,"sessionFramePath");
    }

    public static String mainFramePath(){
        return PropertiesTools.readValue(FILE_PATH,"mainFramePath");
    }

    public static String mainFrameButtonPath(){return PropertiesTools.readValue(FILE_PATH,"mainFrameButtonPath");}
}
