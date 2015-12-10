package com.upsmart.drip.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upsmart.drip.response.BaseMessage;
import com.upsmart.drip.response.MessageCode;

/**
 * @ClassName: FileService
 * @Description:文件上传和复制操作
 * @author lihx
 * @date 2015年7月1日 下午3:52:04
 */
@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${filePath}")
    private String filePath;
    @Value("${tempFilePath}")
    private String tempFilePath;

    /**
     * 文件上传
     * 
     * @param file
     * @return
     */
    public BaseMessage upload(final MultipartFile file) {
        BaseMessage baseMessage = null;
        if (!file.isEmpty()) {
            // 取当前时间戳作为文件名前缀
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destFile = new File(tempFilePath + filename);
            try {
                // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                // 保存文件到临时目录下
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(file.getOriginalFilename() + "上传失败", e);
                baseMessage = new BaseMessage(MessageCode.FAILED);
                return baseMessage;
            }
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            return baseMessage;
        } else {
            logger.info("获取不到上传文件");
            baseMessage = new BaseMessage(MessageCode.FAILED);
            return baseMessage;
        }
    }

    /**
     * 文件复制
     * 
     * @param fileName
     * @param tempfileName
     * @return
     */
    public String copyFile(final String fileName, final String tempfileName) {
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = String.format("%02d", now.get(Calendar.MONTH) + 1);
        String date = String.format("%02d", now.get(Calendar.DATE));
        String datePath = year + "/" + month + "/" + date + "/";
        File path = new File(filePath + datePath);
        // 如果文件夹不存在则创建
        if (!path.exists() && !path.isDirectory()) {
            path.mkdirs();
        }
        String srcFile = filePath + datePath + fileName;
        try {
            FileUtils.copyFile(new File(tempFilePath + tempfileName), new File(srcFile));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(tempfileName + "文件复制错误", e);
        }
        return datePath;
    }

}
