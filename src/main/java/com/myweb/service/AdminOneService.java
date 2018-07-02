package com.myweb.service;

import com.myweb.pojo.User;
import com.myweb.vo.*;
import com.utils.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface AdminOneService {
    public Result login(OneParameter oneParameter, HttpSession httpSession);

    public Result userMe(HttpSession httpSession);

    public Result userAdminList(HttpSession httpSession);

    public Result userAdmin(FourParameter fourParameter, HttpSession httpSession);

    public Result postUserAdmin(FourParameter fourParameter, HttpSession httpSession);

    public Result userRoleList(HttpSession httpSession);

    public Result userRole(FourParameter fourParameter, HttpSession httpSession);

    public Result postUserRole(FourParameter fourParameter, HttpSession httpSession);

    public Result userPrivilege(FourParameter fourParameter, HttpSession httpSession);

    public Result postUserPrivilege(FourParameter fourParameter, HttpSession httpSession);

    public Result userMenuList(HttpSession httpSession);

    public Result logout(HttpSession httpSession);

    public Result userList(OneParameter oneParameter, Pageable pageable, HttpSession httpSession);

    public Result showlog(HttpSession httpSession);

    public Result userToken(Pageable pageable, HttpSession httpSession);

    public Result user(OneParameter oneParameter, HttpSession httpSession);

    public Result studyCount(HttpSession httpSession);

    public Result countUser(HttpSession httpSession);

    public Result countMessage(HttpSession httpSession);

    public Result countMessageUser(OneParameter oneParameter, HttpSession httpSession);

    public Result countFollow(OneParameter oneParameter, HttpSession httpSession);

    public Result follow(OneParameter oneParameter, Pageable pageable, HttpSession httpSession);

    public Result countToken(HttpSession httpSession);

    public Result clickCount(HttpSession httpSession);

    public Result clickMost(Pageable pageable, HttpSession httpSession);

    public Result help(TwoParameter twoParameter, HttpSession httpSession);

    public Result postHelp(TwoParameter twoParameter, HttpSession httpSession);

    public Result setting(AdminOneParameter adminOneParameter, HttpSession httpSession);

    public Result advert(AdminOneParameter adminOneParameter, HttpSession httpSession);

    public Result getSetting(AdminOneParameter adminOneParameter, HttpSession httpSession);

    public Result helpRefer(TwoParameter twoParameter, HttpSession httpSession);


}
