package com.myweb.controller;

import com.myweb.pojo.Book;
import com.myweb.pojo.User;
import com.myweb.service.OneService;
import com.utils.Result;
import com.utils.WeixinUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api
@CrossOrigin("*")
@Controller
public class OneController {

    @Autowired
    public OneService oneService;

    //扫描isbn
    @ApiOperation(value = "扫描isbn", notes = "用户扫描isbn,通过豆瓣接口获得书详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn", value = "isbn（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/book/scan")
    public Result scan(@ModelAttribute Book book) {
        return oneService.scan(book);
    }

    //上架isbn
    @ApiOperation(value = "上架", notes = "用户扫描书上的isbn,通过豆瓣接口获得书详细信息并上架到书店")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isbn", value = "isbn（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/book/isbn")
    public Result isbn(@ModelAttribute Book book) {
        return oneService.isbn(book);
    }

    @ApiOperation(value = "用户注册", notes = "用户使用账号密码注册,如注册成功用户,则自动完成登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码（必需）", required = true, dataType = "String")
    })
    @PostMapping("user/regist")
    public Result regist(@ModelAttribute User user) {
        return oneService.regist(user);
    }

    @ApiOperation(value = "用户登录", notes = "用户使用账号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名（必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("user/login")
    public Result login(@ModelAttribute User user) {
        return oneService.login(user);
    }

    @ApiOperation(value = "微信授权", notes = "微信授权,第一次使用，则自动注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code（必需）", required = true, dataType = "String"),
    })
    @PostMapping("user/weixin/code/{code}")
    @ResponseBody
    public Result weixinCode(@PathVariable String code) {
        return oneService.weixinCode(code);
    }

    @ApiOperation(value = "微信登录", notes = "微信登录,通过微信授权后，前端保存openid，后续都将openid做为登录凭证使用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid（必需）", required = true, dataType = "String"),
    })
    @GetMapping("user/weixin/login")
    @ResponseBody
    public Result weixinOpenId(@ModelAttribute User user) {
        return oneService.weixinLogin(user);
    }
}
