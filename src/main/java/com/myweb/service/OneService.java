package com.myweb.service;


import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.utils.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface OneService {

    public Result regist(OneParameter oneParameter);

    public Result login(OneParameter oneParameter);

    public Result logout(OneParameter oneParameter);

    public Result setBasic(OneParameter oneParameter);

    public Result setAvatar(OneParameter oneParameter);

    public Result setPassword(OneParameter oneParameter);

    public Result setEmail(OneParameter oneParameter);

    public Result followMy(OneParameter oneParameter, Pageable pageable);

    public Result refer(OneParameter oneParameter, Pageable pageable);

    public Result getSetting(AdminOneParameter adminOneParameter);

    public Result followMe(OneParameter oneParameter, Pageable pageable);

    public Result follow(OneParameter oneParameter);

    public Result unfollow(OneParameter oneParameter);

    public Result followIs(OneParameter oneParameter);

    public Result search(OneParameter oneParameter, Pageable pageable);

    public Result uploadImage(MultipartFile multipartFile);
}
