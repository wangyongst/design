package com.myweb.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.Book;
import com.myweb.pojo.Bookstore;
import com.myweb.pojo.Record;
import com.myweb.pojo.User;
import com.myweb.service.OneService;
import com.myweb.service.TwoService;
import com.myweb.vo.DBBook;
import com.utils.Result;
import com.utils.WeixinUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("TwoService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
public class TwoServiceImpl implements TwoService {

    private static final Logger logger = LogManager.getLogger(TwoServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookstoreRepository bookstoreRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private RecordRepository recordRepository;


    @Override
    public Result borrowRequest(Record record) {
        Result result = new Result();
        if (record.getBookid() == null || record.getBookid() == 0 || record.getUserid() == null || record.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<Bookstore> bookstoreList = bookstoreRepository.findAllByBookidAndStauts(record.getBookid(), 1);
        if (bookstoreList.size() != 1) {
            result.setMessage("未找到这本书,或这本书不可借！");
            return result;
        }
        record.setStatus(1);
        record.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        recordRepository.save(record);
        result.setStatus(1);
        result.setData(record);
        return result;
    }

    @Override
    public Result borrowAgree(Bookstore bookstore) {
        Result result = new Result();
        if (bookstore.getOwnerid() == null || bookstore.getOwnerid() == 0 || bookstore.getBookid() == null || bookstore.getBookid() == 0 || bookstore.getUserid() == null || bookstore.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<Bookstore> bookstoreList = bookstoreRepository.findAllByBookidAndOwneridAndStauts(bookstore.getBookid(), bookstore.getOwnerid(), 1);
        if (bookstoreList.size() != 1) {
            result.setMessage("未找到这本书,或这本书不可借出！");
            return result;
        }
        List<Record> recordList = recordRepository.findAllByBookidAndUseridAndStatus(bookstore.getBookid(),bookstore.getUserid(),1);
        if (bookstoreList.size() == 0) {
            result.setMessage("未找借书申请！");
            return result;
        }
        recordList.forEach(e->{
            e.setStatus(2);
            e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            recordRepository.save(e);
        });

        result.setStatus(1);
        result.setData(recordList);
        return result;
    }

    @Override
    public Result borrowDisagree(Bookstore bookstore) {
        Result result = new Result();
        if (bookstore.getOwnerid() == null || bookstore.getOwnerid() == 0 || bookstore.getBookid() == null || bookstore.getBookid() == 0 || bookstore.getUserid() == null || bookstore.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<Bookstore> bookstoreList = bookstoreRepository.findAllByBookidAndOwneridAndStauts(bookstore.getBookid(), bookstore.getOwnerid(), 1);
        if (bookstoreList.size() != 1) {
            result.setMessage("未找到这本书,或这本书不可借出！");
            return result;
        }
        List<Record> recordList = recordRepository.findAllByBookidAndUseridAndStatus(bookstore.getBookid(),bookstore.getUserid(),1);
        if (bookstoreList.size() == 0) {
            result.setMessage("未找借书申请！");
            return result;
        }
        recordList.forEach(e->{
            e.setStatus(3);
            e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            recordRepository.save(e);
        });

        result.setStatus(1);
        result.setData(recordList);
        return result;
    }

    @Override
    public Result borrowConfirm(Bookstore bookstore) {
        Result result = new Result();
        if (bookstore.getOwnerid() == null || bookstore.getOwnerid() == 0 || bookstore.getBookid() == null || bookstore.getBookid() == 0 || bookstore.getUserid() == null || bookstore.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<Bookstore> bookstoreList = bookstoreRepository.findAllByBookidAndOwneridAndStauts(bookstore.getBookid(), bookstore.getOwnerid(), 1);
        if (bookstoreList.size() != 1) {
            result.setMessage("未找到这本书,或这本书不可借出！");
            return result;
        }
        List<Record> recordList = recordRepository.findAllByBookidAndUseridAndStatus(bookstore.getBookid(),bookstore.getUserid(),5);
        if (bookstoreList.size() == 0) {
            result.setMessage("未找借书申请！");
            return result;
        }
        recordList.forEach(e->{
            e.setStatus(6);
            e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            recordRepository.save(e);
        });
        bookstoreList.forEach(e->{
            e.setUserid(bookstore.getUserid());
            e.setStauts(2);
            bookstoreRepository.save(e);
        });
        result.setStatus(1);
        result.setData(bookstoreList);
        return result;
    }
}
