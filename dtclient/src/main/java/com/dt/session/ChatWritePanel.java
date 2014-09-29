package com.dt.session;


import com.dt.sys.SysProperties;
import com.imService.message.FontStyle;
import com.imService.message.SessionMessage;
import com.ui.JTextField.JTextPaneHelper;
import com.ui.session.WrapLetterHTMLEditorKit;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class ChatWritePanel extends JScrollPane implements HyperlinkListener, MouseListener, MouseMotionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private HTMLDocument hdocument;
    public JTextPane chatWriteTextPane = new JTextPane();
    private HTMLEditorKit editorKit;
    private int lastCaretPoint;
    private SessionMessage sessionMessage;
    private MutableAttributeSet attr = new SimpleAttributeSet();
    public ChatWritePanel(Container chatContainer) {
        layoutPane();
        initSendMessage();
        initMessageEditorTxtPane();
        updateMessageTxtFormat(sessionMessage.getFontStyle());
    }

    /**
     * 面板的布局设置
     */
    private void layoutPane() {
        System.setProperty("java.awt.im.style", "on-the-spot");
        this.setWheelScrollingEnabled(true);
        this.setBorder(null);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        final int chatAreaSize = 60;
        Dimension writeMessagePanelDefaultSize = new Dimension(500, (chatAreaSize > 0) ? chatAreaSize : 28);
        Dimension writeMessagePanelMinSize = new Dimension(500, 28);
        Dimension writeMessagePanelMaxSize = new Dimension(500, 100);
        setMinimumSize(writeMessagePanelMinSize);
        this.setMaximumSize(writeMessagePanelMaxSize);
        this.setPreferredSize(writeMessagePanelDefaultSize);
    }

    /**
     * 初始化将要发送的消息对象
     */
    private void initSendMessage() {
        FontStyle fontStyle = new FontStyle();
        fontStyle.setFontStyleName(SysProperties.getFontFamily());
        fontStyle.setItalic(SysProperties.isFontItalic());
        String[] rgb = SysProperties.getFontColor().split("%");
        int r = Integer.valueOf(rgb[0]);
        int g = Integer.valueOf(rgb[1]);
        int b = Integer.valueOf(rgb[2]);
        fontStyle.setColor(new Color(r,g,b));
        fontStyle.setBold(SysProperties.isFontBold());
        fontStyle.setSize(Integer.valueOf(SysProperties.getFontSize()));

        sessionMessage = new SessionMessage("", "", "", fontStyle);
    }

    public SessionMessage getSessionMessage() {
        sessionMessage.setContent(getChatWriteHTMLContent(false));
        return sessionMessage;
    }

    public String getChatWriteHTMLContent(boolean isDisplayLink) {
        String content = "";
        try {
            this.chatWriteTextPane.selectAll();
            this.chatWriteTextPane.setCharacterAttributes(attr, false);
            String fileURI = new File(new File(".").getCanonicalPath()).toURI().toString();
            Element e = ((HTMLDocument) chatWriteTextPane.getDocument()).getDefaultRootElement();
            content = JTextPaneHelper.getHtml(chatWriteTextPane, e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }

    /**
     * 初始化消息内容比编辑面板
     */
    private void initMessageEditorTxtPane() {
        this.editorKit = new WrapLetterHTMLEditorKit();
        this.hdocument = (HTMLDocument) editorKit.createDefaultDocument();
        this.chatWriteTextPane.setEditorKitForContentType("text/html", editorKit);
        this.chatWriteTextPane.setEditorKit(editorKit);
        this.chatWriteTextPane.setEditable(true);
        this.chatWriteTextPane.setDocument(hdocument);
        this.chatWriteTextPane.setDragEnabled(true);
        this.chatWriteTextPane.setCaretPosition(0);
        this.chatWriteTextPane.requestFocus();
        this.chatWriteTextPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.chatWriteTextPane.updateUI();
        this.chatWriteTextPane.addHyperlinkListener(this);
        this.chatWriteTextPane.addMouseListener(this);
        this.chatWriteTextPane.addMouseMotionListener(this);
        this.chatWriteTextPane.addKeyListener(this);
        this.setViewportView(chatWriteTextPane);
    }

    /**
     * 更新消息体的格式
     * 包括字体样式，颜色
     */
    public void updateMessageTxtFormat(FontStyle fontStyle) {
        setFontFamily(fontStyle.getFontStyleName()); //设置字体样式
        setFontSize(fontStyle.getSize()); //设置字体大小
        setFontColor(fontStyle.getColor());
        setFontBold(fontStyle.isBold());
        setFontItalic(fontStyle.isItalic());
    }

    /**
     * 设置字体类型
     *
     * @param fontFamily
     */
    public void setFontFamily(String fontFamily) {
        StyleConstants.setFontFamily(attr, fontFamily);
        setCharacterAttributes(chatWriteTextPane, attr, false);
        sessionMessage.getFontStyle().setFontStyleName(fontFamily);
    }

    /**
     * 设置字体大小
     *
     * @param size
     */
    public void setFontSize(int size) {
        StyleConstants.setFontSize(attr, size);
        setCharacterAttributes(chatWriteTextPane, attr, false);
        sessionMessage.getFontStyle().setSize(size);
        SysProperties.setFontSize(String.valueOf(size));
    }

    /**
     * 设置字体颜色
     * @param color
     */
    public void setFontColor(Color color){
        StyleConstants.setForeground(attr,color);
        setCharacterAttributes(chatWriteTextPane, attr, false);
        sessionMessage.getFontStyle().setColor(color);
    }

    /**
     * 设置字体为粗体
     * @param isBold
     */
    public void setFontBold(boolean isBold){
        StyleConstants.setBold(attr,isBold);
        setCharacterAttributes(chatWriteTextPane, attr, false);
        sessionMessage.getFontStyle().setBold(isBold);
        SysProperties.setFontBold(String.valueOf(isBold));
    }


    /**
     * 设置字体为为斜体
     * @param isItalic
     */
    public void setFontItalic(boolean isItalic){
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setItalic(attr,isItalic);
        setCharacterAttributes(chatWriteTextPane, attr, false);
        sessionMessage.getFontStyle().setItalic(isItalic);
        SysProperties.setFontItalic(String.valueOf(isItalic));
    }

    private static final void setCharacterAttributes(JEditorPane editor,
                                                     AttributeSet attr, boolean replace) {
        int p0 = editor.getSelectionStart();
        int p1 = editor.getSelectionEnd();
        if (p0 != p1) {
            StyledDocument doc = getStyledDocument(editor);
            doc.setCharacterAttributes(p0, p1 - p0, attr, replace);
        }

        StyledEditorKit k = getStyledEditorKit(editor);
        MutableAttributeSet inputAttributes = k.getInputAttributes();
        if (replace) {
            inputAttributes.removeAttributes(inputAttributes);
        }
        inputAttributes.addAttributes(attr);
    }

    protected static final StyledDocument getStyledDocument(JEditorPane e) {
        Document d = e.getDocument();
        if (d instanceof StyledDocument) {
            return (StyledDocument) d;
        }
        throw new IllegalArgumentException("document must be StyledDocument");
    }

    protected static final StyledEditorKit getStyledEditorKit(JEditorPane e) {
        EditorKit k = e.getEditorKit();
        if (k instanceof StyledEditorKit) {
            return (StyledEditorKit) k;
        }
        throw new IllegalArgumentException("EditorKit must be StyledEditorKit");
    }

    /**
     * 插入表情
     *
     * @param emotionPath
     * @throws Exception
     */
    public void insertEmotion(String emotionPath) throws Exception {
        saveCaretPosition();
        String emotionPathURI = new File(emotionPath).toURI().toString();
        HTMLEditorKit kit = (HTMLEditorKit) chatWriteTextPane.getEditorKit();
        HTMLDocument doc1 = (HTMLDocument) chatWriteTextPane.getDocument();
        String str = getPlainText();
        // zbq 修改了7805bug: 2012-3-8 会话窗口插入表情图片，显示有问题
        if ("".equals(str)) {
            kit.insertHTML(doc1, chatWriteTextPane.getCaretPosition(), "<p style=\"margin-top: 0\"><img src= \"" + emotionPathURI
                    + "\" width=\"24\" height=\"24\"/></p>", 0, 0, HTML.Tag.IMG);
        } else if (str.lastIndexOf("\n") == str.length() - 1 && chatWriteTextPane.getCaretPosition() == str.length()) {
            // 新换行
            kit.insertHTML(doc1, chatWriteTextPane.getCaretPosition(), "<p style=\"margin-top: 0\"><img src= \"" + emotionPathURI
                    + "\" width=\"24\" height=\"24\"/></p>", 0, 0, null);
            if (str.length() != 0) {
                // 该语句为了移除末尾多出来的一个字符，如果聊天框是空，怎不会多出来，不移除。
                chatWriteTextPane.getDocument().remove(str.length() + 1, 1);
            }
        } else {
            // 除新换行以外
            kit.insertHTML(doc1, chatWriteTextPane.getCaretPosition(), "<img src= \"" + emotionPathURI + "\" width=\"24\" height=\"24\"/>",
                    0, 0, HTML.Tag.IMG);
        }

        chatWriteTextPane.setCaretPosition(getCurrentCaretPosition());
        chatWriteTextPane.requestFocus();
    }


    /**
     * 得到当前富文本编辑器的光标所在位置
     *
     * @return
     */
    public int getCurrentCaretPosition() {
        int pos = 0;
        try {
            pos = this.chatWriteTextPane.getCaretPosition();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pos;
    }

    /**
     * 获取消息输入框中的纯文本
     *
     * @return
     */
    public String getPlainText() {
        try {
            Document doc = chatWriteTextPane.getDocument();
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * **********************************************************************************************************
     * 点击表情按钮后先保存富文本编辑器的焦点位置
     * ****************************************************
     * *******************************************************
     */
    public void saveCaretPosition() {
        lastCaretPoint = this.getCurrentCaretPosition();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        this.chatWriteTextPane.repaint();
    }

    public void keyReleased(KeyEvent e) {
        String text = getPlainText();
        int index = text.indexOf("按键提示：Enter发送 , Ctrl+Enter换行 , Alt+Enter切换发送和换行热键");
        if (index > 0) {
            text = text.substring(0, index);
            chatWriteTextPane.setText(text);
        }
    }


    public void mouseClicked(MouseEvent e) {
        this.chatWriteTextPane.requestFocus();
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        this.chatWriteTextPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }


    private void removeSpace() {
        String str = getPlainText();
        if (str.lastIndexOf("\n") == str.length() - 1 && str.length() == 1) {
            try {
                chatWriteTextPane.getDocument().remove(0, 1);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }





    @Override
    public void updateUI() {
        super.updateUI();
    }

    /**
     * 调用startNewDocument()方法，清除当前文本，开始一个新文档
     */
    public void clear() {
        startNewDocument();
    }

    /**
     * 开始构建一个新的文档
     */
    public void startNewDocument() {
        Document oldDoc = chatWriteTextPane.getDocument();
        HTMLEditorKit editorKit = new HTMLEditorKit();
        hdocument = (HTMLDocument) editorKit.createDefaultDocument();
        chatWriteTextPane.setDocument(hdocument);
        removeSpace();
    }


    /**
     * 这里重新父类的方法，目的是为了显示设置滚动条的位置，不然，最后的字显示不全
     * zbq 2013-4-25 修改7898bug:会话窗口编辑框，输入的字符，最下面有一点没有显示出来
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.getVerticalScrollBar().isVisible())
            this.getVerticalScrollBar().setValue(this.getVerticalScrollBar().getMaximum());
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {

    }
}
