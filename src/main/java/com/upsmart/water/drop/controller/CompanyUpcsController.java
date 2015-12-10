package com.upsmart.water.drop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.water.drop.dto.CompanyUpcsDto;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.service.CompanyUpcsService;


/** 
* @ClassName: CompanyUpcsController 
* @Description: 公司智慧联系人Controller
* @author lihx 
* @date 2015年11月25日 上午9:20:50 
*/
@Controller
@RequestMapping("companyUpcs")
public class CompanyUpcsController {
    @Autowired
    private CompanyUpcsService companyUpcsService;

    /**
     * 新增公司智慧联系人
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage saveCompanyUpcs(@RequestBody CompanyUpcsDto companyUpcsDto) {
        return companyUpcsService.saveCompanyUpcs(companyUpcsDto);
    }

    /**
     *  获取智慧联系人列表
     */
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage queryUpcsByComId(@PathVariable Integer id) {
        return companyUpcsService.queryUpcsByComId(id);
    }

    /**
     * 刪除公司智慧联系人
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public BaseMessage deleteCompanyUpcs(@PathVariable Integer id) {
        return companyUpcsService.deleteCompanyUpcs(id);
    }

}
