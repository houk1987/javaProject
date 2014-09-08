package com.ui.checkBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 356 on 14-8-31.
 */
public class FreeCheckBox extends JCheckBox {

    public FreeCheckBox(String string,ImageIcon icon) {
        super(string);
        this.setBorder(null);
        this.setOpaque(false);
        this.setIcon(icon);
        String iconPath = icon.getDescription();
        System.out.println(iconPath);
        this.setSize(icon.getIconWidth(),icon.getIconHeight());
//        this.setRolloverIcon(new ImageIcon(currentlySkinPath+"/common/button/uncheckedHover.png"));
//        this.setSelectedIcon(new ImageIcon(currentlySkinPath+"/common/button/checked.png"));
        this.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {
                if(FreeCheckBox.this.isSelected()){
                    FreeCheckBox.this.setSelectedIcon(null);
                   // FreeCheckBox.this.setIcon(new ImageIcon(currentlySkinPath+"/common/button/checkedHover.png"));
                }else {
                 //   FreeCheckBox.this.setIcon(new ImageIcon(currentlySkinPath+"/common/button/uncheckedHover.png"));
                }

            }


            public void mouseExited(MouseEvent e) {
                if(FreeCheckBox.this.isSelected()){
                   // FreeCheckBox.this.setIcon(new ImageIcon(currentlySkinPath+"/common/button/checked.png"));
                }else{
                 //   FreeCheckBox.this.setIcon(new ImageIcon(currentlySkinPath+"/common/button/unchecked.png"));
                }

            }


            public void mouseClicked(MouseEvent e) {
                if(FreeCheckBox.this.isSelected()){
                    FreeCheckBox.this.setFocusPainted(true);
                    //FreeCheckBox.this.setSelectedIcon(new ImageIcon(currentlySkinPath+"common/button/checked.png"));
                }else{
                    FreeCheckBox.this.setFocusPainted(false);
                }
            }
        });
    }

    public void setSelectState(boolean selete){
        setSelected(selete);
        if(selete){
           // FreeCheckBox.this.setIcon(new ImageIcon(currentlySkinPath+"/common/button/checked.png"));
        }else{
           // FreeCheckBox.this.setIcon(new ImageIcon(currentlySkinPath+"/common/button/unchecked.png"));
        }
    }


}
