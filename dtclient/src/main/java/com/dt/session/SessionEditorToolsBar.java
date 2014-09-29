package com.dt.session;


import com.dt.sys.SysProperties;
import com.san30.sim.pub.imagewindow.ImageDialog;
import com.ui.button.ImageButtonFactory;
import com.ui.jlabel.ExpandableImageLabel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SessionEditorToolsBar extends JPanel implements MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;
    private final String SKIN_PATH = SysProperties.sessionFramePath();
    private final String SKIN_PATH_BUTTON = SysProperties.sessionFrameButtonPath();
    private ExpandableImageLabel editorToolsMidLabel;
    private JButton fontButton;  // 字体按钮
    private JButton faceButton;  // 表情按钮
    private JButton fileButton; // 发送文件按钮
    private ImageDialog dialog;// 字体选择器
    // 字体选择器有没有显示
    SessionEditorPane sessionEditorPane;

    public SessionEditorToolsBar(SessionEditorPane sessionEditorPane) {
        layoutComponent();
        this.sessionEditorPane = sessionEditorPane;
//        fileButton = CustomButtonFactory.createFileTransferButton();
//        fileButton.addMouseListener(this);
//        fileButton.addMouseMotionListener(this);
//        editorToolsMidLabel.add(fileButton);
    }

    private void layoutComponent() {
        this.setLayout(new BorderLayout());
        // JLabel editorToolsLeftLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "/session/contentPaneBorder/editorBarLeft.png"));
        //  ImageLabel editorToolsRightLabel = new ImageLabel(SKIN_PATH + "/session/contentPaneBorder/editorBarRight.png");
        editorToolsMidLabel = new ExpandableImageLabel(SKIN_PATH + "editorBarMid.png");
        editorToolsMidLabel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));
        add(editorToolsMidLabel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(0, editorToolsMidLabel.getIcon().getIconHeight()));//设置高度

//        faceButton = ImageButtonFactory.createButton(SKIN_PATH_BUTTON, "表情", "emoticon.png");
//        faceButton.addMouseListener(this);
//        editorToolsMidLabel.add(faceButton);

        fontButton = ImageButtonFactory.createButton(SKIN_PATH_BUTTON, "字体", "format.png"); // 字体按钮
        fontButton.setLayout(null);
        fontButton.addMouseListener(this);
        fontButton.addMouseMotionListener(this);
        editorToolsMidLabel.add(fontButton);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getSource() == faceButton) {
//            showEmotionPane(); //显示表情
//        } else
          if (e.getSource() == fileButton) {
            if (e.getButton() == 1) {
                if (fileButton.isEnabled()) {
                    Rectangle rectangleLeft = new Rectangle(0, 0, 52, 22);
                    Rectangle rectangRight = new Rectangle(52, 0, 16, 22);
                    Point targetPoint = e.getPoint();
                    if (rectangleLeft.contains(targetPoint)) {
                        showFileChoseDialog();
                    }
                }
            }
        } else if (e.getSource() == fontButton) {
            if (e.getButton() == 1) {
                showFontChooser(e.getXOnScreen() - e.getX() - 2, e.getYOnScreen() - e.getY() - 26);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == fileButton) {
            FileSet(e.getPoint());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == fileButton) {
            FileSet(e.getPoint());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource() == fileButton) {
            FileSet(e.getPoint());
        }
    }


    private void FileSet(Point point) {
        // TODO 这里要判断鼠标属于哪个区域
        Rectangle rectangleLeft = new Rectangle(0, 0, 52, 22);
        Rectangle rectangRight = new Rectangle(52, 0, 16, 22);
        Point targetPoint = point;
        if (rectangleLeft.contains(targetPoint)) {
            fileButton.setPressedIcon(new ImageIcon("res/session/button/transferPressed.png"));
            fileButton.updateUI();
        } else if (rectangRight.contains(targetPoint)) {
            fileButton.setPressedIcon(new ImageIcon("res/session/button/transferSetPressed.png"));
            fileButton.updateUI();
        }
        return;
    }

    private void showFileChoseDialog() {
        //选择文件　
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setApproveButtonText("选择");
        fc.setDialogTitle("请选择要添加的文件或文件夹");
        fc.setMultiSelectionEnabled(true);
        fc.setDragEnabled(true);
        int result = fc.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == result) {
            File[] tempFiles = fc.getSelectedFiles();
            for (int i = 0; i < tempFiles.length; i++) {
                if (!tempFiles[i].exists()) {
//                    JOptionPane.showMessageDialog(sessionFrame, "\"" + tempFiles[i].getName() + "\"不存在，请重新选择！", "警告",
//                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            //调用发送文件
        }
    }

    /**
     * 显示表情面板
     */
    private void showEmotionPane() {
        //SessionManager.saveCaretPosition(); // 保存编辑器光标位置
        JPopupMenu emotionPopup = new JPopupMenu();
        EmoticonUI emotionUI = new EmoticonUI(this.sessionEditorPane); // 创建EmoticonUI
        emotionUI.setVisible(true);
        emotionUI.updateUI();
        emotionPopup.add(emotionUI);// 添加到JPopupMenu
        if (emotionPopup.isShowing()) {
            emotionPopup.setVisible(false);
        } else {
            emotionPopup.show(faceButton, 0, -126); // 显示表情 //bug2714
        } // 会话时，鼠标点击A颜色时，显示按钮，移开鼠标后，还剩下按钮的一小块。
    }

    /**
     * *******************************
     * Description: 显示字体颜色选择器
     */
    void showFontChooser(int x, int y) {
        if (dialog == null) {
            dialog = new ImageDialog();
            FontSetPane fontPicker = new FontSetPane(sessionEditorPane);
            fontPicker.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            dialog.setSize(fontPicker.getWidth(), fontPicker.getHeight());
            dialog.setBgBorder(new Insets(0, 0, 0, 0));
            dialog.getContentPane().add(fontPicker);
            dialog.addWindowListener(new WindowAdapter() {
                public void windowDeactivated(WindowEvent e) {
                    dialog.dispose();
                    dialog = null;
                }
            });
        }
        dialog.setLocation(x + 2, y - 64);
        dialog.setVisible(true);
    }
}
