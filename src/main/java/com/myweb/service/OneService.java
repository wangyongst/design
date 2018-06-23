package com.myweb.service;


import com.myweb.pojo.*;
import com.myweb.vo.OneParameter;
import com.utils.Result;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface OneService {

    public Result regist(OneParameter oneParameter);

    public Result login(OneParameter oneParameter);

    public Result setBasic(OneParameter oneParameter);

    public Result setAvatar(OneParameter oneParameter);

    public Result setPassword(OneParameter oneParameter);

    public Result setEmail(OneParameter oneParameter);

    public Result followMy(OneParameter oneParameter, Pageable pageable);

    public Result followMe(OneParameter oneParameter, Pageable pageable);

    public Result follow(OneParameter oneParameter);

    public Result unfollow(OneParameter oneParameter);
}
