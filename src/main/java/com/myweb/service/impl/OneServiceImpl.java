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
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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
        Book savedbook = bookRepository.save(book);
        Bookstore bookstore = new Bookstore();
        bookstore.setBookid(savedbook.getId());
        bookstore.setOwnerid(book.getUserid());
        bookstore.setStauts(0);
        bookstoreRepository.save(bookstore);
        result.setStatus(1);
        result.setData(book);
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
