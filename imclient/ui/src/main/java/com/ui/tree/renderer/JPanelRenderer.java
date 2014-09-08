/**
 * $RCSfile: ,v $
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2004-2011 Jive Software. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ui.tree.renderer;


import javax.swing.*;
import java.awt.*;

/**
 * The <code>JPanelRenderer</code> is the an implementation of ListCellRenderer
 * to add an entire panel ui to lists.
 *
 * @author Derek DeMoro
 */
public class JPanelRenderer extends JPanel implements ListCellRenderer {
    private static final long serialVersionUID = -8968866073878932932L;

    /**
     * Construct Default JPanelRenderer.
     */
    public JPanelRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  final boolean isSelected,
                                                  boolean cellHasFocus) {
        JPanel panel = (JPanel)value;
        panel.setFocusable(false);

        if (isSelected) {
            panel.setForeground((Color)UIManager.get("List.selectionForeground"));
            panel.setBackground(new Color(197,148,193));
            panel.setBorder(BorderFactory.createLineBorder((Color)UIManager.get("List.selectionBorder")));
        }
        else {
            panel.setBackground(Color.WHITE);
            panel.setForeground(list.getForeground());
            panel.setBorder(BorderFactory.createLineBorder((Color)UIManager.get("ContactItem.background")));
        }

       list.setBackground((Color)UIManager.get("ContactItem.background"));
       panel.setBorder(null);
       return panel;
    }
}

