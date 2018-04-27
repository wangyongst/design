package com.myweb.controller;

import com.myweb.pojo.*;
import com.myweb.service.OneService;
import com.myweb.vo.FisherySend;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@Controller
public class OneController {

    @Autowired
    public OneService oneService;

    //注册
    @ResponseBody
    @RequestMapping(value = "/user/registy", method = RequestMethod.POST)
    public Result registy(@ModelAttribute User user) {
        return oneService.registry(user);
    }

    //设置昵称
    @ResponseBody
    @RequestMapping(value = "/user/setNickName", method = RequestMethod.POST)
    public Result setNickName(@ModelAttribute User user) {
        return oneService.setNickName(user);
    }

    //设置头像
    @ResponseBody
    @RequestMapping(value = "/user/setAvatar", method = RequestMethod.POST)
    public Result setAvatar(@RequestParam("avatarFile") MultipartFile multipartFile, @ModelAttribute User user) {
        return oneService.setAvatar(multipartFile, user);
    }

    //获取推荐码
    @ResponseBody
    @RequestMapping(value = "/user/getReferUrl", method = RequestMethod.GET)
    public Result getReferUrl(@ModelAttribute User user) {
        return oneService.getReferUrl(user);
    }


    //获取用户
    @ResponseBody
    @RequestMapping(value = "/user/get", method = RequestMethod.GET)
    public Result get(@ModelAttribute User user) {
        return oneService.get(user);
    }

    @ResponseBody
    @RequestMapping(value = "/fishery/get", method = RequestMethod.GET)
    public Result getfishery(@ModelAttribute Fishery fishery) {
        return oneService.getFishery(fishery);
    }

    //绑定游戏地址
    @ResponseBody
    @RequestMapping(value = "/fishery/bind", method = RequestMethod.POST)
    public Result bind(@ModelAttribute Fishery fishery, @ModelAttribute Opslog opslog) {
        opslog.setAction("绑定渔场");
        opslog.setMemo("渔场ID:" + fishery.getId() + " ,绑定地址:" + fishery.getAddress());
        Result result = oneService.createLog(opslog);
        return oneService.bind(fishery);
    }

    //设置渔场名称
    @ResponseBody
    @RequestMapping(value = "/fishery/setName", method = RequestMethod.POST)
    public Result setName(@ModelAttribute Fishery fishery, @ModelAttribute Opslog opslog) {
        return oneService.setName(fishery);
    }


    //赠送渔场
    @ResponseBody
    @RequestMapping(value = "/fishery/send", method = RequestMethod.POST)
    public Result send(@ModelAttribute FisherySend fisherySend, @ModelAttribute Opslog opslog) {
        if (fisherySend.getId() == 0 || StringUtils.isBlank(fisherySend.getAddress()) || StringUtils.isBlank(fisherySend.getSendAddress())) {
            Result result = new Result();
            result.setMessage("The required parameters are empty!");
            return result;
        }
        opslog.setAction("赠送渔场");
        opslog.setMemo("渔场ID:" + fisherySend.getId()  + " ,赠送地址:" + fisherySend);
        return oneService.createLogAsResult(opslog);
    }

    //取消绑定id
    @ResponseBody
    @RequestMapping(value = "/fishery/unbind", method = RequestMethod.POST)
    public Result unbind(@ModelAttribute Fishery fishery, @ModelAttribute Opslog opslog) {
        opslog.setAction("解除渔场绑定");
        opslog.setMemo("渔场ID:" + fishery.getId());
        Result result = oneService.createLog(opslog);
        return oneService.unbind(fishery);
    }

    //出售
    @ResponseBody
    @RequestMapping(value = "/fishery/sell", method = RequestMethod.POST)
    public Result sell(@ModelAttribute Market market, @ModelAttribute Opslog opslog) {
        opslog.setAction("出售渔场");
        opslog.setMemo("渔场ID:" + market.getFisheryId() + ",起始价格:" + market.getStartPrice() + ", 终止价格:" + market.getStopPrice() + ", 销售时长:" + market.getSellDuration() + "天");
        return oneService.createLogAsResult(opslog);
    }

    //取消出售
    @ResponseBody
    @RequestMapping(value = "/fishery/unsell", method = RequestMethod.POST)
    public Result unsell(@ModelAttribute Market market, @ModelAttribute Opslog opslog) {
        opslog.setAction("取消出售渔场");
        opslog.setMemo("渔场ID:" + market.getFisheryId());
        return oneService.createLogAsResult(opslog);
    }

    //购买渔场
    @ResponseBody
    @RequestMapping(value = "/market/buy", method = RequestMethod.POST)
    public Result buy(@ModelAttribute Fishery fishery, @ModelAttribute Refer refer, @ModelAttribute Opslog opslog) {
        if (fishery.getId() == 0) {
            opslog.setAction("购买新渔场");
        } else {
            opslog.setAction("购买渔场");
            opslog.setMemo("渔场ID:" + fishery.getId());
        }
        Result result = oneService.createLog(opslog);
        return oneService.buy(fishery, refer);
    }

    //赞
    @ResponseBody
    @RequestMapping(value = "/market/favor", method = RequestMethod.POST)
    public Result favor(@ModelAttribute Market market, @ModelAttribute Opslog opslog) {
        opslog.setAction("赞");
        Result result = oneService.createLog(opslog);
        return oneService.favor(market);
    }

    //查询
    @ResponseBody
    @RequestMapping(value = "/opslog/query", method = RequestMethod.GET)
    public Result query(@ModelAttribute Opslog opslog, @RequestParam int pageNumber, @RequestParam int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "actionTime");
        Pageable pageable = new PageRequest(pageNumber, pageSize, sort);
        return oneService.query(opslog, pageable);
    }
}
