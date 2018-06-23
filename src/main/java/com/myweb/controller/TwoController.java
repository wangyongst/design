package com.myweb.controller;


import com.myweb.service.OneService;
import com.myweb.service.TwoService;
import com.myweb.vo.OneParameter;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api
@CrossOrigin("*")
@Controller
public class TwoController {

    @Autowired
    public TwoService twoService;

    @ApiOperation(value = "发布效果求助", notes = "发布效果求助")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "audience", value = " 观众（必需,1所有，2粉丝，3自己）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = " 标题（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "image", value = "图片 （必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = " 问题描述（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "video", value = " 导入视频（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "tag", value = "标签（必需，多个标签请以@符隔开传递）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "design", value = "设计分类 （必需）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "background", value = " 背景颜色（可选）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "set", value = " 设置（可选，1定时发表）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "draft", value = "发布方式 （必需，1草稿，2立即发布）", required = true, dataType = "Integer")

    })
    @ResponseBody
    @PostMapping("/seek/help ")
    public Result help (@ModelAttribute TwoParameter twoParameter) {
        return twoService.help(twoParameter);
    }

    @ApiOperation(value = "已发求助", notes = "已发求助")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "draft", value = "发布方式 （可选，0,全部，1草稿，2，隐藏，3未通过审核，4已经发表）", required = true, dataType = "Integer")

    })
    @ResponseBody
    @PostMapping("/user/help ")
    public Result userHelp (@ModelAttribute TwoParameter twoParameter) {
        return twoService.userHelp(twoParameter);
    }
}
