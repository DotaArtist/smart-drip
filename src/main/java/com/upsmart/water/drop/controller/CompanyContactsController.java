package com.upsmart.water.drop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.water.drop.dto.CompanyContactsDto;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.service.CompanyContactsService;

/**
 * @ClassName: CompanyContactsController
 * @Description: 公司联系人Controller
 * @author lihx
 * @date 2015年11月24日 下午9:07:52
 */
@Controller
@RequestMapping("companyContact")
public class CompanyContactsController {
    @Autowired
    private CompanyContactsService companyContactService;

    /**
     * 新增公司联系人
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage saveCompanyContact(@RequestBody CompanyContactsDto companyContactsDto) {
        return companyContactService.saveCompanyContact(companyContactsDto);
    }

    /**
     * 新增公司联系人
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public BaseMessage updateCompanyContact(@RequestBody CompanyContactsDto companyContactsDto) {
        return companyContactService.updateCompanyContact(companyContactsDto);
    }

    /**
     * 查询公司联系人
     */
    @RequestMapping(value = "{comId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryCompanyContacts(@PathVariable Integer comId) {
        return companyContactService.queryCompanyContacts(comId);
    }

    /**
     * 根据id查询公司联系人
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryCompanyContactByID(@RequestParam(required = false) Integer id) {
        return companyContactService.queryCompanyContactByID(id);
    }

    /**
     * 刪除公司联系人
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseMessage deleteCompanyContact(@PathVariable Integer id) {
        return companyContactService.deleteCompanyContact(id);
    }

}
