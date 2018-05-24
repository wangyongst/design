package com.myweb.service;


import com.myweb.pojo.*;
import com.utils.Result;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface OneService {

    public Result scan(Book book);

    public Result isbn(Book book);

    public Result weixinCode(String code);

    public Result weixinLogin(User user);

    public Result regist(User user);

    public Result login(User user);
}
