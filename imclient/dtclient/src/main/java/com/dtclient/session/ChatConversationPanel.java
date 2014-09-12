
package com.dtclient.session;





import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class ChatConversationPanel extends JScrollPane {
    /**
     * 会话时显示的主体富文本窗口
     */
    private static final long serialVersionUID = 1L;
    public final JTextPane chatTextPane = new JTextPane();
    private final HTMLEditorKit editorKit;
    public HTMLDocument hdocument;
    private Container chatContainer;
    private String imagePath;
    boolean isDisPlayDate = false;

    //消息类型
    public static enum inoroutEnum {
        in, out, notice
    }

    private final Runnable scrollToBottomRunnable = new Runnable() {
        public void run() {
            JScrollBar verticalScrollBar = getVerticalScrollBar();
            if (verticalScrollBar != null)
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        }
    };

    public ChatConversationPanel(Container chatContainer) {
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.chatContainer = chatContainer;
        editorKit = new HTMLEditorKit();
        this.hdocument = (HTMLDocument) editorKit.createDefaultDocument();
        this.chatTextPane.setEditorKitForContentType("text/html", editorKit);
        this.chatTextPane.setEditorKit(editorKit);
        this.chatTextPane.setEditable(false);
        this.chatTextPane.setDocument(hdocument);
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
            hdocument
                    .setInnerHTML(
                            hdocument.getDefaultRootElement(),
                            "<body><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"TABLE-LAYOUT: fixed;WORD-WRAP: break-word\"><tr id='start'><td> </td></tr> <tr id='end'><td> &nbsp;</td></tr></table></body>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(scrollToBottomRunnable);
    }

    public void insertMessage(String html) {
        Element end = hdocument.getElement("end");
        try {
            hdocument.insertBeforeStart(end, html);
            SwingUtilities.invokeLater(scrollToBottomRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取消息输入框中的纯文本
     *
     * @return
     */
    public String getPlainText() {
        try {
            Document doc = chatTextPane.getDocument();
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearContent() throws Exception {
        hdocument.setInnerHTML(hdocument.getDefaultRootElement(),
                "<body><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tr id='start'><td></td></tr><tr id='end'><td></td></tr></table></body>");
    }

    //获得选中的内容
    private String getSelectedContent() {
        StringSelection ss = new StringSelection(chatTextPane.getSelectedText());
        String selectedContent = null;
        try {
            selectedContent = (String) ss
                    .getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedContent;
    }
}
