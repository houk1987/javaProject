package com.dtclient.session;


import com.dtclient.sys.SysProperties;
import com.imService.message.FontStyle;
import com.ui.button.ImageButtonFactory;
import com.ui.button.RolloverButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ****************************************************
 * Description:
 *
 * @author yangwy
 * @date 创建日期 2012-8-21
 * ******************************************************
 */
public class FontSetPane extends JPanel implements ActionListener, ItemListener {

    private static final long serialVersionUID = 1L;
    private String currentlySkinPath = SysProperties.sessionFrameButtonPath();
    private JComboBox fontComb;  //字体选择
    private JComboBox fontSizeComb;//字体大小
    private JButton boldButton;//粗体
    private JButton italicButton;//斜体
    private ImageIcon isBoldIcon = new ImageIcon(currentlySkinPath + "boldPressed.png");
    private ImageIcon notBoldIcon = new ImageIcon(currentlySkinPath + "bold.png");
    private ImageIcon isItalicIcon = new ImageIcon(currentlySkinPath + "italicPressed.png");
    private ImageIcon notItalicIcon = new ImageIcon(currentlySkinPath + "italic.png");
    private RolloverButton currentColorButton;
    private SessionEditorPane sessionEditorPane;

    public FontSetPane(SessionEditorPane sessionEditorPane) {
        super();
        this.sessionEditorPane = sessionEditorPane;
        this.setSize(245, 85);
        this.setLayout(null);
        initFontPane();
        initColorPane();
        this.setBackground(Color.white);
        JLabel bgIcon = new JLabel(new ImageIcon(currentlySkinPath + "session/bgIcon.png"));
        bgIcon.setBounds(0, 0, 245, 88);
        add(bgIcon);
    }

    //初始化字体面板
    private void initFontPane() {
        //取得当前环境可用字体.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        //字体名称
        fontComb = new JComboBox(fontNames);
        fontComb.setBounds(6, 55, 130, 20);
        add(fontComb);
        //字号.
        String[] sizeStr = new String[]{"10", "12", "14", "16", "18", "20", "22", "24", "26"};
        fontSizeComb = new JComboBox(sizeStr);
        fontSizeComb.setBounds(137, 55, 50, 20);
        add(fontSizeComb);

        //粗体按钮
        boldButton = ImageButtonFactory.createButton(currentlySkinPath,"","bold.png");
        boldButton.setLocation(190, 55);
        boldButton.setBorderPainted(true);
        boldButton.setBorder(BorderFactory.createLineBorder(new Color(127, 157, 185), 1));
        this.add(boldButton);

        //斜体按钮
        italicButton =ImageButtonFactory.createButton(currentlySkinPath, "", "italic.png");
        italicButton.setLocation(213, 55);
        italicButton.setBorderPainted(true);
        italicButton.setBorder(BorderFactory.createLineBorder(new Color(127, 157, 185), 1));
        this.add(italicButton);

        italicButton.addActionListener(this);
        boldButton.addActionListener(this);
        fontComb.addItemListener(this);
        fontSizeComb.addItemListener(this);
        initFontStyle();
    }

    private void initFontStyle(){
        FontStyle fontStyle = sessionEditorPane.getRichEditor().getSessionMessage().getFontStyle();
        boldButton.setIcon(fontStyle.isBold()?isBoldIcon:notBoldIcon);
        italicButton.setIcon(fontStyle.isItalic()?isItalicIcon:notItalicIcon);
        fontComb.setSelectedItem(fontStyle.getFontStyleName());
    }



    //初始化颜色面板
    public void initColorPane() {
        JPanel mainPan = new JPanel();
        mainPan.setBounds(5, 5, 230, 50);
        mainPan.setLayout(new GridLayout(2, 10));
        mainPan.setOpaque(true);
        Map<String, ImageIcon> imageMap = this.getImageIconMap();
        for (Iterator iter = imageMap.keySet().iterator(); iter.hasNext(); ) {
            final String key = (String) iter.next();
            final ImageIcon icon = imageMap.get(key);// 获取颜色的图片
            final RolloverButton colorButton = new RolloverButton(icon);// 创建颜色按钮
            colorButton.setBorderPainted(true); //设置为无边框
            colorButton.setContentAreaFilled(false); // 设置无背景
            colorButton.setFocusPainted(false);// 设置无焦点虚线框
            colorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //获得选中卡片信息
                    String s = ((RolloverButton) e.getSource()).getIcon().toString();
                    int start = s.lastIndexOf("\\");
                    int end = s.lastIndexOf(".");
                    s = s.substring(start + 1, end);
                    String[] rgb = s.split("%");
                    int r = Integer.valueOf(rgb[0]);
                    int g = Integer.valueOf(rgb[1]);
                    int b = Integer.valueOf(rgb[2]);
                    sessionEditorPane.getRichEditor().setFontColor(new Color(r,g,b));
                    if (currentColorButton != null) {
                        currentColorButton.setBorder(null);
                        currentColorButton.updateUI();
                    }
                    currentColorButton = (RolloverButton) e.getSource();
                }
            });
            colorButton.addMouseMotionListener(new MouseMotionListener() {
                public void mouseMoved(MouseEvent e) {
                    ((RolloverButton) e.getSource()).setBorder(new LineBorder(
                            Color.red));// 设置边框颜色
                }

                public void mouseDragged(MouseEvent e) {

                }
            });
            colorButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    if (currentColorButton != (RolloverButton) e.getSource()) {
                        ((RolloverButton) e.getSource()).setBorder(null);
                    }
                }
            });
            mainPan.add(colorButton);
        }
        this.add(mainPan);
    }

    /**
     * 取得所有color的集合
     *
     * @return the active set of color.
     */
    public Map<String, ImageIcon> getImageIconMap() {
        File colorFile = new File(currentlySkinPath + "/color");// 获取颜色所在的文件夹
        File[] colorFileList = colorFile.listFiles();// 取得颜色文件下所有表情图片的集合
        LinkedHashMap<String, ImageIcon> imageMap = new LinkedHashMap<String, ImageIcon>();
        for (int i = 0; i < colorFileList.length; i++) {
            if (colorFileList[i].getName().endsWith(".png")) {
                String key = colorFileList[i].getName();
                ImageIcon icon = new ImageIcon(colorFileList[i].getPath());
                imageMap.put(key, icon);
            }
        }
        return imageMap;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boldButton) {//粗体按钮事件
            boolean isBold = !sessionEditorPane.getRichEditor().getSessionMessage().getFontStyle().isBold();
            sessionEditorPane.getRichEditor().setFontBold(isBold);
            boldButton.setIcon(isBold?isBoldIcon:notBoldIcon);
        } else if (e.getSource() == italicButton) {    //斜体按钮事件
            boolean isItalic = !sessionEditorPane.getRichEditor().getSessionMessage().getFontStyle().isBold();
            sessionEditorPane.getRichEditor().setFontItalic(isItalic);
            italicButton.setIcon(isItalic?isItalicIcon:notItalicIcon);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == fontComb) {  //字体名称事件
            sessionEditorPane.getRichEditor().setFontFamily(fontComb.getSelectedItem().toString());
        } else if (e.getSource() == fontSizeComb) {    //字体大小事件
            String size = String.valueOf(fontSizeComb.getSelectedItem());
            sessionEditorPane.getRichEditor().setFontSize(Integer.valueOf(size));
        }
    }
}
