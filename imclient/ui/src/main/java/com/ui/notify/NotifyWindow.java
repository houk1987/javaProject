package com.ui.notify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HK on 2014/9/8.
 * message notifyWindow
 */
public abstract class NotifyWindow extends JDialog implements ActionListener {


    private JButton closeWindowBtn;

    protected NotifyWindow() {
        setContentPane(initContentPane());
    }

    protected abstract JPanel initContentPane();


    /**
     * @Title:showNotifyWindow
     * @Description:显示消息提示窗口
     * @author houk
     * @date 2012-6-21 下午5:53:37
     */
    public void showNotifyWindow() {
        setWindowOnRightBottom(this);
//        String trayTime = SimConfig.getTrayFloatPromptDuration() + "000";
//        if (timer.isRunning()) {
//            timer.stop();
//        }
//        timer.setInitialDelay(Integer.parseInt(trayTime));
//        timer.start();
        this.setVisible(true);
    }

    /**
     * Description 设置窗口位置，桌面右下侧，任务栏之上(屏幕右下的提示信息窗口)
     *
     * @author youyou
     * @version 2012-2-20
     */
    public static void setWindowOnRightBottom(Window window) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension size = tk.getScreenSize();
        Insets insets = tk.getScreenInsets(window.getGraphicsConfiguration());
        int x = size.width - window.getWidth() - 10 - insets.right;
        int y = size.height - window.getHeight() - insets.bottom;
        window.setLocation(x, y);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
