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
	private LinkedHashMap<String, ImageIcon> imageMap = new LinkedHashMap<String, ImageIcon>();// 取得图片的
    private SessionEditorPane sessionEditorPane;
	public EmoticonUI(final SessionEditorPane sessionEditorPane) {
        this.sessionEditorPane = sessionEditorPane;
		setBackground(Color.white);// 设置背景颜色为白色
		setLayout(new GridLayout(0, 7));// 设置一行显示7个
		Map<String, ImageIcon> imageMap = this.getImageIconMap();
		for (Iterator iter = imageMap.keySet().iterator(); iter.hasNext();) {
			final String key = (String) iter.next();
			final ImageIcon icon = imageMap.get(key);// 获取表情的图片
			int dotShowIndex = key.lastIndexOf(".");
			String tipText = key.substring(2, dotShowIndex);// 取得表情中文提示
			RolloverButton emotButton = new RolloverButton(icon);// 创建表情按钮
			emotButton.setToolTipText(tipText);// 设置表情按钮提示
			emotButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String emotionPath = "";// 返回图片的绝对路径
					try {
						// 当用户选择了表情，则将表情界面关闭
						((RolloverButton) e.getSource()).getParent()
								.setVisible(false);// 设置Jpanel 不显示
						((RolloverButton) e.getSource()).getParent()
								.getParent().setVisible(false);// 设置JPopupMenu
																// 容器不显示

						/********** 获取调用面板的方法将数据穿过去 *********************/
						Container c = ((RolloverButton) e.getSource())
								.getParent().getParent();
						JPopupMenu p = (JPopupMenu) c;// 获取表情所在的菜单
						Component Combutton = p.getInvoker();// 取得调用菜单的组件
						JButton button = (JButton) Combutton;// 转换成按钮组件

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
							Color.blue));// 设置Jpanel 不显示

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
	 * 取得所有表情的集合
	 * 
	 * @return the active set of emoticons.
	 */
	private Map<String, ImageIcon> getImageIconMap() {
		File emoticonFile = new File(SysProperties.sessionFrameButtonPath()+"emotion/");// 获取照片所在的文件夹
		File[] emoticonFileList = emoticonFile.listFiles();// 取得表情文件下所有表情图片的集合
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
