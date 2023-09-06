package com.bean.beanapiinterface.controller;


import com.bean.beanapiclientsdk.model.User;
import com.bean.beanapiclientsdk.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 *
 * @author Sami
 */
@RestController
@RequestMapping("/name")
public class NameController {


    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request) {

        return "POST 用户名是" + user.getUsername();
    }

}
