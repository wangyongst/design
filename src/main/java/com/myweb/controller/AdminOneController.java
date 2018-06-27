package com.myweb.controller;


import com.myweb.service.AdminOneService;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ResultUtils;
import com.myweb.vo.ThreeParameter;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminOneController {

    @Autowired
    public AdminOneService adminOneService;

    @GetMapping("/login")
    public String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
        return "/page-login";
    }

    @PostMapping("/user/login")
    public Result login(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.login(oneParameter, httpSession));
    }

    @PostMapping("/user/logout")
    public Result logout(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.logout(oneParameter, httpSession));
    }

    @GetMapping("/click/count")
    public Result clickCount(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.clickCount(httpSession));
    }

    @GetMapping("/click/most")
    public Result clickMost(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        Pageable pageable = new PageRequest(oneParameter.getPage(), 10, sort);
        //return ResultUtils.result(adminOneService.clickMost(httpSession));
        return null;
    }

    @GetMapping("/study/count")
    public Result studyCount(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCount(httpSession));
    }

    @GetMapping("/study/count/help")
    public Result studyCountHelp(@ModelAttribute ThreeParameter threeParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCountHelp(threeParameter, httpSession));
    }

    @GetMapping("/help/refer")
    public Result helpRefer(@ModelAttribute ThreeParameter threeParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.helpRefer(threeParameter, httpSession));
    }

    @GetMapping("/showlog")
    public Result showlog(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        Pageable pageable = new PageRequest(oneParameter.getPage(), 10, sort);
        return ResultUtils.result(adminOneService.showlog(pageable, httpSession));
    }
}
