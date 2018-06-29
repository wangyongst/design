package com.myweb.service;

import com.myweb.pojo.User;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ThreeParameter;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface AdminOneService {
    public Result login(OneParameter oneParameter, HttpSession httpSession);

    public Result logout(HttpSession httpSession);

    public Result userList(OneParameter oneParameter, Pageable pageable, HttpSession httpSession);

    public Result showlog(Pageable pageable, HttpSession httpSession);

    public Result userToken(Pageable pageable, HttpSession httpSession);

    public Result user(OneParameter oneParameter, HttpSession httpSession);

    public Result studyCount(HttpSession httpSession);

    public Result countUser(HttpSession httpSession);

    public Result countMessage(HttpSession httpSession);

    public Result countMessageUser(OneParameter oneParameter, HttpSession httpSession);

    public Result countFollow(OneParameter oneParameter, HttpSession httpSession);

    public Result follow(OneParameter oneParameter,Pageable pageable, HttpSession httpSession);

    public Result countToken(HttpSession httpSession);

    public Result clickCount(HttpSession httpSession);

    public Result clickMost(Pageable pageable, HttpSession httpSession);

    public Result help(TwoParameter twoParameter, HttpSession httpSession);

    public Result postHelp(TwoParameter twoParameter, HttpSession httpSession);

    public Result setting(AdminOneParameter adminOneParameter, HttpSession httpSession);

    public Result advert(AdminOneParameter adminOneParameter, HttpSession httpSession);

    public Result getSetting(AdminOneParameter adminOneParameter,HttpSession httpSession);

    public Result helpRefer(TwoParameter twoParameter, HttpSession httpSession);


}
