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
package com.ui.tree.panes;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

/**
 * Internal implementation of the Title pane in the northern region of a CollapsiblePane.
 *
 * @author Derek DeMoro
 */
public class CollapsibleTitlePane extends BaseCollapsibleTitlePane {
	private static final long serialVersionUID = 2528585101535037612L;
	private JLabel titleLabel;
    private JLabel iconLabel;

    private JLabel preIconLabel;

    private boolean collapsed;

    private Color startColor;
    private Color endColor;

    private Color titleColor;

    private boolean subPane;

    private Image backgroundImage;

    public CollapsibleTitlePane() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        titleColor = new Color(62,38,89);
        Font titleFont = new Font("Dialog", Font.BOLD, 12);

        // Initialize color
        startColor = new Color(238,242,253);
        endColor = Color.white;
        titleLabel = new JLabel();

        iconLabel = new JLabel();

        preIconLabel = new JLabel();

        add(preIconLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 2, 0), 0, 0));
        add(titleLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 2), 0, 0));
        add(iconLabel, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 2), 0, 0));

        setCollapsed(false);

        Color customTitleColor = (Color)UIManager.get("CollapsiblePane.titleColor");
        if (customTitleColor != null) {
            titleLabel.setForeground(customTitleColor);
        }
        else {
            titleLabel.setForeground(titleColor);
        }

        titleLabel.setFont(titleFont);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

    }

    public void setStartColor(Color color) {
        // Initialize color
        startColor = color;
    }

    public void setEndColor(Color color) {
        endColor = color;
    }

    public void setIcon(Icon icon) {
        titleLabel.setIcon(icon);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;

        if (!isSubPane()) {

            if (!collapsed) {
                preIconLabel.setIcon(new ImageIcon("res/main/tree/blueArrowDown.png"));
            }
            else {
                preIconLabel.setIcon(new ImageIcon("res/main/tree/blueArrowRight.png"));
            }
        }
        else {
            iconLabel.setIcon(null);
//            if (collapsed) {
//                preIconLabel.setIcon(SparkRes.getImageIcon(SparkRes.PLUS_SIGN));
//            }
//            else {
//                preIconLabel.setIcon(SparkRes.getImageIcon(SparkRes.MINUS_SIGN));
//            }
            if (collapsed) {
                preIconLabel.setIcon(new ImageIcon("res/main/tree/blueArrowDown.png"));
            }
            else {
                preIconLabel.setIcon(new ImageIcon("res/main/tree/blueArrowRight.png"));
            }
        }
    }

    public void setTitleColor(Color color) {
        titleColor = color;

        titleLabel.setForeground(color);
    }

    public Color getTitleColor() {
        return titleColor;
    }

    protected boolean isSubPane() {
        return subPane;
    }

    @Override
    public void setSubPane(boolean subPane) {
        this.subPane = subPane;
        setCollapsed(isCollapsed());
    }

    public static Color getColor(String commaColorString) {
        Color color;
        try {
            StringTokenizer tkn = new StringTokenizer(commaColorString, ",");
            color = new Color(Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken()));
        }
        catch (NumberFormatException e1) {
            return Color.white;
        }
        return color;
    }

    public void setTitleForeground(Color color) {
        titleLabel.setForeground(color);
    }

    public void useImageAsBackground(Image image) {
        this.backgroundImage = image;
    }
}
