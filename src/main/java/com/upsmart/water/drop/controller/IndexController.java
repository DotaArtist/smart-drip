package com.upsmart.water.drop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.response.MessageCode;

/**
 * @ClassName: IndexController
 * @Description:
 * @author lihx
 * @date 2015年11月24日 下午2:27:56
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Value("${admin.username}")
    private String adminName;
    @Value("${admin.password}")
    private String adminPassword;

    /**
     * 用户登录
     * 
     * @param params
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public BaseMessage login(@RequestBody final Map<String, Object> params, final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {
        try {
            String username = String.valueOf(params.get("username"));
            String password = String.valueOf(params.get("password"));
            if (!adminName.equals(username) || !adminPassword.equals(password)) {
                return new BaseMessage(MessageCode.USERNAME_PASSWORD_ERROR);
            }
        } catch (Exception e) {
            return new BaseMessage(MessageCode.PARAMS_ERROR);
        }
        return new BaseMessage(MessageCode.SUCCESSED);
    }
}