package com.yh.session;

import com.yh.button.CustomButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a on 2014/8/22.
 */
public class EditorbarPane extends JPanel implements ActionListener{
    private JButton emoticonButton; //表情按钮
    private JButton audiblesButton; //声色按钮
    private JButton fontButton; //字体按钮
    private JButton buzzButton;//呼叫朋友
    private JButton fileButton; //传输文件
    private JButton screenCaptureButton; //截屏
    private JButton disCloseButton; //关闭个人相册
    private JButton disOpenButton; //开启个人相册

    public EditorbarPane() {
        setBackground(new Color(118,36,110));
        this.initComponent();
        this.layoutComponent();

    }

    private void initComponent(){
        emoticonButton = CustomButtonFactory.createFaceButton();
        audiblesButton =CustomButtonFactory.createAudiblesButton();
        fontButton = CustomButtonFactory.createFontButton();
        buzzButton = CustomButtonFactory.createBuzzButton();
        fileButton = CustomButtonFactory.createFileTransferButton();
        screenCaptureButton = CustomButtonFactory.createScreenButton();
        disCloseButton =  CustomButtonFactory.createDisCloseButton();
        disOpenButton = CustomButtonFactory.createDisOpenButton();
        disCloseButton.setVisible(false);//默认是关闭的
    }

    private void layoutComponent(){
        setLayout(new BorderLayout());
        JPanel leftPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPane.setBackground(new Color(118,36,110));
        leftPane.add(emoticonButton);
        leftPane.add(audiblesButton);
        leftPane.add(fontButton);
        leftPane.add(buzzButton);
        leftPane.add(fileButton);
        leftPane.add(screenCaptureButton);
        JPanel rightPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rightPane.setBackground(new Color(118,36,110));
        rightPane.add(disCloseButton);
        rightPane.add(disOpenButton);
        add(leftPane, BorderLayout.WEST);
        add(rightPane,BorderLayout.EAST);
        JLabel expandableImageLabel = new JLabel();
        expandableImageLabel.setBackground(new Color(118, 36, 110));
        add(expandableImageLabel, BorderLayout.CENTER);
        emoticonButton.addActionListener(this);
        disCloseButton.addActionListener(this);
        disOpenButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(emoticonButton == e.getSource()){

        }else if(disCloseButton == e.getSource()){
            disOpenButton.setVisible(true);
            disCloseButton.setVisible(false);
        }else if(disOpenButton == e.getSource()){
            disOpenButton.setVisible(false);
            disCloseButton.setVisible(true);
        }
    }
}
