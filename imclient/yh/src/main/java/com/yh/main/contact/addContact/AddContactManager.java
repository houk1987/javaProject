package com.yh.main.contact.addContact;


import com.yh.lanuch.YhClient;
import com.yh.main.contact.YmContactManager;

import javax.swing.*;

/**
 * Created by a on 2014/9/2.
 */
public class AddContactManager {

    private JDialog addContactDialog;
    private static final AddContactManager addContactManager = new AddContactManager();

    private AddContactManager() {

    }

    public static AddContactManager getInstance(){
        return addContactManager;
    }


    public void showAddContactDialog(){

    }

    public void disposeAddContactDialog(){
        addContactDialog.dispose();
        addContactDialog = null;
    }
}
