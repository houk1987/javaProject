package com.ui.session;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

/**
 * Created by a on 2014/8/22.
 * 信息显示面板
 */
public class ChatDisplayPane extends JScrollPane {
    public final JTextPane chatTextPane = new JTextPane();
    private final HTMLEditorKit editorKit;
    public HTMLDocument document;

    private final Runnable scrollToBottomRunnable = new Runnable() {
        public void run() {
            JScrollBar verticalScrollBar = getVerticalScrollBar();
            if (verticalScrollBar != null)
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        }
    };

    public ChatDisplayPane() {
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        editorKit = new HTMLEditorKit();
        this.document = (HTMLDocument) editorKit.createDefaultDocument();
        this.chatTextPane.setEditorKitForContentType("text/html", editorKit);
        this.chatTextPane.setEditorKit(editorKit);
        this.chatTextPane.setEditable(false);
        this.chatTextPane.setDocument(document);
        this.chatTextPane.setDragEnabled(true);
        chatTextPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES,
                Boolean.TRUE);
        this.chatTextPane.updateUI();

        this.chatTextPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.setWheelScrollingEnabled(true);
        this.setViewportView(chatTextPane);
        chatTextPane.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder());
        try {
            document
                    .setInnerHTML(
                            document.getDefaultRootElement(),
                            "<body><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"TABLE-LAYOUT: fixed;WORD-WRAP: break-word\"><tr id='start'><td> </td></tr> <tr id='end'><td> &nbsp;</td></tr></table></body>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertMessage(String html) {
        Element end = document.getElement("end");
        try {
            document.insertBeforeStart(end, html);
            SwingUtilities.invokeLater(scrollToBottomRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
