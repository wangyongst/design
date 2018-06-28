package com.myweb.service;


import com.myweb.vo.TwoParameter;
import com.utils.Result;
import org.springframework.data.domain.Pageable;

public interface TwoService {

    public Result seek(TwoParameter twoParameter);

    public Result user(TwoParameter twoParameter, Pageable pageable);

    public Result index(TwoParameter twoParameter, Pageable pageable);

    public Result search(TwoParameter twoParameter, Pageable pageable);

    public Result mine(TwoParameter twoParameter, Pageable pageable);

    public Result advert(Pageable pageable);

    public Result delete(TwoParameter twoParameter, String helpids);
}
