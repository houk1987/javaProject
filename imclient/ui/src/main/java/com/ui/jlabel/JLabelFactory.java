package com.ui.jlabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 356 on 14-8-30.
 */
public class JLabelFactory {

    public static JLabel createJLabel(String text,Font font,Color color){
        JLabel jLabel = new JLabel(text);
        if(font!=null)jLabel.setFont(font);
        if(color !=null)jLabel.setForeground(color);
        return jLabel;
    }

    public static JLabel createJLabel(ImageIcon imageIcon){
        JLabel label = new JLabel(imageIcon);
        if(imageIcon != null)label.setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
        return label;
    }

    public static LinkLabel createLinkLabel(String text,Font font,String color,String url){
        LinkLabel jLabel = new LinkLabel(text,color,url);
        jLabel.setFont(font);
        return jLabel;
    }
}
