package com.imService.message;

import java.awt.*;

/**
 * Created by a on 2014/9/4.
 *
 */
public class FontStyle {
    private Color color; //字体颜色
    private int size=10; //字体大小
    private boolean bold; //是否粗体
    private boolean italic;//是否斜体
    private String fontStyleName="宋体"; //字体样式名称

    public FontStyle(){

    }

    public FontStyle setColor(Color color){
        this.color = color;
        return this;
    }

    public FontStyle setSize(int size){
        this.size = size;
        return this;
    }

    public FontStyle setBold(boolean isBold){
        this.bold = isBold;
        return this;
    }

    public FontStyle setItalic(boolean isItalic){
        this.italic = isItalic;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public String getFontStyleName() {
        return fontStyleName;
    }

    public FontStyle setFontStyleName(String fontStyleName) {
        this.fontStyleName = fontStyleName;
        return this;
    }
}
