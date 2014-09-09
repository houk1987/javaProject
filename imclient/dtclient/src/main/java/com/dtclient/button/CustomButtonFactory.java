package com.dtclient.button;

import javax.swing.*;

/**
 * Created by a on 2014/7/31.
 */
public class CustomButtonFactory {

    private static final String mainButtonPath = "res/main/";
    private static final String sessionButtonPath = "res/button/session/";
    private static final String contactButtonPath = "res/main/addContact/";

    public static JButton createTabLeftBtn(){return ButtonFactory.createButton(mainButtonPath+"tab/","","TabLeft.png");}
    public static JButton createTabRightBtn(){return ButtonFactory.createButton(mainButtonPath+"tab/","","TabRight.png");}
}
