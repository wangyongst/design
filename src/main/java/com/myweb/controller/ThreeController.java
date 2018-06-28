package com.myweb.controller;


import com.myweb.service.ThreeService;
import com.myweb.service.TwoService;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ResultUtils;
import com.myweb.vo.ThreeParameter;
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
public class ThreeController {

    @Autowired
    public ThreeService threeService;

    @ApiOperation(value = "点击", notes = "点击")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（可选）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "helpid", value = "求助id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/help/click")
    public Result click(@ModelAttribute ThreeParameter threeParameter) {
        return threeService.click(threeParameter);
    }

    @ApiOperation(value = "想学", notes = "想学")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（可选）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "helpid", value = "求助id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/help/study")
    public Result study(@ModelAttribute ThreeParameter threeParameter) {
        return threeService.study(threeParameter);
    }

    @ApiOperation(value = "发送消息", notes = "发送消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "touserid", value = "发送用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "message", value = "私信（必需）", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/message/send")
    public Result send(@ModelAttribute ThreeParameter threeParameter) {
        return threeService.send(threeParameter);
    }

    @ApiOperation(value = "我的消息（用户列表）", notes = "我的消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/message/user")
    public Result user(@ModelAttribute ThreeParameter threeParameter) {
        return ResultUtils.result(threeService.user(threeParameter));
    }

    @ApiOperation(value = "我的消息（查看消息）", notes = "我的消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "touserid", value = "发送用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "page", value = "页数（可选）从0开始，如果不传默认为0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pagesize", value = "每页条数（可选），如果不传默认10条", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/message/message")
    public Result message(@ModelAttribute ThreeParameter threeParameter) {
        Sort sort = new Sort(Sort.Direction.DESC, "createtime");
        if (threeParameter.getPage() == null) threeParameter.setPage(0);
        Pageable pageable = new PageRequest(threeParameter.getPage(), threeParameter.getPagesize(), sort);
        return ResultUtils.result(threeService.message(threeParameter,pageable));
    }

    @ApiOperation(value = "我的消息（已读消息）", notes = "调用此接口后，与此发送用户的所有相关消息都变为已读")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "当前用户id（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "touserid", value = "发送用户id（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/message/read")
    public Result read(@ModelAttribute ThreeParameter threeParameter) {
        return ResultUtils.result(threeService.read(threeParameter));
    }


}
