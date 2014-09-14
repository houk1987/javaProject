package com.imService.file;

import com.imService.connection.ImConnection;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransfer;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

import java.io.File;

/**
 * Created by HK on 2014/9/14.
 */
public class FileManager {

    private ImConnection imConnection;

    public FileManager(ImConnection imConnection) {
        this.imConnection = imConnection;
    }

    /**
     * 发送文件
     * @param filePath  文件路径
     * @param user
     */
    public void sendFile(String filePath,String user) throws Exception{
        if (filePath == null)throw new NullPointerException("文件路径不正确!");
        File sendFile = new File(filePath);
        sendFile(sendFile,user);  //发送文件
    }

    /**
     * 发送文件
     * 发送文件前，首先判断通讯联系是否已经中断,如果中断，提示用户服务器连接中断
     * 默认设置文件发送超时时间为 1分钟
     *
     * @param sendFile  文件
     * @param user  接收文件的对象
     * @throws Exception
     */

    public void sendFile(File sendFile,String user) throws Exception{
        if(imConnection == null || imConnection.getXMPPConnection().isConnected())throw new XMPPException("服务器连接中断!");

        XMPPConnection connection = imConnection.getXMPPConnection();   //服务器通信连接对象

        FileTransferManager manager = new FileTransferManager(connection);  //创建一个文件传输管理类对象

        OutgoingFileTransfer transfer = manager.createOutgoingFileTransfer(user); //创建一个发送文件对象

        long timeOut = 60000;   //文件发送超时时间
        long sleepMin = 3000;
        long spTime = 0;
        int rs = 0;

        transfer.sendFile(sendFile, "jjjj");  //发送文件   文件， 文件描述
        rs = transfer.getStatus().compareTo(FileTransfer.Status.complete);  //文件传输状态
        while(rs!=0){
            rs = transfer.getStatus().compareTo(FileTransfer.Status.complete);
            spTime = spTime + sleepMin;
            if(spTime>timeOut){return ;}
            Thread.sleep(sleepMin);
        }
    }

    public static void main(String[] args) {

    }
}
