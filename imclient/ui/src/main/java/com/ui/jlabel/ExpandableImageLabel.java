package com.ui.jlabel;


import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * 一般的JLabel虽然可以设置图片，但是不能随其尺寸变化而扩展，本类可以解决该问题
 * @author xly
 *
 */
public class ExpandableImageLabel extends JLabel {
	private static final long serialVersionUID = 1L;

    private ImageIcon imageIcon;
	public ExpandableImageLabel(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon){
        this.imageIcon = imageIcon;
    }
	

	

	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = null;
		if( g instanceof Graphics2D)
			 g2d = (Graphics2D) g;
		else 
			return;
        if(imageIcon == null)return;
		g2d.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
	    super.paint(g);
	}
}
