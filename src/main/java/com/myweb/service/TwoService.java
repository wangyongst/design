package com.myweb.service;


import com.myweb.vo.OneParameter;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import org.springframework.data.domain.Pageable;

public interface TwoService {

    public Result seek(TwoParameter twoParameter);

    public Result user(TwoParameter twoParameter,Pageable pageable);
}
