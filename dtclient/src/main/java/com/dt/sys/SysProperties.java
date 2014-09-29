package com.dt.sys;

import com.pubTools.properties.PropertiesTools;

import java.io.UnsupportedEncodingException;

/**
 * Created by lenovo on 2014/9/12.
 */
public class SysProperties {

    private final static String FILE_PATH="user/sys.properties";

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

    public static String frameButtonPath(){
        return PropertiesTools.readValue(FILE_PATH,"frameButtonPath");
    }

    public static String groupButtonPath(){
        return PropertiesTools.readValue(FILE_PATH,"groupButtonPath");
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

    public static String getFontFamily(){
        Object value = PropertiesTools.readValue(FILE_PATH,"fontFamily");
        try {
          return  new String(value.toString().getBytes("ISO-8859-1"),"gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFontSize(){
        return PropertiesTools.readValue(FILE_PATH,"fontSize");
    }

    public static String getFontColor(){
        return PropertiesTools.readValue(FILE_PATH,"fontColor");
    }

    public static boolean isFontBold(){
        String value = PropertiesTools.readValue(FILE_PATH,"fontBold");
        if(value!=null)return Boolean.valueOf(value);
        return false;
    }

    public static boolean isFontItalic(){
        String value = PropertiesTools.readValue(FILE_PATH,"fontItalic");
        if(value!=null)return Boolean.valueOf(value);
        return false;
    }


    public static void setFontFamily(String fontFamily){
          PropertiesTools.writeValue(FILE_PATH,"fontFamily",fontFamily);
    }

    public static void setFontSize(String fontSize){
        PropertiesTools.writeValue(FILE_PATH,"fontSize",fontSize);
    }

    public static void setFontColor(String fontColor){
      PropertiesTools.writeValue(FILE_PATH,"fontColor","fontColor");
    }

    public static void setFontBold(String fontBold){
        PropertiesTools.writeValue(FILE_PATH,"fontBold",fontBold);
    }

    public static void setFontItalic(String fontItalic){
        PropertiesTools.writeValue(FILE_PATH, "fontItalic", fontItalic);
    }
}
