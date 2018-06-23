package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.User;
import com.myweb.service.OneService;
import com.myweb.vo.OneParameter;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("OneService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
public class OneServiceImpl implements OneService {

    private static final Logger logger = LogManager.getLogger(OneServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result regist(OneParameter oneParameter) {
        Result result = new Result();
        if (StringUtils.isBlank(oneParameter.getUsername()) || StringUtils.isBlank(oneParameter.getPassword()) || StringUtils.isBlank(oneParameter.getEmail()) || StringUtils.isBlank(oneParameter.getNickname())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        if (userRepository.findByUsername(oneParameter.getUsername()).size() > 0) {
            result.setMessage("用户名已经被占用!");
            return result;
        }
        User user = new User();
        user.setUsername(oneParameter.getUsername());
        user.setPassword(oneParameter.getPassword());
        user.setEmail(oneParameter.getEmail());
        user.setNickname(oneParameter.getNickname());
        userRepository.save(user);
        result.setStatus(1);
        result.setData(user);
        return result;
    }

    @Override
    public Result login(OneParameter oneParameter) {
        Result result = new Result();
        if (StringUtils.isNotBlank(oneParameter.getUsername()) || StringUtils.isNotBlank(oneParameter.getPassword())) {
            List<User> userList = userRepository.findByUsernameAndPassword(oneParameter.getUsername(), oneParameter.getPassword());
            if (userList.size() == 1) {
                result.setStatus(1);
                result.setData(userList.get(0));
                return result;
            } else if (userList.size() == 0) {
                result.setMessage("用户不存在或密码错误！");
                return result;
            }
        } else if (StringUtils.isNotBlank(oneParameter.getEmail()) || StringUtils.isNotBlank(oneParameter.getPassword())) {
            List<User> userList = userRepository.findByEmailAndPassword(oneParameter.getEmail(), oneParameter.getPassword());
            if (userList.size() == 1) {
                result.setStatus(1);
                result.setData(userList.get(0));
                return result;
            } else if (userList.size() == 0) {
                result.setMessage("邮箱不存在或密码错误！");
                return result;
            }
        } else {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        result.setMessage("登录失败！");
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result setBasic(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || StringUtils.isBlank(oneParameter.getUsername()) || StringUtils.isBlank(oneParameter.getNickname())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            user.setUsername(oneParameter.getUsername());
            user.setNickname(oneParameter.getNickname());
            user.setMobile(oneParameter.getMobile());
            user.setJobs(oneParameter.getJobs());
            user.setSex(oneParameter.getSex());
            userRepository.save(user);
            result.setStatus(1);
            result.setData(user);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result setAvatar(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || StringUtils.isBlank(oneParameter.getAvatar())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            user.setAvatar(oneParameter.getAvatar());
            userRepository.save(user);
            result.setStatus(1);
            result.setData(user);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result setPassword(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || StringUtils.isBlank(oneParameter.getPassword()) || StringUtils.isBlank(oneParameter.getPassword2())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null) {
            result.setMessage("当前用户不存在或未登录!");
        } else if (StringUtils.equals(user.getPassword(), oneParameter.getPassword())) {
            user.setPassword(oneParameter.getPassword2());
            userRepository.save(user);
            result.setStatus(1);
            result.setData(user);
        } else {
            result.setMessage("未找到这个用户,或旧密码不正确！");
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result setEmail(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || StringUtils.isBlank(oneParameter.getEmail())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            user.setEmail(oneParameter.getEmail());
            userRepository.save(user);
            result.setStatus(1);
            result.setData(user);
        }
        return result;
    }
}
