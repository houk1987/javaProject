package com.yh.button;

import javax.swing.*;

/**
 * Created by a on 2014/7/31.
 */
public class CustomButtonFactory {

    private static final String windowButtonPath = "res/button/window/";
    private static final String sessionButtonPath = "res/button/session/";
    private static final String contactButtonPath = "res/main/addContact/";

    public static JButton createCloseButton(){return ButtonFactory.createButton(windowButtonPath, "关闭", "closed.png"); }

    public static JButton createMinButton(){
        return ButtonFactory.createButton(windowButtonPath, "最小化", "min.png");
    }

    public static JButton createMaxButton(){
        return ButtonFactory.createButton(windowButtonPath, "最大化", "max.png");
    }

    public static JButton createReturnButton(){return ButtonFactory.createButton(windowButtonPath, "还原", "return.png");}

    public static JButton createFontButton() {
        return ButtonFactory.createButton(sessionButtonPath, "字体", "font.png");
    }

    public static JButton createFileTransferButton() {return ButtonFactory.createButton(sessionButtonPath, "文件传输", "transfer.png");}

    public static JButton createSendButton() {
        return ButtonFactory.createButton(sessionButtonPath, "发送", "send.png");
    }

    public static JButton createCloseFrameButton() {return ButtonFactory.createButton(sessionButtonPath, "关闭", "close.png"); }

    public static JButton createFaceButton() {return ButtonFactory.createButton(sessionButtonPath, "表情", "face.png");}

    public static JButton createBoldButton() {
        return ButtonFactory.createButton(sessionButtonPath, "粗体", "bold.png");
    }

    public static JButton createItalicButton() {return ButtonFactory.createButton(sessionButtonPath, "斜体", "italic.png");}

    public static JButton createCancelButton() {
        return ButtonFactory.createButton(contactButtonPath, "取消", "cancel.png");
    }

    public static JButton createNextButton() {
        return ButtonFactory.createButton(contactButtonPath, "下一步", "next.png");
    }

    public static JButton createPreviousButton() {
        return ButtonFactory.createButton(contactButtonPath, "上一步", "previous.png");
    }




    public static JButton createAudiblesButton(){
        return ButtonFactory.createButton(sessionButtonPath,"聲色圖案","audibles.png");
    }

    public static JButton createBuzzButton(){
        return ButtonFactory.createButton(sessionButtonPath,"呼叫朋友","buzz.png");
    }

    public static JButton createScreenButton(){
        return ButtonFactory.createButton(sessionButtonPath,"","screenCapture.png");
    }

    public static JButton createDisCloseButton() {
        return ButtonFactory.createButton(sessionButtonPath,"","dispImgToggleClose.png");
    }

    public static JButton createDisOpenButton() {
        return ButtonFactory.createButton(sessionButtonPath,"","dispImgToggleOpen.png");
    }

    public static JButton createAddBuddyButton() {
        return ButtonFactory.createButton(contactButtonPath,"","AddBuddy.png");
    }

    public static JButton createFinishButton() {
        return ButtonFactory.createButton(contactButtonPath,"","finish.png");
    }

}
