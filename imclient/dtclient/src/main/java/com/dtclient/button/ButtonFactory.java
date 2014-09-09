package com.dtclient.button;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Created by a on 2014/7/11.
 */
public abstract class ButtonFactory {
    private String path;

    /**
     * 按钮图片所在的目录路径
     * @param path
     */


    public static JButton createButton(String path,String toolTipText,String imageName) {
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        String buttonPath = path+imageName;
        String otherPath = buttonPath.substring(0, buttonPath.lastIndexOf("."));
        String hoverFullPath = otherPath + "Hover.png";
        String pressedFullPath = otherPath + "Pressed.png";
        ImageIcon defaultImage = new ImageIcon(buttonPath);
        button.setIcon(defaultImage);
        button.setRolloverIcon(new ImageIcon(hoverFullPath));
        button.setPressedIcon(new ImageIcon(pressedFullPath));
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        Dimension dim = new Dimension(defaultImage.getIconWidth(), defaultImage.getIconHeight());
        button.setPreferredSize(dim);
        button.setSize(dim);
        return button;
    }
}
