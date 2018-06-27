package com.myweb.controller;


import com.myweb.service.AdminOneService;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ResultUtils;
import com.myweb.vo.ThreeParameter;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminOneController {

    @Autowired
    public AdminOneService adminOneService;

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
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.clickMost(pageable, httpSession));
    }

    @GetMapping("/study/count")
    public Result studyCount(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCount(httpSession));
    }

    @GetMapping("/help")
    public Result help(@ModelAttribute ThreeParameter threeParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.help(threeParameter, httpSession));
    }

    @PostMapping("/setting")
    public Result setting(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    @PostMapping("/help/delete")
    public Result helpDelete(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.helpDelete(adminOneParameter, httpSession));
    }

    @PostMapping("/help/refer")
    public Result helpRefer(@ModelAttribute ThreeParameter threeParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.helpRefer(threeParameter, httpSession));
    }

    @GetMapping("/showlog")
    public Result showlog(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.showlog(pageable, httpSession));
    }
}
