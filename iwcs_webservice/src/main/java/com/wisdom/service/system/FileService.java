package com.wisdom.service.system;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.YZConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {


    public Result imgUpload(MultipartFile file, String pathRoot) {
        if (!file.isEmpty()) {
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型
            String contentType = file.getContentType();
            //获得文件后缀名称
            String imageSuffix = contentType.substring(contentType.indexOf("/") + 1);
            String targetPath = YZConstants.FileImageUploadDownload.FILE_IMAGE_STORAGE_DEFAULT_PATH + uuid + "." + imageSuffix;
            String imgUrl = pathRoot + targetPath;
            //保存图片
            try {
                file.transferTo(new File(imgUrl));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            return new Result(targetPath);
        }
        return new Result(400, "文件为空");
    }

}
