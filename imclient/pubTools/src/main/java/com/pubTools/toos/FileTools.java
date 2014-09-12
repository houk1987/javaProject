package com.pubTools.toos;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hk on 2014/7/14.
 */
public class FileTools {

    public static String readFileToString(File file){
        if (file != null && file.exists()) {
            try {
                return FileUtils.readFileToString(file,"GBK");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static List readFileToList(File file){
        if (file != null && file.exists()) {
            try {
                return FileUtils.readLines(file,"GBK");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean writeFile(File file,String content){
        try {
            if(file != null && (file.exists() || file.createNewFile())) {
                    List list = readFileToList(file);
                    list.add(content);
                    FileUtils.writeLines(file, "GBK",list);
                    return true;
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除文件，再创建一个新文件
     * @param file
     */
    public static boolean cleanFileContent(File file) {
        if (file != null && file.exists()) {
            try {
                file.delete();
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
