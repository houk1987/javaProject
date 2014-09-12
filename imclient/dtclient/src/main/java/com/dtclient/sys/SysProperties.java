package com.dtclient.sys;

import com.pubTools.properties.PropertiesTools;

/**
 * Created by lenovo on 2014/9/12.
 */
public class SysProperties {

    private final static String FILE_PATH="user/sys.properties";

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
