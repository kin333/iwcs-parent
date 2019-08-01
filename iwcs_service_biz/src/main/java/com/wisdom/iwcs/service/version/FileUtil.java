package com.wisdom.iwcs.service.version;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil{

    private enum include{
        APK,JPG,apk,txt,png;
    }

    public static void filter(String suffix) throws Exception{
        include.valueOf(suffix.toUpperCase());
    }
    //静态方法：三个参数：文件的二进制，文件路径，文件名
    //通过该方法将在指定目录下添加指定文件
    public static void fileupload(byte[] file,String filePath,String fileName) throws IOException {
        //目标目录
        File targetfile = new File(filePath);
        if (!targetfile.exists()) {
            targetfile.mkdirs();
        }

        //二进制流写入
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
