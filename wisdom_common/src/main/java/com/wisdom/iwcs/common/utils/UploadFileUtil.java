package com.wisdom.iwcs.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadFileUtil {

    public static Map saveFilePic(MultipartFile file, String rootPath, String filePath) {
        Map<String, String> map = new HashMap();

        if (file == null) {
            map.put("status", "fail");
            map.put("path", "");
            return map;
        }
        String fileName = file.getOriginalFilename();
        // 获取文件的扩展名
        String extensionName = fileName
                .substring(fileName.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newfileName = uuid + "." + extensionName;

        if (!new File(rootPath + filePath).exists()) {
            new File(rootPath + filePath).mkdirs();
        }
        //这里是随机获取文件名 加文件后缀名
        File path = new File(rootPath + filePath + newfileName);
        try {
            path.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {//create path
            file.transferTo(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", "success");
        String paths = path.toString();
        map.put("path", "/" + filePath + newfileName);
        map.put("fileName", fileName);
        return map;
    }


}
