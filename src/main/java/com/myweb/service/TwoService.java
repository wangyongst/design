package com.myweb.service;


import com.myweb.vo.OneParameter;
import com.myweb.vo.TwoParameter;
import com.utils.Result;

public interface TwoService {

    public Result help(TwoParameter twoParameter);

    public Result userHelp(TwoParameter twoParameter);
}
