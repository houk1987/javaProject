package com.yh.login;

import com.ui.JTextField.FreeTextField;
import com.ui.JTextField.JTextFieldFactory;
import com.ui.checkBox.CheckBoxFactory;
import com.ui.checkBox.FreeCheckBox;
import com.ui.font.FontFactory;
import com.ui.jlabel.JLabelFactory;
import com.yh.lanuch.YhClient;
import com.yh.main.MainFrame;

import org.jivesoftware.smack.XMPPException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 356 on 14-8-30.
 */
public class LoginPane extends JPanel implements ActionListener{

    private final static String SKIN_PATH = "res/login/";
    private ImageIcon bgImageIcon = new ImageIcon(SKIN_PATH + "loginBg.png");
    private String[] menuNames = {"登入(M)", "說明(H)"};
    private JLabel idLabel; //Yahoo! ID
    private JLabel pwdLabel;//登入密碼(P):
    private FreeTextField idTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel logLabel;
    private FreeCheckBox rememberPwdCheckbox; //记住我的密码
    private JLabel rememberPwdLabel; //记住我的密码
    private FreeCheckBox autoLoginCheckbox; //记住我的密码
    private JLabel autoLoginLabel; //记住我的密码
    private FreeCheckBox hideLoginCheckbox; //记住我的密码
    private JLabel hideLoginLabel; //记住我的密码
    private JLabel registerLinkLabel; //注册
    private JLabel forgetPwdLinkLabel; //忘记密码
    private LoginFrame loginFrame;

    public LoginPane(final LoginFrame loginFrame) {
        setLayout(null);
        MenuPane menuPane = new MenuPane(menuNames);
        menuPane.setBounds(0, 0, 200, 20);
        add(menuPane);
        this.loginFrame = loginFrame;

        logLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "log.png"));
        add(logLabel);

        idLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "IdName.png"));
        add(idLabel);
        pwdLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "pwdName.png"));
        add(pwdLabel);

        idTextField = JTextFieldFactory.createJTextField(159, 21, Color.GRAY);
        passwordField = JTextFieldFactory.createJPasswordField(159, 21, Color.GRAY, '●');
        add(idTextField);
        add(passwordField);

        rememberPwdCheckbox = CheckBoxFactory.createJCheckBox("", new ImageIcon(SKIN_PATH + "checkboxEnabled.png"), true, false);
        rememberPwdLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "rememberPwdCheckBoxName.png"));
        add(rememberPwdCheckbox);
        add(rememberPwdLabel);

        autoLoginCheckbox = CheckBoxFactory.createJCheckBox("", new ImageIcon(SKIN_PATH + "checkboxEnabled.png"), true, false);
        autoLoginLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "autoLoginCheckBoxName.png"));
        add(autoLoginCheckbox);
        add(autoLoginLabel);

        hideLoginCheckbox = CheckBoxFactory.createJCheckBox("", new ImageIcon(SKIN_PATH + "checkboxEnabled.png"), true, false);
        hideLoginLabel = JLabelFactory.createJLabel(new ImageIcon(SKIN_PATH + "hideStateLoginCheckBoxName.png"));
        add(hideLoginCheckbox);
        add(hideLoginLabel);

        ImageIcon loginButtonBg = new ImageIcon(SKIN_PATH + "loginButton.png");
        loginButton = new JButton();
        loginButton.setIcon(loginButtonBg);
        loginButton.setSize(loginButtonBg.getIconWidth(), loginButtonBg.getIconHeight());

        add(loginButton);
        loginButton.addActionListener(this);

        Font font = FontFactory.createFont("宋体", 12);
        registerLinkLabel = JLabelFactory.createLinkLabel("立即註冊申請", font, "#66a1e3", "www.baidu.com");
        registerLinkLabel.setSize(100, 20);
        add(registerLinkLabel);

        forgetPwdLinkLabel = JLabelFactory.createLinkLabel("忘記密碼？", font, "#66a1e3", null);
        forgetPwdLinkLabel.setSize(100, 20);
        add(forgetPwdLinkLabel);
        setLocation();
    }


    public void setLocation() {
        int x = this.getWidth() / 2 - 80;
        int y = 170;
        idLabel.setLocation(x, y);
        idTextField.setLocation(x, y + 20);
        pwdLabel.setLocation(x, y + 50);
        passwordField.setLocation(x, y + 70);
        logLabel.setLocation(this.getWidth() / 2 - 60, 55);

        rememberPwdCheckbox.setLocation(x, y + 105);
        rememberPwdLabel.setLocation(x + rememberPwdCheckbox.getWidth() + 2, y + 104);

        autoLoginCheckbox.setLocation(x, y + 128);
        autoLoginLabel.setLocation(x + autoLoginCheckbox.getWidth() + 2, y + 126);

        hideLoginCheckbox.setLocation(x, y + 151);
        hideLoginLabel.setLocation(x + hideLoginCheckbox.getWidth() + 2, y + 149);

        loginButton.setLocation(x + 30, y + 180);
        registerLinkLabel.setLocation(x, this.getHeight() - 60);
        forgetPwdLinkLabel.setLocation(x, this.getHeight() - 40);
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
        if(e.getSource()  == loginButton){
            final String account = idTextField.getText();
            final String pwd = String.valueOf(passwordField.getPassword());
            if (account.equals("")) {
                JOptionPane.showMessageDialog(LoginPane.this, "账号不能为空!");
                idTextField.requestFocus();
            }
            if (pwd.equals("")) {
                JOptionPane.showMessageDialog(LoginPane.this, "密码不能为空!");
                passwordField.requestFocus();
            }
            try {
                YhClient.getInstance().loginClient(account,pwd);
                loginFrame.setVisible(false);
                loginFrame = null;
                MainFrame.getInstance().setVisible(true);
            } catch (XMPPException e1) {
                JOptionPane.showMessageDialog(LoginPane.this, "账号或密码错误!");
                YhClient.getInstance().loginOut();
            }
        }
    }
}
