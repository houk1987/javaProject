package com.ui.jlabel;


import java.awt.*;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * 一般的JLabel虽然可以设置图片，但是不能随其尺寸变化而扩展，本类可以解决该问题
 * @author xly
 *
 */
public class ExpandableImageLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private String imageFile = null;
    public ExpandableImageLabel(String imageFile) {
        setImageFile(imageFile);
    }

    public boolean setImageFile(String imageFile){
        this.imageFile = imageFile;
        if (null == imageFile) return false;
        if (imageFile.isEmpty())return false;
        File file = new File(imageFile);
        if (!file.exists())return false;
        if (!file.isFile())return false;
        ImageIcon icon = new ImageIcon(imageFile);
        if (null == icon) return false;
        this.setIcon(icon);
        Dimension size = new Dimension(icon.getIconWidth(),icon.getIconHeight());
        this.setPreferredSize(size);
        return true;
    }

    public String getImageFile(){
        return imageFile;
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Image image = ((ImageIcon)this.getIcon()).getImage();
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        super.paint(g);
    }
}
