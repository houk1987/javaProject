package com.ui.checkBox;

import javax.swing.*;

/**
 * Created by 356 on 14-8-31.
 */
public class CheckBoxFactory {

    public static FreeCheckBox createJCheckBox(String text,ImageIcon imageIcon,boolean state,boolean enabled){
        FreeCheckBox jCheckBox = new FreeCheckBox(text,imageIcon);
        jCheckBox.setSelectState(state);
//        jCheckBox.setEnabled(enabled);
        return jCheckBox;
    }
}
