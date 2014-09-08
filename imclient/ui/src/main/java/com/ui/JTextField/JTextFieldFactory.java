package com.ui.JTextField;

import com.sun.javafx.scene.layout.region.Border;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by 356 on 14-8-31.
 */
public class JTextFieldFactory {

    public static FreeTextField createJTextField(int width,int height,Color fontColor){
        FreeTextField jTextField = new FreeTextField();
        jTextField.setSize(width,height);
        jTextField.setForeground(fontColor);
        setBorder(jTextField);
        return jTextField;
    }

    public static JPasswordField createJPasswordField(int width,int height,Color fontColor,char echoChar){
        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setSize(width,height);
        jPasswordField.setForeground(fontColor);
        jPasswordField.setEchoChar(echoChar);
        setBorder(jPasswordField);
        return jPasswordField;
    }

    private static void setBorder(JTextField jTextField){
        Color BORDER_COLOR = new Color(210, 210, 210, 0);
        jTextField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(BORDER_COLOR, 1), new EmptyBorder(new Insets(2, 2, 2, 2))));
    }


    class JTextFieldWithBorder extends JTextField{
        @Override
        protected void paintBorder(Graphics g) {
            super.paintBorder(g);
        }
    }
}
