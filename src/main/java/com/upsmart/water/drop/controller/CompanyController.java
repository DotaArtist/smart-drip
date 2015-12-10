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

import com.upsmart.water.drop.dto.CompanyDetailDto;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.service.CompanyService;

/**
 * @ClassName: CompanyController
 * @Description: 公司信息Controller
 * @author lihx
 * @date 2015年11月24日 下午2:27:38
 */
@Controller
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 新增公司
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage saveCompany(@RequestBody CompanyDetailDto companyDetailDto) {
        return companyService.saveCompany(companyDetailDto);
    }

    /**
     * 修改公司
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public BaseMessage updateCompany(@RequestBody CompanyDetailDto companyDetailDto) {
        return companyService.updateCompany(companyDetailDto);
    }

    /**
     * 查询公司
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryCompanys(@RequestParam(required = false) String name, @RequestParam(required = false, defaultValue = DEF_PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = DEF_PAGE_SIZE) int pageSize) {
        if (pageNum < 0) {
            pageNum = 0;
        }
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        return companyService.queryCompanys(name, pageRequest);
    }

    /**
     * 根据id获取公司详细信息
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryCompanyDetail(@PathVariable Integer id) {
        return companyService.queryCompanyDetail(id);
    }

    /**
     * 刪除公司
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseMessage deleteCompany(@PathVariable Integer id) {
        return companyService.deleteCompany(id);
    }

}
