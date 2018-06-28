package com.myweb.controller;


import com.myweb.service.AdminOneService;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ResultUtils;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminOneController {

    @Autowired
    public AdminOneService adminOneService;

    //登录
    @PostMapping("/user/login")
    public Result login(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.login(oneParameter, httpSession));
    }

    //登出
    @PostMapping("/user/logout")
    public Result logout(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.logout(httpSession));
    }

    //用户列表,搜索用户
    @GetMapping("/user/list")
    public Result userList(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.userList(oneParameter, pageable, httpSession));
    }

    //登录时间列表
    @GetMapping("/user/token")
    public Result userToken(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.userToken(pageable, httpSession));
    }

    //在线人数
    @GetMapping("/count/token")
    public Result countToken(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.countToken(httpSession));


    }

    //type2删除，其它修改
    @PostMapping("/user")
    public Result postUser(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.user(oneParameter, httpSession));
    }

    //全网点击量
    @GetMapping("/count/click")
    public Result countClick(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.clickCount(httpSession));
    }

    //注册人数
    @GetMapping("/count/user")
    public Result countUser(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.countUser(httpSession));
    }

    //全网想学
    @GetMapping("/count/study")
    public Result countStudy(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.studyCount(httpSession));
    }

    //全网私信数
    @GetMapping("/count/message")
    public Result countMessage(HttpSession httpSession) {
        return ResultUtils.result(adminOneService.countMessage(httpSession));
    }

    //1发送，2接收，3发送给谁
    @GetMapping("/count/message/user")
    public Result countMessageUser(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.countMessageUser(oneParameter, httpSession));
    }

    //浏览最多统计
    @GetMapping("/most/click")
    public Result mostClick(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "clicked");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.clickMost(pageable, httpSession));
    }

    //求助详情
    @GetMapping("/help")
    public Result getHelp(@ModelAttribute TwoParameter twoParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.help(twoParameter, httpSession));
    }

    //type2删除,type1审核
    @PostMapping("/help")
    public Result postHelp(@ModelAttribute TwoParameter twoParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.postHelp(twoParameter, httpSession));
    }

    //operation 1增 2改，3删除 type1上线,2下线
    @PostMapping("/advert")
    public Result PostAdvert(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    //operation 1增 2改 type1logo,2友情链接
    @PostMapping("/setting")
    public Result PostSetting(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    //查询设置1log,2友链
    @GetMapping("/setting")
    public Result getSetting(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.getSetting(adminOneParameter, httpSession));
    }

    @GetMapping("/searching")
    public Result getSearching(@ModelAttribute AdminOneParameter adminOneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.setting(adminOneParameter, httpSession));
    }

    //每个用户被关注的次数
    @GetMapping("/count/follow")
    public Result countFollow(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.countFollow(oneParameter, httpSession));
    }

    //每个用户被关注的详情
    @GetMapping("/follow")
    public Result getfollow(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.follow(oneParameter, pageable, httpSession));
    }

    //强制推荐
    @PostMapping("/help/refer")
    public Result helpRefer(@ModelAttribute TwoParameter twoParameter, HttpSession httpSession) {
        return ResultUtils.result(adminOneService.helpRefer(twoParameter, httpSession));
    }

    //操作日志
    @GetMapping("/showlog")
    public Result showlog(@ModelAttribute OneParameter oneParameter, HttpSession httpSession) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(adminOneService.showlog(pageable, httpSession));
    }
}
