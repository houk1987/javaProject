package com.ui.JTextField;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * HTML显示控件的帮忙类。主要提供针对JEditorPane的操作函数。
 * @author 刘福强 20111014
 */

public class JTextPaneHelper { 
	 private static final Pattern URL_PATTERN
     = Pattern.compile(
         "("
         + "((http|ftp|https)://[a-zA-Z_0-9]*\\.([a-zA-Z_0-9[:&=;?/\\.%-]])*)" // wwwURL
         + "|"
         + "((http|ftp|https)://[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}[:a-zA-Z_0-9[&=;?/\\.%-]]*)"
//         + "|"+
//         + "(\\b\\w+://[^\\s<>\"]+/*[?#]*(\\w+[&=;?]\\w+)*\\b)" // protocolURL
         + ")");
	/**
	 * 
	 * 取JEditorPane对象中的HTML代码。
	 * 要求JEditorPane中内容必须是"text/html"格式，函数中未进行格式判断，如果不是，会出现类型转换错。
	 * @param editor JEditorPane对象
	 * @return html字符串
	 * @throws java.io.IOException
	 * @throws javax.swing.text.BadLocationException
	 */
	public static String getHtml(JTextPane editor){
		// 取文档对象。这里认为一定是HTML格式文档。
		HTMLDocument hdoc = (HTMLDocument)editor.getDocument();
		// 创建内存输出流对象接收HTML数据。
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		OutputStream os = new BufferedOutputStream(baos);
		// 取JDK中HTMLEditor的工具对象，并输出数据
		HTMLEditorKit editorKit = (HTMLEditorKit)editor.getEditorKit();
		try{
			editorKit.write(os, hdoc, 0, hdoc.getLength());
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return baos.toString();
	}
	public static String getHtml(JEditorPane editor, Element element){
		// 取文档对象。这里认为一定是HTML格式文档。
		HTMLDocument hdoc = (HTMLDocument)editor.getDocument();
		// 创建内存输出流对象接收HTML数据。
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		OutputStream os = new BufferedOutputStream(baos);
		// 取JDK中HTMLEditor的工具对象，并输出数据
		HTMLEditorKit editorKit = (HTMLEditorKit)editor.getEditorKit();
		try {
			editorKit.write(os, hdoc, element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return baos.toString().replaceAll("<html>", "").replaceAll("</html>", "").replaceAll("<body>", "").replaceAll("</body>", "");
	}
	public static String getText(Element element){
		// 取文档对象。这里认为一定是HTML格式文档。
		HTMLDocument hdoc = (HTMLDocument)element.getDocument();
		try {
			return hdoc.getText(element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
		} catch (BadLocationException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getText(JEditorPane editor, String id) throws BadLocationException{
		// 取文档对象。这里认为一定是HTML格式文档。
		HTMLDocument hdoc = (HTMLDocument)editor.getDocument();
		Element element = hdoc.getElement(id);
		if (element == null){ // 未找到要求的
			return null;
		}
		return getText(element);
	}
	
	/**智能识别用户消息中的URL链接
	 * @param message
	 * @param processHTMLChars
	 * @return
	 */
	public static String processLinksAndHTMLChars(String message,boolean processHTMLChars) {
		Matcher m = URL_PATTERN.matcher(message);
		StringBuffer msgBuffer = new StringBuffer();
		int prevEnd = 0;

		while (m.find()) {
			String fromPrevEndToStart = message.substring(prevEnd, m.start());

			if (processHTMLChars)
				fromPrevEndToStart = processHTMLChars(fromPrevEndToStart);
			msgBuffer.append(fromPrevEndToStart);
			prevEnd = m.end();
			String url = m.group().trim(); 
			if(m.group().endsWith("&"))
				url = m.group().trim().substring(0, m.group().trim().length()-1); 
			msgBuffer.append("<font color =\"blue\"><A id=\"userdeflink\" href=\"");
			if (url.startsWith("www"))
				msgBuffer.append("http://");
			msgBuffer.append(url);
			msgBuffer.append("\">");
			msgBuffer.append(url);
			msgBuffer.append("</A></font>"); 
			if(m.group().endsWith("&"))
				msgBuffer.append("&");
		} 
		String fromPrevEndToEnd = message.substring(prevEnd); 
		if (processHTMLChars)
			fromPrevEndToEnd = processHTMLChars(fromPrevEndToEnd);
		msgBuffer.append(fromPrevEndToEnd);
//		System.out.println(msgBuffer.toString());
		return msgBuffer.toString();
	}
	/**
	 * 该方法主要对回复网址过滤a标签处理
	 * @param message
	 * @return
	 */
	public static String transerUrl(String message) {
		Matcher m = URL_PATTERN.matcher(message);
		StringBuffer msgBuffer = new StringBuffer();
		int prevEnd = 0;
		while (m.find()) {
			String fromPrevEndToStart = message.substring(prevEnd, m.start());
			if(fromPrevEndToStart.contains("<A id=\"userdeflink\" href=\"")){
				fromPrevEndToStart = fromPrevEndToStart.replaceAll("<A id=\"userdeflink\" href=\"", "");
				msgBuffer.append(fromPrevEndToStart);
				prevEnd = m.end();
				continue;
			}
			String url = m.group().trim();
			prevEnd = m.end();
			msgBuffer.append(url);
		}
		String fromPrevEndToEnd = message.substring(prevEnd);
		msgBuffer.append(fromPrevEndToEnd);
		return msgBuffer.toString();
	}
	/**
	 * 只能识别URL并能保持html格式不变
	 * @param message
	 * @return
	 */
	public static String processLinksAndHTMLChars(String message) {
		Matcher m = URL_PATTERN.matcher(message);
		StringBuffer msgBuffer = new StringBuffer();
		int prevEnd = 0;
		msgBuffer.append("<xmp>");
		while (m.find()) {
			String fromPrevEndToStart = message.substring(prevEnd, m.start());
			msgBuffer.append(fromPrevEndToStart);
			prevEnd = m.end();
			String url = m.group().trim(); 
			if(m.group().endsWith("&"))
				url = m.group().trim().substring(0, m.group().trim().length()-1); 
			msgBuffer.append("</xmp>");
			msgBuffer.append("<font color =\"blue\"><A id=\"userdeflink\" href=\"");
			if (url.startsWith("www"))
				msgBuffer.append("http://");
			msgBuffer.append(url);
			msgBuffer.append("\">");
			msgBuffer.append(url);
			msgBuffer.append("</A></font>"); 
			if(m.group().endsWith("&"))
				msgBuffer.append("&");
			msgBuffer.append("<xmp>");
		} 
		String fromPrevEndToEnd = message.substring(prevEnd); 
		msgBuffer.append(fromPrevEndToEnd);
		msgBuffer.append("</xmp>");
		return msgBuffer.toString();
	}
	
	private static  String processHTMLChars(String message) {
	        return
	            message
	                .replace("&", "&amp;")
	                    .replace("<", "&lt;")
	                        .replace(">", "&gt;")
	                            .replace("\"", "&quot;");
	}

  public  static void main(String[] args) throws Exception{
	  
	  JTextPaneHelper.processLinksAndHTMLChars("&#26543;<img src=\"file:/d:/pp.png\"/>&#22320;&#39318;&#21457;&#24335;http://baidu.com&#22375;http://192.168.25.100:8080/aa.jsp?id=123&sdf=23&#22763;&#22823;&#22827;&#25954;&#27515;&#38431;&#25954;&#27515;&#38431;",false);

  }
}
