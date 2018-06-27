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
    public Result userLogin(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.login(oneParameter, httpSession));
    }

    @PostMapping("/user/logout")
    public Result userLogout(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.logout(oneParameter, httpSession));
    }

    @GetMapping("/user")
    public Result userList(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.logout(oneParameter, httpSession));
    }

    @GetMapping("/user/token")
    public Result userToken(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.logout(oneParameter, httpSession));
    }

    @PostMapping("/user")
    public Result postUser(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.logout(oneParameter, httpSession));
    }

    @PostMapping("/user/delete")
    public Result userDelete(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.logout(oneParameter, httpSession));
    }

    @GetMapping("/count/click")
    public Result countClick(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.clickCount(httpSession));
    }

    @GetMapping("/count/user")
    public Result countUser(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.clickCount(httpSession));
    }

    @GetMapping("/count/study")
    public Result countStudy(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCount(httpSession));
    }

    @GetMapping("/count/message")
    public Result countMessage(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCount(httpSession));
    }

    @GetMapping("/count/message/user")
    public Result countMessageUser(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCount(httpSession));
    }

    @GetMapping("/message")
    public Result getMessage(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCount(httpSession));
    }

    @GetMapping("/most/click")
    public Result mostClick(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.clickMost(pageable, httpSession));
    }

    @GetMapping("/most/study")
    public Result mostStudy(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.clickMost(pageable, httpSession));
    }


    @GetMapping("/help")
    public Result getHelp(@ModelAttribute ThreeParameter threeParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.help(threeParameter, httpSession));
    }

    @PostMapping("/help")
    public Result postHelp(@ModelAttribute ThreeParameter threeParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.help(threeParameter, httpSession));
    }

    @PostMapping("/setting")
    public Result PostSetting(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    @GetMapping("/setting")
    public Result getSetting(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    @GetMapping("/searching")
    public Result getSearching(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    @GetMapping("/count/follow")
    public Result countFollow(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    @GetMapping("/follow")
    public Result getfollow(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
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
