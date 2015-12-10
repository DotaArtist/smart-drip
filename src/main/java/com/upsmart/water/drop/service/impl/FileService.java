package com.upsmart.water.drop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.upsmart.water.drop.dto.CompanyFileDto;
import com.upsmart.water.drop.orm.domain.CompanyFile;
import com.upsmart.water.drop.orm.repository.CompanyFileRepository;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.response.MessageCode;

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
    @Autowired
    private CompanyFileRepository companyFileRepository;

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
            CompanyFileDto companyFileDto = new CompanyFileDto();
            companyFileDto.setName(filename);
            companyFileDto.setPath(tempFilePath);
            companyFileDto.setUpdate(true);
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            baseMessage.setData(companyFileDto);
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
        String month = String.format("%02d", now.get(Calendar.MONTH)+1);
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

    /**
     * 新增图片附件信息
     * 
     * @param imgFiles
     */
    @Transactional
    public int saveFiles(final CompanyFileDto fileDto, final int comId, int i) {
        Date now = new Date();
        CompanyFile companyFile = new CompanyFile();
        String fileName = fileDto.getName();
        String type = fileName.substring(fileName.lastIndexOf("."));
        String name = comId + "_" + i + type;
        String filePath = copyFile(name, fileName);
        companyFile.setName(name);
        companyFile.setPath(filePath);
        companyFile.setType(i);
        companyFile.setCreatedAt(now);
        companyFile.setUpdatedAt(now);
        companyFile.setValid(true);
        companyFile = companyFileRepository.save(companyFile);
        return companyFile.getId();
    }

    /**
     * 更新图片附件信息
     * 
     * @param imgFiles
     * @param userId
     */
    @Transactional
    public int updateFiles(final CompanyFileDto fileDto, final int comId, int i) {
        Date now = new Date();
        CompanyFile companyFile = companyFileRepository.findOne(fileDto.getId());
        String fileName = fileDto.getName();
        String type = fileName.substring(fileName.lastIndexOf("."));
        String name = comId + "_" + i + type;
        String filePath = copyFile(name, fileName);
        companyFile.setName(name);
        companyFile.setPath(filePath);
        companyFile.setUpdatedAt(now);
        companyFile = companyFileRepository.save(companyFile);
        return companyFile.getId();
    }

    /**
     * 删除图片
     * 
     * @param imgFiles
     * @param userId
     */
    @Transactional
    public void deleteFile(int id) {
        Date now = new Date();
        CompanyFile companyFile = companyFileRepository.findOne(id);
        if (null != companyFile) {
            companyFile.setUpdatedAt(now);
            companyFile.setValid(false);
            companyFileRepository.save(companyFile);
        }
    }
}
