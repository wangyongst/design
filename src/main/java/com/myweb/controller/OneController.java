package com.myweb.controller;


import com.myweb.service.OneService;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ResultUtils;
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
            @ApiImplicitParam(name = "email", value = "邮箱 （必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refer", value = "推荐人id （可选）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/regist")
    public Result regist(@ModelAttribute OneParameter oneParameter) {
        return ResultUtils.result(oneService.regist(oneParameter));
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
        return ResultUtils.result(oneService.login(oneParameter));
    }

    @ApiOperation(value = "登出", notes = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/user/destroy")
    public Result destroy(@ModelAttribute OneParameter oneParameter) {
        return ResultUtils.result(oneService.destroy(oneParameter));
    }


    @ApiOperation(value = "注销", notes = "注销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/user/logout")
    public Result logout(@ModelAttribute OneParameter oneParameter) {
        return ResultUtils.result(oneService.logout(oneParameter));
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
        return ResultUtils.result(oneService.setBasic(oneParameter));
    }

    @ApiOperation(value = "账户设置(修改头像)", notes = "账户设置(修改头像)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "avatar", value = "头像（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/set/avatar")
    public Result setAvatar(@ModelAttribute OneParameter oneParameter) {
        return ResultUtils.result(oneService.setAvatar(oneParameter));
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
        return ResultUtils.result(oneService.setPassword(oneParameter));
    }

    @ApiOperation(value = "账户设置(修改邮箱)", notes = "账户设置(修改邮箱)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "email", value = "邮箱（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/user/set/email")
    public Result setEmail(@ModelAttribute OneParameter oneParameter) {
        return ResultUtils.result(oneService.setEmail(oneParameter));
    }

    @ApiOperation(value = "我的关注", notes = "我的关注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pagesize", value = "每页条数（可选），如果不传默认10条", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/follow/my")
    public Result followMy(@ModelAttribute OneParameter oneParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(oneService.followMy(oneParameter, pageable));
    }

    @ApiOperation(value = "我的粉丝", notes = "我的粉丝")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pagesize", value = "每页条数（可选），如果不传默认10条", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/follow/me")
    public Result followMe(@ModelAttribute OneParameter oneParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(oneService.followMe(oneParameter, pageable));
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

    @ApiOperation(value = "找人", notes = "首页找人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "type", value = "排序方式 （可选，0,最新，1想学最多，2，点击最多，3特别推荐），默认为0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "keyword", value = "标签关键字 （可选),搜索范围：账号，昵称，性别，职业", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pagesize", value = "每页条数（可选），如果不传默认10条", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/search")
    public Result search(@ModelAttribute OneParameter oneParameter) {
        Sort sort = null;
        if (oneParameter.getType() == null || oneParameter.getType() == 0) {
            sort = new Sort(Sort.Direction.DESC, "createtime");
        } else if (oneParameter.getType() == 1) {
            sort = new Sort(Sort.Direction.DESC, "studied");
        } else if (oneParameter.getType() == 2) {
            sort = new Sort(Sort.Direction.DESC, "clicked");
        } else if (oneParameter.getType() == 3) {
            sort = new Sort(Sort.Direction.DESC, "refertime");
        }
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(oneService.search(oneParameter, pageable));
    }

    @ApiOperation(value = "已邀请的好友", notes = "已邀请的好友")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pagesize", value = "每页条数（可选），如果不传默认10条", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/refer")
    public Result refer(@ModelAttribute OneParameter oneParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (oneParameter.getPage() == null) oneParameter.setPage(0);
        if (oneParameter.getPagesize() == null) oneParameter.setPagesize(10);
        Pageable pageable = new PageRequest(oneParameter.getPage(), oneParameter.getPagesize(), sort);
        return ResultUtils.result(oneService.refer(oneParameter, pageable));
    }


    @ApiOperation(value = "上传图片", notes = "上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "image", value = "图片（必需）", required = true, dataType = "File")

    })
    @ResponseBody
    @PostMapping("/upload/image")
    public Result uploadImage(@RequestParam("image") MultipartFile multipartFile) {
        return ResultUtils.result(oneService.uploadImage(multipartFile));
    }


    @ApiOperation(value = "检查是否关注过", notes = "检查是否关注过")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "touserid", value = "要关注的用户id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/follow/is")
    public Result followIs(@ModelAttribute OneParameter oneParameter) {
        return ResultUtils.result(oneService.followIs(oneParameter));
    }



    @ApiOperation(value = "检查是否想学", notes = "检查是否想学")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "touserid", value = "求助id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/user/studied/is")
    public Result studiedIs(@ModelAttribute OneParameter oneParameter) {
        return ResultUtils.result(oneService.studiedIs(oneParameter));
    }


    @ApiOperation(value = "获取后台设置", notes = "获取后台设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型（必需）1代表logo设置，2代表友情链接设置,3推荐关键字", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/setting")
    public Result setting(@ModelAttribute AdminOneParameter adminOneParameter) {
        return ResultUtils.result(oneService.getSetting(adminOneParameter));
    }
}
