package com.dt.session;





import com.dt.sys.SysProperties;
import com.ui.button.RolloverButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmoticonUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, ImageIcon> imageMap = new LinkedHashMap<String, ImageIcon>();// ȡ��ͼƬ��
    private SessionEditorPane sessionEditorPane;
	public EmoticonUI(final SessionEditorPane sessionEditorPane) {
        this.sessionEditorPane = sessionEditorPane;
		setBackground(Color.white);// ���ñ�����ɫΪ��ɫ
		setLayout(new GridLayout(0, 7));// ����һ����ʾ7��
		Map<String, ImageIcon> imageMap = this.getImageIconMap();
		for (Iterator iter = imageMap.keySet().iterator(); iter.hasNext();) {
			final String key = (String) iter.next();
			final ImageIcon icon = imageMap.get(key);// ��ȡ�����ͼƬ
			int dotShowIndex = key.lastIndexOf(".");
			String tipText = key.substring(2, dotShowIndex);// ȡ�ñ���������ʾ
			RolloverButton emotButton = new RolloverButton(icon);// �������鰴ť
			emotButton.setToolTipText(tipText);// ���ñ��鰴ť��ʾ
			emotButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String emotionPath = "";// ����ͼƬ�ľ���·��
					try {
						// ���û�ѡ���˱��飬�򽫱������ر�
						((RolloverButton) e.getSource()).getParent()
								.setVisible(false);// ����Jpanel ����ʾ
						((RolloverButton) e.getSource()).getParent()
								.getParent().setVisible(false);// ����JPopupMenu
																// ��������ʾ

						/********** ��ȡ�������ķ��������ݴ���ȥ *********************/
						Container c = ((RolloverButton) e.getSource())
								.getParent().getParent();
						JPopupMenu p = (JPopupMenu) c;// ��ȡ�������ڵĲ˵�
						Component Combutton = p.getInvoker();// ȡ�õ��ò˵������
						JButton button = (JButton) Combutton;// ת���ɰ�ť���

						String currentDirPath = new File(".")
								.getCanonicalPath();
						emotionPath = currentDirPath
								+ "\\"
								+ ((RolloverButton) e.getSource()).getIcon()
										.toString();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                    //Session.getInstance().getSessionFrame("1").insertEmotion(emotionPath);
                    try {
                        sessionEditorPane.getRichEditor().insertEmotion(emotionPath);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
			});
			emotButton.addMouseMotionListener(new MouseMotionListener() {

				
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub
					((RolloverButton) e.getSource()).setBorder(new LineBorder(
							Color.blue));// ����Jpanel ����ʾ

				}
				
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					String currentDirPath = "111";
					System.out.print("");

				}
			});
			emotButton.addMouseListener(new MouseAdapter(){
				public void mouseExited(MouseEvent e) {
					((RolloverButton) e.getSource()).setBorderPainted(false);
					((RolloverButton) e.getSource()).setContentAreaFilled(false);
				}
			});
				
				
			
			add(emotButton);
		}
	}

	/**
	 * ȡ�����б���ļ���
	 * 
	 * @return the active set of emoticons.
	 */
	private Map<String, ImageIcon> getImageIconMap() {
		File emoticonFile = new File(SysProperties.sessionFrameButtonPath()+"emotion/");// ��ȡ��Ƭ���ڵ��ļ���
		File[] emoticonFileList = emoticonFile.listFiles();// ȡ�ñ����ļ������б���ͼƬ�ļ���
		for (int i = 0; i < emoticonFileList.length; i++) {
			String key = emoticonFileList[i].getName();
			ImageIcon icon = null;
			if(key.endsWith("png")){
				icon = new ImageIcon(emoticonFileList[i].getPath());
				imageMap.put(key, icon);
			}
		}
		return imageMap;
	}
}
