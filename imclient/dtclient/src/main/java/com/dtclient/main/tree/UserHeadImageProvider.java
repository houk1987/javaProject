package com.dtclient.main.tree;

import javax.swing.*;

/**
 * Created by a on 2014/7/9.
 */
public class UserHeadImageProvider {

    public static ImageIcon getHeadImageIcon(int sex) {
        ImageIcon imageIcon = new ImageIcon(imageIconUrl(sex));
        return imageIcon;
    }

    private static String imageIconUrl(int sex) {
        String sexUrl = sex ==1 ? "male" : "female";
        String url = "res/tree/head/" + sexUrl + "/" + "online.png";
        return url;
    }
}
