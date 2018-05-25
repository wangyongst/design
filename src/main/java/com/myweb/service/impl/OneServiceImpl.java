package com.myweb.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.Book;
import com.myweb.pojo.Bookstore;
import com.myweb.pojo.User;
import com.myweb.service.OneService;
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
import java.util.ArrayList;
import java.util.List;


@Service("OneService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
public class OneServiceImpl implements OneService {

    private static final Logger logger = LogManager.getLogger(OneServiceImpl.class);

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
    public Result scan(Book book) {
        Result result = new Result();
        if (StringUtils.isBlank(book.getIsbn())) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        String url = "https://api.douban.com/v2/book/isbn/";
        String response = null;
        try {
            response = restTemplate.getForObject(url + book.getIsbn(), String.class);
        } catch (RestClientException e) {
            result.setMessage("Douban api has exception!");
            return result;
        }
        ObjectMapper mapper = new ObjectMapper();
        DBBook dbBook = null;
        try {
            dbBook = mapper.readValue(response, DBBook.class);
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("Douban api result has exception!");
            return result;
        }
        String translators = "";
        for (String translator : dbBook.getTranslator()) {
            translators = translators + "," + translator;
        }
        if (StringUtils.isNotBlank(translators)) {
            book.setTranslator(translators.substring(1));
        }
        book.setIsbn(dbBook.getIsbn10() + "," + dbBook.getIsbn13());
        book.setTitle(dbBook.getTitle());
        book.setImage(dbBook.getImage());
        book.setPublisher(dbBook.getPublisher());
        if (StringUtils.isNotBlank(dbBook.getPrice())) {
            book.setPrice(new BigDecimal(dbBook.getPrice().substring(0, dbBook.getPrice().length() - 1)));
        }
        book.setAuthorintro(dbBook.getAuthor_intro());
        book.setRating(dbBook.getRating().getAverage());
        result.setStatus(1);
        result.setData(book);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result isbn(Book book) {
        Result result = new Result();
        if (StringUtils.isBlank(book.getIsbn()) || book.getUserid() == 0) {
            result.setMessage("The required parameters are empty!");
            return result;
        }
        String url = "https://api.douban.com/v2/book/isbn/";
        String response = null;
        try {
            response = restTemplate.getForObject(url + book.getIsbn(), String.class);
        } catch (RestClientException e) {
            result.setMessage("从豆瓣获取图书信息失败!");
            return result;
        }
        ObjectMapper mapper = new ObjectMapper();
        DBBook dbBook = null;
        try {
            dbBook = mapper.readValue(response, DBBook.class);
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("从豆瓣获取图书信息失败!");
            return result;
        }
        String authors = "";
        for (String author : dbBook.getAuthor()) {
            authors = authors + "," + author;
        }
        if (StringUtils.isNotBlank(authors)) {
            book.setAuthor(authors.substring(1));
        }

        String translators = "";
        for (String translator : dbBook.getTranslator()) {
            translators = translators + "," + translator;
        }
        if (StringUtils.isNotBlank(translators)) {
            book.setTranslator(translators.substring(1));
        }
        book.setIsbn(dbBook.getIsbn10() + "," + dbBook.getIsbn13());
        book.setTitle(dbBook.getTitle());
        book.setImage(dbBook.getImage());
        book.setPublisher(dbBook.getPublisher());
        if (StringUtils.isNotBlank(dbBook.getPrice())) {
            book.setPrice(new BigDecimal(dbBook.getPrice().substring(0, dbBook.getPrice().length() - 1)));
        }
        book.setAuthorintro(dbBook.getAuthor_intro());
        book.setRating(dbBook.getRating().getAverage());
        book.setCatalog(dbBook.getCatalog());
        bookRepository.save(book);
        Bookstore bookstore = new Bookstore();
        bookstore.setBook(book);
        bookstore.setOwnerid(book.getUserid());
        bookstore.setStauts(1);
        bookstoreRepository.save(bookstore);
        result.setStatus(1);
        result.setData(book);
        return result;
    }

    @Override
    public Result list(Bookstore bookstore) {
        Result result = new Result();
        if (bookstore.getOwnerid() == null || bookstore.getOwnerid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<Bookstore> bookstoreList = new ArrayList<>();
        if (bookstore.getStauts() == null || bookstore.getStauts() == 0) {
            bookstoreList = bookstoreRepository.findAllByOwneridOrUserid(bookstore.getOwnerid(), bookstore.getOwnerid());
        } else if(bookstore.getStauts() == 1 || bookstore.getStauts() == 2) {
            bookstoreList = bookstoreRepository.findAllByOwneridAndStauts(bookstore.getOwnerid(), bookstore.getStauts());
        }
        else if(bookstore.getStauts() == 3) {
            bookstoreList = bookstoreRepository.findAllByUseridAndStauts(bookstore.getOwnerid(), bookstore.getStauts());
        }
        if (bookstoreList.size() == 0) {
            result.setMessage("你的书架为空!");
            return result;
        }
        result.setStatus(1);
        result.setData(bookstoreList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result out(Bookstore bookstore) {
        Result result = new Result();
        if (bookstore.getOwnerid() == null || bookstore.getOwnerid() == 0 || bookstore.getBookid() == null || bookstore.getBookid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<Book> bookList = bookRepository.findAllByIdAndUserid(bookstore.getBookid(), bookstore.getOwnerid());
        if (bookList.size() < 1) {
            result.setMessage("未找到这本书,或这本书不是你上架的图书！");
            return result;
        }
        int out = bookstoreRepository.deleteAllByBookidAndOwneridAndStauts(bookstore.getBookid(), bookstore.getOwnerid(), 1);
        if (out > 0) {
            result.setStatus(1);
            return result;
        }
        result.setMessage("这本书目前不是自有状态，不能下架！");
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result weixinCode(String code) {
        Result result = new Result();
        if (StringUtils.isBlank(code)) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        String response = WeixinUtils.getOpenId(restTemplate, code);
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(response, User.class);
            if (StringUtils.isNotBlank(user.getOpenid())) {
                List<User> userList = userRepository.findByOpenid(user.getOpenid());
                if (userList.size() == 1) {
                    result.setStatus(1);
                    result.setData(userList.get(0));
                    return result;
                } else if (userList.size() == 0) {
                    userRepository.save(user);
                    result.setStatus(1);
                    result.setData(user);
                    return result;
                } else {
                    result.setMessage("openid存在重复记录");
                    return result;
                }
            } else {
                result.setMessage("授权失败，无法获取openid");
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result regist(User user) {
        Result result = new Result();
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) || user.getId() != 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        if (userRepository.findByUsername(user.getUsername()).size() > 0) {
            result.setMessage("用户名已经被占用!");
            return result;
        }
        userRepository.save(user);
        result.setStatus(1);
        result.setData(user);
        return result;
    }

    @Override
    public Result login(User user) {
        Result result = new Result();
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<User> userList = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userList.size() == 1) {
            result.setStatus(1);
            result.setData(userList.get(0));
            return result;
        } else if (userList.size() == 0) {
            result.setMessage("用户不存在或密码错误！");
            return result;
        }
        result.setMessage("登录失败！");
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result set(Bookstore bookstore) {
        Result result = new Result();
        if (bookstore.getOwnerid() == null || bookstore.getOwnerid() == 0 || bookstore.getBookid() == null || bookstore.getBookid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<Book> bookList = bookRepository.findAllByIdAndUserid(bookstore.getBookid(), bookstore.getOwnerid());
        if (bookList.size() < 1) {
            result.setMessage("未找到这本书,或这本书不是你上架的图书！");
            return result;
        }
        List<Bookstore> bookstoreList = bookstoreRepository.findAllByBookidAndOwneridAndStauts(bookstore.getBookid(), bookstore.getOwnerid(), 1);
        if (bookstoreList.size() == 0) {
            result.setMessage("这本书目前不是自有状态，不能变更属性！");
            return result;
        }
        bookstoreList.forEach(e -> {
            if (bookstore.getDeposit() != null) e.setDeposit(bookstore.getDeposit());
            if (bookstore.getDays() != null) e.setDays(bookstore.getDays());
            if (bookstore.getFee() != null) e.setFee(bookstore.getFee());
            bookstoreRepository.save(e);
        });
        result.setStatus(1);
        return result;
    }

    @Override
    public Result weixinLogin(User user) {
        Result result = new Result();
        if (StringUtils.isBlank(user.getOpenid())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        List<User> userList = userRepository.findByOpenid(user.getOpenid());
        if (userList.size() == 1) {
            result.setStatus(1);
            result.setData(userList.get(0));
            return result;
        } else if (userList.size() == 0) {
            result.setMessage("用户不存在！");
            return result;
        }
        result.setMessage("登录失败！");
        return result;
    }
}
