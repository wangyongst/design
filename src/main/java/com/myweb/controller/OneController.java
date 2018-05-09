package com.myweb.controller;

import com.myweb.pojo.User;
import com.myweb.service.OneService;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
public class OneController {

    @Autowired
    public OneService oneService;

    //注册
    @ResponseBody
    @RequestMapping(value = "/user/registy", method = RequestMethod.POST)
    public Result registy(@ModelAttribute User user) {
        return null;
    }

}
