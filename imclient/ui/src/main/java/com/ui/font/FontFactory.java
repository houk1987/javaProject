package com.ui.font;

import java.awt.*;

/**
 * Created by 356 on 14-8-30.
 */
public class FontFactory {

    public static Font createFont(String style,int size){
        Font font = new Font(style,Font.PLAIN,size);
        return font;
    }

    public static Font createFontBold(String style,int size){
        Font font = new Font(style,Font.BOLD,size);
        return font;
    }

    public static Font createFontITALIC(String style,int size){
        Font font = new Font(style,Font.ITALIC,size);
        return font;
    }
}
