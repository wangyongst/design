package com.myweb.service;


import com.myweb.vo.ThreeParameter;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import org.springframework.data.domain.Pageable;

public interface ThreeService {

    public Result click(ThreeParameter threeParameter);

    public Result recommend(ThreeParameter threeParameter);

    public Result study(ThreeParameter threeParameter);

    public Result send(ThreeParameter threeParameter);

    public Result help(ThreeParameter threeParameter);

    public Result helpStudied(ThreeParameter threeParameter,Pageable pageable);

    public Result followHelp(ThreeParameter threeParameter,Pageable pageable);

    public Result userDelete(ThreeParameter threeParameter);

    public Result messageDelete(ThreeParameter threeParameter);

    public Result user(ThreeParameter threeParameter);

    public Result userMost(ThreeParameter threeParameter,Pageable pageable);

    public Result userMostHelp(ThreeParameter threeParameter,Pageable pageable);

    public Result message(ThreeParameter threeParameter,Pageable pageable);

    public Result noticeNew(ThreeParameter threeParameter,Pageable pageable);

    public Result read(ThreeParameter threeParameter);
}
