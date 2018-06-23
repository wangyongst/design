package com.myweb.controller;


import com.myweb.pojo.User;
import com.myweb.service.OneService;
import com.myweb.vo.OneParameter;
import com.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api
@CrossOrigin("*")
@Controller
public class OneController {

    @Autowired
    public OneService oneService;

    @ApiOperation(value = "注册", notes = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = " 密码（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = " 昵称（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱 （必需）", required = true, dataType = "String")

    })
    @ResponseBody
    @PostMapping("/user/regist")
    public Result regist(@ModelAttribute OneParameter oneParameter) {
        return oneService.regist(oneParameter);
    }

    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = " 密码（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱 （可选）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/login")
    public Result login(@ModelAttribute OneParameter oneParameter) {
        return oneService.login(oneParameter);
    }

    @ApiOperation(value = "账户设置(基本资料)", notes = "账户设置(基本资料)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "username", value = "账号（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = "昵称（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "手机号码（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "jobs", value = " 职业（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别 （可选）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/set/basic")
    public Result setBasic(@ModelAttribute OneParameter oneParameter) {
        return oneService.setBasic(oneParameter);
    }

    @ApiOperation(value = "账户设置(修改头像)", notes = "账户设置(修改头像)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "avatar", value = "头像（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/set/avatar")
    public Result setAvatar(@ModelAttribute OneParameter oneParameter) {
        return oneService.setAvatar(oneParameter);
    }


    @ApiOperation(value = "账户设置(修改密码)", notes = "账户设置(修改密码)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "password", value = "旧密码（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password2", value = "新密码（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/set/password")
    public Result setPassword(@ModelAttribute OneParameter oneParameter) {
        return oneService.setPassword(oneParameter);
    }

    @ApiOperation(value = "账户设置(修改邮箱)", notes = "账户设置(修改邮箱)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "email", value = "邮箱（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/set/email")
    public Result setEmail(@ModelAttribute OneParameter oneParameter) {
        return oneService.setEmail(oneParameter);
    }

    @ApiOperation(value = "我的关注", notes = "我的关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0，每页10条分页", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/follow/my")
    public Result followMy(@ModelAttribute OneParameter oneParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        Pageable pageable = new PageRequest(oneParameter.getPage(), 10, sort);
        return oneService.followMy(oneParameter,pageable);
    }

    @ApiOperation(value = "我的粉丝", notes = "我的粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0，每页10条分页", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/follow/me")
    public Result followMe(@ModelAttribute OneParameter oneParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        Pageable pageable = new PageRequest(oneParameter.getPage(), 10, sort);
        return oneService.followMe(oneParameter,pageable);
    }

    @ApiOperation(value = "关注", notes = "关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "touserid", value = "要关注的用户id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/user/follow")
    public Result follow(@ModelAttribute OneParameter oneParameter) {
        return oneService.follow(oneParameter);
    }

    @ApiOperation(value = "取消关注", notes = "取消关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "touserid", value = "要取消关注的用户id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/user/unfollow")
    public Result unfollow(@ModelAttribute OneParameter oneParameter) {
        return oneService.unfollow(oneParameter);
    }
}
