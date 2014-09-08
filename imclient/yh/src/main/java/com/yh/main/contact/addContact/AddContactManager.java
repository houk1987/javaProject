package com.yh.main.contact.addContact;


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
//        if(addContactDialog == null){
//            addContactDialog = WindowFactory.createJDialog("加入朋友名单",new ImageIcon("").getImage(),426,416);
//            addContactDialog.setContentPane(new ContactPane());
//            addContactDialog.setVisible(true);
//        }

        addContactDialog.requestFocus();
    }

    public void disposeAddContactDialog(){
        addContactDialog.dispose();
        addContactDialog = null;
    }
}
