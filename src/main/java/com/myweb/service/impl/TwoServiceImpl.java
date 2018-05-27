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
import com.myweb.vo.TwoParameter;
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
    private RecordRepository recordRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result borrowRequest(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 1) {
            result.setMessage("未找到这本书,或这本书不可借！");
            return result;
        }
        Record record = new Record();
        record.setUser(userRepository.findOne(twoParameter.getUserid()));
        if (StringUtils.isNotBlank(twoParameter.getLetter())) record.setLetter(twoParameter.getLetter());
        record.setStatus(1);
        record.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        record.setBookstore(bookstore);
        recordRepository.save(record);
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result returnRequest(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 2 || bookstore.getUser().getId() != twoParameter.getUserid()) {
            result.setMessage("未找到这本书,或这本书不可还！");
            return result;
        }
        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 7) && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(8);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result borrowAgree(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 1 || bookstore.getOwner().getId() != twoParameter.getOwnerid()) {
            result.setMessage("未找到这本书,或这本书不可借！");
            return result;
        }
        bookstore.getRecord().forEach(e -> {
            if (e.getStatus() == 1 && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(2);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result borrowDisagree(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 1 || bookstore.getOwner().getId() != twoParameter.getOwnerid()) {
            result.setMessage("未找到这本书,或这本书不可借！");
            return result;
        }

        bookstore.getRecord().forEach(e -> {
            if (e.getStatus() == 1 && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(3);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result returnDisagree(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 2 || bookstore.getUser().getId() != twoParameter.getUserid()) {
            result.setMessage("未找到这本书,或这本书不可还！");
            return result;
        }
        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 10) && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(11);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result borrowConfirm(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 1 || bookstore.getOwner().getId() != twoParameter.getOwnerid()) {
            result.setMessage("未找到这本书,或这本书不可借！");
            return result;
        }

        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 6 || e.getStatus() == 4) && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(7);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
                bookstore.setUser(userRepository.findOne(twoParameter.getUserid()));
                bookstoreRepository.save(bookstore);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result returnAgree(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 2 || bookstore.getOwner().getId() != twoParameter.getOwnerid()) {
            result.setMessage("未找到这本书,或这本书不可还！");
            return result;
        }

        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 10) && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(12);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
                bookstore.setUser(null);
                bookstoreRepository.save(bookstore);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result returnFee(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 2 || bookstore.getOwner().getId() != twoParameter.getOwnerid()) {
            result.setMessage("未找到这本书,或这本书不可还！");
            return result;
        }

        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 9 || e.getStatus() == 11) && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(10);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result returnConfirm(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 2 || bookstore.getOwner().getId() != twoParameter.getOwnerid()) {
            result.setMessage("未找到这本书,或这本书不可还！");
            return result;
        }

        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 8) && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(9);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result returnOk(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 2 || bookstore.getOwner().getId() != twoParameter.getOwnerid()) {
            result.setMessage("未找到这本书,或这本书不可还！");
            return result;
        }

        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 9|| e.getStatus() == 11) && e.getUser().getId() == twoParameter.getUserid()) {
                e.setStatus(12);
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }

    @Override
    public Result borrowMyrequest(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        if (twoParameter.getStatus() == null || twoParameter.getStatus() == 0) {
            result.setStatus(1);
            result.setData(recordRepository.findAllByUserid(twoParameter.getUserid()));
        }
        result.setStatus(1);
        result.setData(recordRepository.findAllByUseridAndStatus(twoParameter.getUserid(), twoParameter.getStatus()));
        return result;
    }

    @Override
    public Result borrowTorequest(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getOwnerid() == null || twoParameter.getOwnerid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        if (twoParameter.getStatus() == null || twoParameter.getStatus() == 0) {
            result.setStatus(1);
            result.setData(recordRepository.findAllByOwnerid(twoParameter.getOwnerid()));
            return result;
        }
        result.setStatus(1);
        result.setData(recordRepository.findAllByOwneridAndStatus(twoParameter.getOwnerid(), twoParameter.getStatus()));
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result borrowStart(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getBookstoreid() == null || twoParameter.getBookstoreid() == 0 || twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Bookstore bookstore = bookstoreRepository.findOne(twoParameter.getBookstoreid());
        if (bookstore == null || bookstore.getStatus() != 1) {
            result.setMessage("未找到这本书,或这本书不可借！");
            return result;
        }

        bookstore.getRecord().forEach(e -> {
            if ((e.getStatus() == 2) && e.getUser().getId() == twoParameter.getUserid()) {
                if (bookstore.getDeposit() == null || bookstore.getDeposit().intValue() == 0) {
                    e.setStatus(4);

                } else {
                    e.setStatus(5);
                }
                e.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                recordRepository.save(e);
            }
        });
        result.setStatus(1);
        result.setData(bookstore);
        return result;
    }
}
