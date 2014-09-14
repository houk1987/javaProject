package com.yh.notify;


import com.san30.pub.tools.SanHttpClient;
import com.ui.jlabel.JLabelFactory;
import com.ui.notify.NotifyWindow;
import com.ui.session.WrapLetterHTMLEditorKit;
import com.yh.manager.YhManager;
import sys.SysProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * Created by a on 2014/9/9.
 */
public class AcceptNewContactDialog extends NotifyWindow {

    private JLabel linkLabel;
    private JLabel closeLabel;
    private JEditorPane content;

    private String from;
    public AcceptNewContactDialog(String from) {
        super();
        setTitle("好友申请");
        this.from = from;
        setContent(from+"想申请您为好友!",10,10);
        setSize(220, 151);
    }

    @Override
    protected JPanel initContentPane() {
        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(Color.WHITE);
        String url = null;
        Font font = new Font("宋体",Font.PLAIN,13);
        linkLabel = JLabelFactory.createLinkLabel("同意",font,"#0000FF",url);
        linkLabel.setBounds(20,70,50,23);
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HashMap<String,String> paramMap = new HashMap<String, String>();
                paramMap.put("jid",from);
                paramMap.put("targetAccount", YhManager.getInstance().getLoginAccount());
                try {
                    String URL = "http://" + SysProperties.getHost() + ":" + 9090 + "/plugins/updserver/contactok";
                    SanHttpClient.getDataAsString(URL, paramMap);
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        closeLabel = JLabelFactory.createLinkLabel("拒绝",font,"#0000FF",null);
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        closeLabel.setBounds(80,70,50,23);
        Font font1 = new Font("宋体",Font.PLAIN,13);
        content = new JEditorPane();
        content.setEditable(false);
        content.setEditorKit(new WrapLetterHTMLEditorKit());
        content.setSize(new Dimension(193, 88));

        contentPane.add(linkLabel);
        contentPane.add(closeLabel);
        contentPane.add(content);
        return contentPane;
    }


    /**
     * @Title:setContent
     * @Description: 设置消息提示内容
     * @author houk
     * @date 2012-6-21 下午5:40:02
     */
    public void setContent(String contentStr, int x, int y) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("<body><table  width=\"100%\" height=\"100%\"><tr  height=\"200\"><td valign=\"middle\" height=\"80\" style=\"repeat-x;line-height:23pt; font-size:14pt\"> ");
//        stringBuffer.append(contentStr);
//        stringBuffer.append("</td></tr></table></body>");
        this.content.setText(new StringBuffer().append("<html><font size=").append(5).append(">")
                .append(contentStr).append("</font></html>").toString());
        content.setLocation(x, y + 2);

        this.add(content);
    }
}
