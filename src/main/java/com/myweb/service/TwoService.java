package com.myweb.service;


import com.myweb.pojo.Book;
import com.myweb.pojo.Bookstore;
import com.myweb.pojo.Record;
import com.myweb.pojo.User;
import com.utils.Result;

public interface TwoService {

    public Result borrowRequest(Record record);

    public Result borrowAgree(Bookstore bookstore);

    public Result borrowDisagree(Bookstore bookstore);

    public Result borrowConfirm(Bookstore bookstore);
}
