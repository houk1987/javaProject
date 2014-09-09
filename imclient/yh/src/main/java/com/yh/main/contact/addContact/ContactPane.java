package com.yh.main.contact.addContact;

import com.ui.JTextField.JTextFieldFactory;
import com.ui.jlabel.JLabelFactory;
import com.yh.button.CustomButtonFactory;
import com.yh.lanuch.YhClient;
import com.yh.main.contact.YmContactManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by a on 2014/9/2.
 */
public class ContactPane extends JPanel implements ActionListener{
    private final static String SKIN_PATH = "res/main/addContact/";
    private ImageIcon bgImageIcon = new ImageIcon("res/login/loginBg.png");
    private JButton nextButton;
    private JButton cancelButton;
    private JButton finishButton;
    private JButton previousButton;
    private JDialog jDialog;
    private FirstPane firstPane;
    private SecondPane secondPane;
    private ThridPane thridPane;
    private JPanel[] panels = new JPanel[3];
    private int currentIndex;
    public ContactPane(JDialog jDialog) {
        setOpaque(false);
        this.jDialog  = jDialog;
        setLayout(new BorderLayout(10,10));
        firstPane = new FirstPane();
        secondPane = new SecondPane();
        thridPane = new ThridPane();
        panels[0] = firstPane;
        panels[1] = secondPane;
        panels[2] = thridPane;

        JPanel controlPanel = new JPanel(new GridLayout(2,1,0,-2));
        controlPanel.setOpaque(false);
        controlPanel.setBorder(new EmptyBorder(0,0,8,0));
        JLabel lineBgLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"lineBg.png"));
        lineBgLabel.setLocation(7,5);
        controlPanel.add(lineBgLabel);
        add(controlPanel, BorderLayout.SOUTH);
        JPanel btnPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPane.setOpaque(false);
        controlPanel.add(btnPane);
        previousButton = CustomButtonFactory.createPreviousButton();
        previousButton.setDisabledIcon(new ImageIcon(SKIN_PATH+"previousDisabel.png"));
        nextButton = CustomButtonFactory.createNextButton();
        nextButton.setDisabledIcon(new ImageIcon(SKIN_PATH+"nextDisabel.png"));
        cancelButton = CustomButtonFactory.createCancelButton();
        btnPane.add(previousButton);
        btnPane.add(nextButton);
        btnPane.add(cancelButton);

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        cancelButton.addActionListener(this);
        switchPane(currentIndex);
    }

    public void switchPane(int index){

        switch (index){
            case 0:
                previousButton.setEnabled(false);
                if("".equals(firstPane.accountJTextField.getText())){
                    nextButton.setEnabled(false);
                }
                cancelButton.setVisible(true);
                finishButton.setVisible(false);
                break;
            case 1:
                previousButton.setEnabled(true);
                nextButton.setEnabled(true);
                cancelButton.setVisible(true);
                finishButton.setVisible(false);
                break;
            case 2:
                previousButton.setEnabled(true);
                nextButton.setEnabled(false);
                cancelButton.setVisible(false);
                finishButton.setVisible(true);
               break;
        }


        add(panels[currentIndex], BorderLayout.CENTER);
        updateUI();
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
           jDialog.dispose();
        }else if(e.getSource() == nextButton){
            currentIndex = ++currentIndex;
            if(currentIndex == 1){
                secondPane.setName(firstPane.accountJTextField.getText());
            }
            remove(1);
            switchPane(currentIndex);
        }else if(e.getSource() == previousButton){
            remove(1);
            currentIndex = --currentIndex;

            switchPane(currentIndex);
        }else if(finishButton == e.getSource()){
            YmContactManager ymContactManager = new YmContactManager(YhClient.getInstance().getImConnection());
            ymContactManager.applyNewContact(firstPane.accountJTextField.getText());
        }
    }

    private class FirstPane extends JPanel {
        private JTextField accountJTextField;
        private FirstPane() {
            setLayout(null);
            setOpaque(false);
            JLabel IdNameLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"IdName.png"));
            IdNameLabel.setLocation(7,18);
            add(IdNameLabel);

            JLabel netLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"netBg.png"));
            netLabel.setLocation(290, 20);
            add(netLabel);

            JLabel  accountExampleBgLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"accountExampleBg.png"));
            accountExampleBgLabel.setLocation(7,70);
            add(accountExampleBgLabel);


            JLabel  addressBgLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"addressBg.png"));
            addressBgLabel.setLocation(7,270);
            add(addressBgLabel);

            accountJTextField = JTextFieldFactory.createJTextField(270, 21, Color.GRAY);
            accountJTextField.setLocation(11,43);
            accountJTextField.setBorder(null);
            add(accountJTextField);
            accountJTextField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    String text = null;
                    try {
                        text = e.getDocument().getText(e.getDocument().getStartPosition().getOffset(), e.getDocument().getLength());
                        nextButton.setEnabled("".equals(text)?false:true);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    String text = null;
                    try {
                        text = e.getDocument().getText(e.getDocument().getStartPosition().getOffset(), e.getDocument().getLength());
                        nextButton.setEnabled("".equals(text)?false:true);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }
            });
        }


        public JTextField getAccountJTextField() {
            return accountJTextField;
        }
    }

    private class SecondPane extends JPanel {
        private JTextField desTextField;
        private JLabel nameLabel;
        private SecondPane() {
            setLayout(null);
            setOpaque(false);

            JLabel bgLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"sendContentBg.png"));
            bgLabel.setLocation(7,35);
            add(bgLabel);
            desTextField =JTextFieldFactory.createJTextField(350,21,Color.GRAY);
            desTextField.setLocation(37,170);
            add(desTextField);

            JLabel showName = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"showName.png"));
            showName.setLocation(37,195);
            add(showName);

            JLabel changeBtn = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"changBtnBg.png"));
            changeBtn.setLocation(315,215);
            add(changeBtn);
        }

        public void setName(String account) {
            Font font = new Font("宋体",Font.PLAIN,13);
            nameLabel = JLabelFactory.createJLabel("輸入或選擇"+account+"的組別(G):",font,new Color(204,204,204));
            nameLabel.setBounds(17,15,200,21);
            add(nameLabel);
        }
    }

    private class ThridPane extends JPanel {
        private final static String SKIN_PATH = "res/main/addContact/";
        private JLabel lineLabel;
        private ThridPane() {
            setLayout(null);
            setOpaque(false);
            lineLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH+"thridCard.png"));
            lineLabel.setLocation(3,347);
            add(lineLabel);
        }
    }


    class validateApplyAccountWork extends SwingWorker{
        @Override
        protected Object doInBackground() throws Exception {
            return false;
        }

        @Override
        protected void done() {
            if(isDone()){
                cancelButton.setVisible(false);
                finishButton.setVisible(true);
            }
        }
    }



}
