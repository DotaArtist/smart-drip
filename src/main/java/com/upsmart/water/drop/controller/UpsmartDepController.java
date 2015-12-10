package com.upsmart.water.drop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.service.impl.UpsmartDepService;

/**
 * @ClassName: UpsmartDepController
 * @Description: 智慧部门Controller
 * @author lihx
 * @date 2015年11月24日 下午2:28:25
 */
@Controller
@RequestMapping("upsmartDep")
public class UpsmartDepController {
    @Autowired
    private UpsmartDepService upsmartDepService;

    /**
     * 查找全部部门
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage getUpsmartDeps() {
        return upsmartDepService.getUpsmartDeps();
    }

    /**
     * 查找全部部门和联系人
     */
    @RequestMapping(value = "contacts", method = RequestMethod.GET)
    @ResponseBody
    public BaseMessage getUpsmartDepsAndContacts() {
        return upsmartDepService.getUpsmartDepsAndContacts();
    }

}
