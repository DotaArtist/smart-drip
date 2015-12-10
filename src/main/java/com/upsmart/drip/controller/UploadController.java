package com.upsmart.drip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.upsmart.drip.response.BaseMessage;
import com.upsmart.drip.service.impl.FileService;

/**
 * 文件上传
 *
 * @author hekui
 * @since 2015年12月10日 下午5:06:37
 */
@Controller
public class UploadController {
    @Autowired
    FileService fileService;

    @RequestMapping(value = "upload")
    @ResponseBody
    public BaseMessage upload(@RequestParam("file") final MultipartFile file) {
        return fileService.upload(file);
    }
}
