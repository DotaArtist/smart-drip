package com.upsmart.water.drop.controller;

import static com.upsmart.water.drop.constant.Constant.DEF_PAGE_NUM;
import static com.upsmart.water.drop.constant.Constant.DEF_PAGE_SIZE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.water.drop.dto.UpsmartContactsDto;
import com.upsmart.water.drop.dto.UpsmartContactsFilterDto;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.service.impl.UpsmartContactsService;

/** 
* @ClassName: UpsmartContactsController 
* @Description: 智慧联系人Controller
* @author lihx 
* @date 2015年11月24日 下午2:28:06 
*/
@Controller
@RequestMapping("upsmartContact")
public class UpsmartContactsController {
    @Autowired
    private UpsmartContactsService upsmartContactService;

    /**
     * 新增智慧联系人
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage saveUpsmartContact(@RequestBody UpsmartContactsDto upsmartContactsDto) {
        return upsmartContactService.saveUpsmartContact(upsmartContactsDto);
    }

    /**
     * 新增智慧联系人
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public BaseMessage updateUpsmartContact(@RequestBody UpsmartContactsDto upsmartContactsDto) {
        return upsmartContactService.updateUpsmartContact(upsmartContactsDto);
    }

    /**
     * 查询智慧联系人
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryUpsmartContacts(@RequestParam(required = false) Integer depId, @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageNum, @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int pageSize) {
        if (pageNum < 0) {
            pageNum = 0;
        }
        UpsmartContactsFilterDto upsmartContactsFilterDto = new UpsmartContactsFilterDto(name, depId);
        upsmartContactsFilterDto.setVaild(true);
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        return upsmartContactService.queryUpsmartContacts(upsmartContactsFilterDto, pageRequest);
    }
    
    /**
     * 查询智慧联系人(查询全部,不分页)
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryUpsmartContacts(@RequestParam(required = false) Integer depId,@RequestParam(required = false) Integer comId) {
        return upsmartContactService.queryUpsmartContacts(depId,comId);
    }
    
    /**
     * 根据id查询智慧联系人
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryUpsmartContactById(@PathVariable Integer id) {
        return upsmartContactService.queryUpsmartContactById(id);
    }

    /**
     * 刪除智慧联系人
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseMessage deleteUpsmartContact(@PathVariable Integer id) {
        return upsmartContactService.deleteUpsmartContact(id);
    }

}
