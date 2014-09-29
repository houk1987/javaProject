package com.dt.session;


import com.dt.sys.SysProperties;
import com.imService.message.SessionMessage;
import com.ui.button.ImageButtonFactory;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SessionEditorPane extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    private String skinPath = SysProperties.sessionFrameButtonPath();

    private ChatWritePanel richEditor;//�༭��
    private JPanel sessionEditorBottomPane;
    private JButton sendButton;
    private JButton closeButton;
    private SessionFrame sessionFrame;
    public SessionEditorPane(SessionFrame sessionFrame) {
        this.setPreferredSize(new Dimension(0,150));
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.sessionFrame = sessionFrame;

        richEditor = new ChatWritePanel(this);
        sessionEditorBottomPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sessionEditorBottomPane.setOpaque(false);


        this.add(richEditor, BorderLayout.CENTER);
        this.add(sessionEditorBottomPane, BorderLayout.SOUTH);

//        closeButton = CustomButtonFactory.createCloseFrameButton();
//        closeButton.addActionListener(this);
//        sessionEditorBottomPane.add(closeButton);
//
        sendButton = ImageButtonFactory.createButton(skinPath,"������Ϣ","send.png");
        sendButton.addActionListener(this);
        sessionEditorBottomPane.add(sendButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendButton){
            try {
                SessionMessage sessionMessage = richEditor.getSessionMessage();
                sessionFrame.sendMessage(sessionMessage);
                richEditor.clear();//��������ı�
                sessionFrame.insertMessageToDisplay(sessionMessage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }else if(e.getSource() == closeButton){
            //sessionFrame.dispose();
        }
    }
    public ChatWritePanel getRichEditor() {
        return richEditor;
    }
}

