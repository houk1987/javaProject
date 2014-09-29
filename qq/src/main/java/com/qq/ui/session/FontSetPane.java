package com.qq.ui.session;



import com.imService.message.FontStyle;
import com.qq.sys.SysProperties;
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
 * @date �������� 2012-8-21
 * ******************************************************
 */
public class FontSetPane extends JPanel implements ActionListener, ItemListener {

    private static final long serialVersionUID = 1L;
    private String currentlySkinPath = SysProperties.sessionFrameButtonPath();
    private JComboBox fontComb;  //����ѡ��
    private JComboBox fontSizeComb;//�����С
    private JButton boldButton;//����
    private JButton italicButton;//б��
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

    //��ʼ���������
    private void initFontPane() {
        //ȡ�õ�ǰ������������.
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        //��������
        fontComb = new JComboBox(fontNames);
        fontComb.setBounds(6, 55, 130, 20);
        add(fontComb);
        //�ֺ�.
        String[] sizeStr = new String[]{"10", "12", "14", "16", "18", "20", "22", "24", "26"};
        fontSizeComb = new JComboBox(sizeStr);
        fontSizeComb.setBounds(137, 55, 50, 20);
        add(fontSizeComb);

        //���尴ť
        boldButton = ImageButtonFactory.createButton(currentlySkinPath,"","bold.png");
        boldButton.setLocation(190, 55);
        boldButton.setBorderPainted(true);
        boldButton.setBorder(BorderFactory.createLineBorder(new Color(127, 157, 185), 1));
        this.add(boldButton);

        //б�尴ť
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
        fontSizeComb.setSelectedItem(String.valueOf(fontStyle.getSize()));
    }



    //��ʼ����ɫ���
    public void initColorPane() {
        JPanel mainPan = new JPanel();
        mainPan.setBounds(5, 5, 230, 50);
        mainPan.setLayout(new GridLayout(2, 10));
        mainPan.setOpaque(true);
        Map<String, ImageIcon> imageMap = this.getImageIconMap();
        for (Iterator iter = imageMap.keySet().iterator(); iter.hasNext(); ) {
            final String key = (String) iter.next();
            final ImageIcon icon = imageMap.get(key);// ��ȡ��ɫ��ͼƬ
            final RolloverButton colorButton = new RolloverButton(icon);// ������ɫ��ť
            colorButton.setBorderPainted(true); //����Ϊ�ޱ߿�
            colorButton.setContentAreaFilled(false); // �����ޱ���
            colorButton.setFocusPainted(false);// �����޽������߿�
            colorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //���ѡ�п�Ƭ��Ϣ
                    String s = ((RolloverButton) e.getSource()).getIcon().toString();
                    int start = s.lastIndexOf("\\");
                    int end = s.lastIndexOf(".");
                    s = s.substring(start + 1, end);
                    String[] rgb = s.split("%");
                    int r = Integer.valueOf(rgb[0]);
                    int g = Integer.valueOf(rgb[1]);
                    int b = Integer.valueOf(rgb[2]);
                    sessionEditorPane.getRichEditor().setFontColor(new Color(r,g,b));
                    SysProperties.setFontColor(s);
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
                            Color.red));// ���ñ߿���ɫ
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
     * ȡ������color�ļ���
     *
     * @return the active set of color.
     */
    public Map<String, ImageIcon> getImageIconMap() {
        File colorFile = new File(currentlySkinPath + "/color");// ��ȡ��ɫ���ڵ��ļ���
        File[] colorFileList = colorFile.listFiles();// ȡ����ɫ�ļ������б���ͼƬ�ļ���
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
        if (e.getSource() == boldButton) {//���尴ť�¼�
            boolean isBold = !sessionEditorPane.getRichEditor().getSessionMessage().getFontStyle().isBold();
            sessionEditorPane.getRichEditor().setFontBold(isBold);
            boldButton.setIcon(isBold?isBoldIcon:notBoldIcon);
        } else if (e.getSource() == italicButton) {    //б�尴ť�¼�
            boolean isItalic = !sessionEditorPane.getRichEditor().getSessionMessage().getFontStyle().isItalic();
            sessionEditorPane.getRichEditor().setFontItalic(isItalic);
            italicButton.setIcon(isItalic?isItalicIcon:notItalicIcon);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == fontComb) {  //���������¼�
            sessionEditorPane.getRichEditor().setFontFamily(fontComb.getSelectedItem().toString());
        } else if (e.getSource() == fontSizeComb) {    //�����С�¼�
            String size = String.valueOf(fontSizeComb.getSelectedItem());
            sessionEditorPane.getRichEditor().setFontSize(Integer.valueOf(size));
        }
    }
}
