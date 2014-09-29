package com.dt.ui.main.presence;


import com.dt.lanuch.DTClient;
import com.imService.presence.PresenceType;
import com.ui.jlabel.ExpandableImageLabel;
import com.ui.jlabel.JLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by a on 2014/9/1.
 */
public class PresenceSelectButton extends JPanel {

    private final String SKIN_PATH = "res/main/presenceSelectButton/";
    private JLabel leftLabel;
    private ExpandableImageLabel centerLabel;
    private JLabel rightLabel;
    private JLabel statusIconLabel;


    public PresenceSelectButton() {
        setLayout(new BorderLayout());
        setOpaque(false);
        leftLabel = JLabelFactory.createJLabel(null);
       centerLabel = new ExpandableImageLabel(null);
        rightLabel = JLabelFactory.createJLabel(null);
        add(leftLabel, BorderLayout.WEST);
        add(centerLabel, BorderLayout.CENTER);
        add(rightLabel, BorderLayout.EAST);
        //默认为在线
        statusIconLabel = JLabelFactory.createJLabel(new ImageIcon("res/status/online.png"));

        leftLabel.setLayout(new BorderLayout(3, 0));
        leftLabel.add(statusIconLabel, BorderLayout.CENTER);
        Font font = new Font("宋体", Font.BOLD, 14);
        centerLabel.setLayout(new BorderLayout());
        defaultBg();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressedBg();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hoverBg();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                defaultBg();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                StatusMenu statusMenu = new StatusMenu();
                statusMenu.show(PresenceSelectButton.this,5,20);
            }
        });
    }




    private void defaultBg() {
        switchBg(new ImageIcon(SKIN_PATH + "leftDefault.png"), new ImageIcon(SKIN_PATH + "centerDefault.png"), new ImageIcon(SKIN_PATH + "rightDefault.png"));
    }

    private void hoverBg() {
        switchBg(new ImageIcon(SKIN_PATH + "leftHover.png"), new ImageIcon(SKIN_PATH + "centerHover.png"), new ImageIcon(SKIN_PATH + "rightHover.png"));
    }

    private void pressedBg() {
        switchBg(new ImageIcon(SKIN_PATH + "leftPressed.png"), new ImageIcon(SKIN_PATH + "centerPressed.png"), new ImageIcon(SKIN_PATH + "rightPressed.png"));
    }


    private void switchBg(ImageIcon left, ImageIcon center, ImageIcon right) {
        leftLabel.setIcon(left);
        leftLabel.setSize(left.getIconWidth(), left.getIconHeight());
       // centerLabel.setImageIcon(center);
        rightLabel.setIcon(right);
        rightLabel.setSize(right.getIconWidth(), right.getIconHeight());
        setSize(100, rightLabel.getHeight());
    }



    class StatusMenu extends JPopupMenu{

        StatusMenu() {
            List<PresenceType> presenceTypeList = DTClient.getInstance().getPresenceTypes();
            for(PresenceType e : presenceTypeList){
                StatusItem menuItem = new StatusItem(e);
                add(menuItem);
            }
        }
    }

    class StatusItem extends JMenuItem{
        StatusItem(final PresenceType type) {
            this.setText(type.getPresenceName());
            this.setIcon(type.getPresenceTypeIcon());
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    statusIconLabel.setIcon(type.getPresenceTypeIcon());
                    DTClient.getInstance().changeLanderPresence(type.getPresence());
                }
            });
        }
    }
}
