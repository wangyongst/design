package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.*;
import com.myweb.service.ThreeService;
import com.myweb.vo.ThreeParameter;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("ThreeService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
public class ThreeServiceImpl implements ThreeService {

    private static final Logger logger = LogManager.getLogger(ThreeServiceImpl.class);

    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result click(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getHelpid() == null || threeParameter.getHelpid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Help help = helpRepository.findOne(threeParameter.getHelpid());
        if (help == null) {
            result.setMessage("点击的求助不存在");
        } else {
            help.setClicked(help.getClicked() + 1);
            helpRepository.save(help);
            result.setStatus(1);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result study(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getHelpid() == null || threeParameter.getHelpid() == 0 || threeParameter.getUserid() == null || threeParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(threeParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
            return result;
        }
        Help help = helpRepository.findOne(threeParameter.getHelpid());
        if (help == null) {
            result.setMessage("想要学习的求助不存在");
        } else {
            if (threeParameter.getType() == null || threeParameter.getType() == 0) {
                Study study = new Study();
                study.setUser(user);
                study.setHelp(help);
                study.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                studyRepository.save(study);
                help.setStudied(help.getStudied() + 1);
                helpRepository.save(help);
                result.setStatus(1);
                createNotice(help.getUser(), user, help, "想学", 1);
            } else {
                studyRepository.deleteAllByHelpAndUser(help, user);
                if (help.getStudied() > 1) {
                    help.setStudied(help.getStudied() - 1);
                    helpRepository.save(help);
                }
                result.setStatus(1);
            }
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result send(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getUserid() == null || threeParameter.getUserid() == 0 || threeParameter.getTouserid() == null || threeParameter.getTouserid() == 0 || StringUtils.isBlank(threeParameter.getMessage())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Message message = new Message();
        User user = userRepository.findOne(threeParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            User touser = userRepository.findOne(threeParameter.getTouserid());
            if (touser == null) {
                result.setMessage("发送用户不存在!");
            } else {
                message.setUser(user);
                message.setTouser(touser);
                message.setMessage(threeParameter.getMessage());
                message.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                message.setIsread(0);
                messageRepository.save(message);
                result.setStatus(1);
            }
        }
        return result;
    }

    @Override
    public Result user(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getUserid() == null || threeParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(threeParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            result.setStatus(1);
            List<User> userList = messageRepository.findUserByUserOrTouser(user);
            userList.addAll(messageRepository.findTouserByUserOrTouser(user));
            userList.remove(user);
            result.setData(userList);
        }
        return result;
    }

    @Override
    public Result userMost(ThreeParameter threeParameter, Pageable pageable) {
        Result result = new Result();
        Page<User> users = helpRepository.findUserByMost(pageable);
        users.forEach(e -> {
            e.setPublished(helpRepository.countAllByUser(e));
            e.setFollowed(followRepository.countAllByUser(e));
            e.setFans(followRepository.countAllByTouser(e));
            if (threeParameter.getUserid() != null || threeParameter.getUserid() != 0) {
                User u = userRepository.findOne(threeParameter.getUserid());
                if (u == null || isNotLogin(u)) {
                    result.setStatus(9);
                    result.setMessage("当前用户不存在或未登录!");
                } else {
                    List<Follow> follows2 = followRepository.findByUserAndTouser(u, e);
                    if (follows2.size() > 0) {
                        e.setIsFollow(1);
                    } else {
                        e.setIsFollow(0);
                    }
                }
            }
        });
        if (result.getStatus() == 9) return result;
        result.setStatus(1);
        result.setData(users);
        return result;
    }

    @Override
    public Result message(ThreeParameter threeParameter, Pageable pageable) {
        Result result = new Result();
        if (threeParameter.getUserid() == null || threeParameter.getUserid() == 0 || threeParameter.getTouserid() == null || threeParameter.getTouserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(threeParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            User touser = userRepository.findOne(threeParameter.getTouserid());
            if (touser == null) {
                result.setMessage("发送用户不存在!");
            } else {
                result.setStatus(1);
                result.setData(messageRepository.findByUserOrTouser(user, touser, pageable));
            }
        }
        return result;
    }

    @Override
    public Result noticeNew(ThreeParameter threeParameter, Pageable pageable) {
        Result result = new Result();
        if (threeParameter.getUserid() == null || threeParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(threeParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            result.setStatus(1);
            result.setData(noticeRepository.findAllByIsreadNot(1, pageable));
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result read(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getUserid() == null || threeParameter.getUserid() == 0 || threeParameter.getTouserid() == null || threeParameter.getTouserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(threeParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            User touser = userRepository.findOne(threeParameter.getTouserid());
            if (touser == null) {
                result.setMessage("发送用户不存在!");
            } else {
                List<Message> messageList = messageRepository.findByUserOrTouser(user, touser);
                messageList.forEach(e -> {
                    e.setIsread(1);
                    messageRepository.save(e);
                });
                result.setStatus(1);
            }
        }
        return result;
    }

    public boolean isNotLogin(User user) {
        Token token = tokenRepository.findTop1ByUserOrderByCreatetimeDesc(user);
        if (token != null && token.getExpiretime() > new Date().getTime() && token.getOuttime() == null) return false;
        return true;
    }

    public void createNotice(User user, User fromuser, Help help, String message, Integer type) {
        Notice notice = new Notice();
        notice.setUser(user);
        notice.setFromuser(fromuser);
        notice.setHelp(help);
        notice.setType(type);
        notice.setIsread(0);
        notice.setMessage(message);
        notice.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        noticeRepository.save(notice);
    }

    public void createSysNotice(User user, Help help, String message, Integer type) {
        Notice notice = new Notice();
        notice.setUser(user);
        notice.setFromuser(userRepository.findOne(1));
        notice.setHelp(help);
        notice.setType(type);
        notice.setIsread(0);
        notice.setMessage(message);
        notice.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        noticeRepository.save(notice);
        Message me = new Message();
        me.setIsread(0);
        me.setMessage(message);
        me.setTouser(user);
        me.setUser(userRepository.findOne(1));
        me.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        messageRepository.save(me);
    }
}
