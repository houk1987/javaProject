package com.yh.main.contact.addContact;

import com.yh.button.CustomButtonFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a on 2014/9/2.
 */
public class ContactPane extends JPanel implements ActionListener{
    private final static String SKIN_PATH = "res/login/";
    private ImageIcon bgImageIcon = new ImageIcon(SKIN_PATH + "loginBg.png");
    private JButton nextButton;
    private JButton cancelButton;
    private JButton finishButton;
    private JButton previousButton;
    private JPanel cardPane;
    private CardLayout cardLayout = new CardLayout();
    public ContactPane() {
        setOpaque(false);
        cardPane.setLayout(cardLayout);
        cardPane.setOpaque(false);
        setLayout(new BorderLayout());
        add(cardPane,BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        previousButton = CustomButtonFactory.createPreviousButton();
        nextButton = CustomButtonFactory.createNextButton();
        cancelButton = CustomButtonFactory.createCancelButton();
        controlPanel.add(previousButton);
        controlPanel.add(nextButton);
//        controlPanel.add(finishButton);
        controlPanel.add(cancelButton);
        controlPanel.setOpaque(false);
        controlPanel.setBorder(new EmptyBorder(0,120,0,0));
        add(controlPanel, BorderLayout.PAGE_END);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Image image = bgImageIcon.getImage();
        g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton){
           // YhManager.getAddContactManager().disposeAddContactDialog();
        }else if(e.getSource() == nextButton){

        }else if(e.getSource() == previousButton){

        }
    }
}
