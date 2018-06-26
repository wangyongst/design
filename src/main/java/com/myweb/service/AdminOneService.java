package com.myweb.service;


import com.myweb.vo.OneParameter;
import com.myweb.vo.ThreeParameter;
import com.utils.Result;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;

public interface AdminOneService {
    public Result login(OneParameter oneParameter,HttpSession httpSession);

    public Result logout(OneParameter oneParameter,HttpSession httpSession);

    public Result showlog(Pageable pageable,HttpSession httpSession);

    public Result studyCount(HttpSession httpSession);

    public Result clickCount(HttpSession httpSession);

    public Result studyCountHelp(ThreeParameter threeParameter, HttpSession httpSession);

    public Result clickCountHelp(ThreeParameter threeParameter, HttpSession httpSession);

    public Result helpRefer(ThreeParameter threeParameter, HttpSession httpSession);
}
