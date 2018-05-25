package com.myweb.controller;

import com.myweb.pojo.Book;
import com.myweb.pojo.Bookstore;
import com.myweb.pojo.Record;
import com.myweb.service.TwoService;
import com.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api
@CrossOrigin("*")
@Controller
public class TwoController {

    @Autowired
    public TwoService twoService;

    @ApiOperation(value = "借书申请", notes = "借书申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookid", value = "bookid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/borrow/request")
    public Result borrowRequest(@ModelAttribute Record record) {
        return twoService.borrowRequest(record);
    }

    @ApiOperation(value = "同意借书", notes = "同意借书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookid", value = "bookid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/borrow/agree")
    public Result borrowAgree(@ModelAttribute Bookstore bookstore, @ModelAttribute Record record) {
        return twoService.borrowAgree(bookstore, record);
    }

    @ApiOperation(value = "拒绝借书", notes = "拒绝借书")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookid", value = "bookid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @PostMapping("/borrow/disagree")
    public Result borrowDisagree(@ModelAttribute Bookstore bookstore, @ModelAttribute Record record) {
        return twoService.borrowDisagree(bookstore, record);
    }

    @ApiOperation(value = "确认借书", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bookid", value = "bookid（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userid", value = "申请者（必需）", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "ownerid", value = "所有者（必需）", required = true, dataType = "Integer")
    })
    @ResponseBody
    @GetMapping("/borrow/confirm")
    public Result borrowConfirm(@ModelAttribute Bookstore bookstore, @ModelAttribute Record record) {
        return twoService.borrowConfirm(bookstore, record);
    }

}
