package com.myweb.service;


import com.myweb.pojo.Book;
import com.myweb.pojo.Bookstore;
import com.myweb.pojo.Record;
import com.myweb.pojo.User;
import com.myweb.vo.TwoParameter;
import com.utils.Result;

public interface TwoService {

    public Result borrowRequest(TwoParameter twoParameter);

    public Result returnRequest(TwoParameter twoParameter);

    public Result returnDisagree(TwoParameter twoParameter);

    public Result borrowAgree(TwoParameter twoParameter);

    public Result returnAgree(TwoParameter twoParameter);

    public Result returnOk(TwoParameter twoParameter);

    public Result borrowDisagree(TwoParameter twoParameter);

    public Result borrowConfirm(TwoParameter twoParameter);

    public Result returnConfirm(TwoParameter twoParameter);

    public Result returnFee(TwoParameter twoParameter);

    public Result borrowMyrequest(TwoParameter twoParameter);

    public Result borrowTorequest(TwoParameter twoParameter);

    public Result borrowStart(TwoParameter twoParameter);
}
