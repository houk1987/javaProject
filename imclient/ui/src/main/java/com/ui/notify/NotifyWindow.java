package com.ui.notify;

import javax.swing.*;

/**
 * Created by HK on 2014/9/8.
 * message notifyWindow
 */
public abstract class NotifyWindow extends JDialog{


    protected NotifyWindow() {
        setContentPane(initContentPane());
    }

    abstract JPanel initContentPane();
}
