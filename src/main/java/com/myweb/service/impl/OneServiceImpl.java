package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.FollowRepository;
import com.myweb.dao.jpa.hibernate.SettingRepository;
import com.myweb.dao.jpa.hibernate.TokenRepository;
import com.myweb.dao.jpa.hibernate.UserRepository;
import com.myweb.pojo.Follow;
import com.myweb.pojo.Token;
import com.myweb.pojo.User;
import com.myweb.service.OneService;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("OneService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
public class OneServiceImpl implements OneService {

    private static final Logger logger = LogManager.getLogger(OneServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private FollowRepository followRepository;

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
        if (oneParameter.getRefer() != null && oneParameter.getRefer() != 0) {
            User refer = userRepository.findOne(oneParameter.getRefer());
            if (refer == null) {
                result.setMessage("推荐人不存在!");
                return result;
            } else {
                user.setRefer(refer.getId());
            }
        }
        user.setUsername(oneParameter.getUsername());
        user.setPassword(oneParameter.getPassword());
        user.setEmail(oneParameter.getEmail());
        user.setNickname(oneParameter.getNickname());
        user.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        userRepository.save(user);
        result.setStatus(1);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result login(OneParameter oneParameter) {
        Result result = new Result();
        if (StringUtils.isNotBlank(oneParameter.getUsername()) || StringUtils.isNotBlank(oneParameter.getPassword())) {
            List<User> userList = userRepository.findByUsernameAndPassword(oneParameter.getUsername(), oneParameter.getPassword());
            if (userList.size() == 1) {
                result.setStatus(1);
                Token token = new Token();
                token.setUser(userList.get(0));
                token.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                token.setExpiretime(new Date().getTime() + 60000 * 120);
                tokenRepository.save(token);
                result.setData(userList.get(0));
                return result;
            } else if (userList.size() != 1) {
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
    public Result logout(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
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
    public Result setBasic(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || StringUtils.isBlank(oneParameter.getUsername()) || StringUtils.isBlank(oneParameter.getNickname())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            Token token = tokenRepository.findTop1ByUserOrderByCreatetimeDesc(user);
            token.setOuttime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
            result.setStatus(1);
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
        if (user == null || isNotLogin(user)) {
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
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else if (StringUtils.equals(user.getPassword(), oneParameter.getPassword())) {
            user.setPassword(oneParameter.getPassword2());
            userRepository.save(user);
            result.setStatus(1);
            result.setData(user);
        } else {
            result.setMessage("旧密码不正确！");
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
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            user.setEmail(oneParameter.getEmail());
            userRepository.save(user);
            result.setStatus(1);
            result.setData(user);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result follow(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || oneParameter.getTouserid() == null || oneParameter.getTouserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            User touser = userRepository.findOne(oneParameter.getTouserid());
            if (touser == null) {
                result.setMessage("要关注的用户不存在!");
            } else {
                Follow follow = new Follow();
                follow.setUser(user);
                follow.setTouser(touser);
                follow.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                followRepository.save(follow);
                result.setStatus(1);
            }
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result unfollow(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || oneParameter.getTouserid() == null || oneParameter.getTouserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            User touser = userRepository.findOne(oneParameter.getTouserid());
            if (touser == null) {
                result.setMessage("要取消关注的用户不存在!");
            } else {
                List<Follow> follows = followRepository.findByUserAndTouser(user, touser);
                follows.forEach(e -> {
                    followRepository.delete(e);
                });
                result.setStatus(1);
            }
        }
        return result;
    }

    @Override
    public Result followMy(OneParameter oneParameter, Pageable pageable) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            result.setStatus(1);
            result.setData(followRepository.findByUser(user, pageable));
        }
        return result;
    }

    @Override
    public Result followMe(OneParameter oneParameter, Pageable pageable) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            result.setStatus(1);
            result.setData(followRepository.findByTouser(user, pageable));
        }
        return result;
    }

    @Override
    public Result getSetting(AdminOneParameter adminOneParameter) {
        Result result = new Result();
        if (adminOneParameter.getType() == null || adminOneParameter.getType() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        result.setStatus(1);
        result.setData(settingRepository.findAllByType(adminOneParameter.getType()));
        return result;
    }

    @Override
    public Result search(OneParameter oneParameter, Pageable pageable) {
        Result result = new Result();
        result.setStatus(1);
        if (oneParameter.getKeyword() == null) {
            result.setData(userRepository.findAll(pageable));
        } else {
            result.setData(userRepository.findAllByUsernameOrNicknameOrSexOrJobs(oneParameter.getKeyword(), pageable));
        }
        return result;
    }


    @Override
    public Result refer(OneParameter oneParameter, Pageable pageable) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            result.setStatus(1);
            result.setData(userRepository.findByRefer(user.getId(), pageable));
        }
        return result;
    }

    @Override
    public Result followIs(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || oneParameter.getTouserid() == null || oneParameter.getTouserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            User touser = userRepository.findOne(oneParameter.getTouserid());
            if (touser == null) {
                result.setMessage("要检查关注的用户不存在!");
            } else {
                List<Follow> follows = followRepository.findByUserAndTouser(user, touser);
                if (follows.size() > 0) {
                    result.setStatus(1);
                } else {
                    result.setMessage("你未关注此用户");
                }
            }
        }
        return result;
    }


    public boolean isNotLogin(User user) {
        Token token = tokenRepository.findTop1ByUserOrderByCreatetimeDesc(user);
        if (token != null && token.getExpiretime() > new Date().getTime() && token.getOuttime() == null) return false;
        return true;
    }
}
