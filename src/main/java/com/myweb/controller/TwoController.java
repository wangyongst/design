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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/help/seek")
    public Result seek(@ModelAttribute TwoParameter twoParameter) {
        return twoService.seek(twoParameter);
    }

    @ApiOperation(value = "已发求助", notes = "已发求助")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "draft", value = "发布方式 （可选，0,全部，1草稿，2，隐藏，3未通过审核，4已经发表）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0，每页10条分页", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/help/user")
    public Result user(@ModelAttribute TwoParameter twoParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (twoParameter.getPage() == null) twoParameter.setPage(0);
        Pageable pageable = new PageRequest(twoParameter.getPage(), 10, sort);
        return twoService.user(twoParameter, pageable);
    }


    @ApiOperation(value = "首页求助推荐", notes = "首页推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "推荐方式 （可选，0,最新，1想学最多，2，点击最多，3特别推荐），默认为0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "design", value = "设计分类 （可选)", required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/help/index")
    public Result index(@ModelAttribute TwoParameter twoParameter) {
        Sort sort = null;
        if (twoParameter.getType() == null || twoParameter.getType() == 0) {
            sort = new Sort(Sort.Direction.DESC, "createtime");
        } else if (twoParameter.getType() == 1) {
            sort = new Sort(Sort.Direction.DESC, "studied");
        } else if (twoParameter.getType() == 2) {
            sort = new Sort(Sort.Direction.DESC, "clicked");
        } else if (twoParameter.getType() == 3) {
            sort = new Sort(Sort.Direction.DESC, "refertime");
        }
        if (twoParameter.getPage() == null) twoParameter.setPage(0);
        Pageable pageable = new PageRequest(twoParameter.getPage(), 10, sort);
        return twoService.index(twoParameter, pageable);
    }

    @ApiOperation(value = "删除求助", notes = "删除求助")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "helpids", value = "求助id(必需），有多个时以，号隔开，如1，2，3", required = true, dataType = "String"),
    })
    @ResponseBody
    @PostMapping("/help/delete")
    public Result delete(@ModelAttribute TwoParameter twoParameter, @RequestParam String helpids) {
        return twoService.delete(twoParameter, helpids);
    }
}
