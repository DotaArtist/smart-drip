package com.upsmart.water.drop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.service.FileService;



/** 
* @ClassName: UploadController 
* @Description:文件上传Controller
* @author lihx 
* @date 2015年11月24日 下午8:56:40 
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
