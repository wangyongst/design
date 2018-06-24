package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.*;
import com.myweb.service.ThreeService;
import com.myweb.service.TwoService;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ThreeParameter;
import com.myweb.vo.TwoParameter;
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
    private ClickRepository clickRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result click(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getHelpid() == null || threeParameter.getHelpid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Click click = new Click();
        if (threeParameter.getUserid() != null && threeParameter.getUserid() != 0) {
            User user = userRepository.findOne(threeParameter.getUserid());
            click.setUserid(user.getId());
        }
        click.setHelpid(threeParameter.getHelpid());
        click.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        clickRepository.save(click);
        Help help = helpRepository.findOne(click.getHelpid());
        help.setClicked(help.getClicked() + 1);
        helpRepository.save(help);
        result.setStatus(1);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result study(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getHelpid() == null || threeParameter.getHelpid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        Study study = new Study();
        if (threeParameter.getUserid() != null && threeParameter.getUserid() != 0) {
            User user = userRepository.findOne(threeParameter.getUserid());
            study.setUserid(user.getId());
        }
        study.setHelpid(threeParameter.getHelpid());
        study.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        studyRepository.save(study);
        Help help = helpRepository.findOne(study.getHelpid());
        help.setStudied(help.getStudied() + 1);
        helpRepository.save(help);
        result.setStatus(1);
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
        if (user == null) {
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
                message.setRead(0);
                messageRepository.save(message);
                result.setStatus(1);
                result.setData(user);
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
        if (user == null) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            result.setStatus(1);
            List<User> userList = messageRepository.findUserByUserOrTouser(user);
            userList.addAll(messageRepository.findTouserByUserOrTouser(user));
            result.setData(userList);
        }
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
        if (user == null) {
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result read(ThreeParameter threeParameter) {
        Result result = new Result();
        if (threeParameter.getUserid() == null || threeParameter.getUserid() == 0 || threeParameter.getTouserid() == null || threeParameter.getTouserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(threeParameter.getUserid());
        if (user == null) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            User touser = userRepository.findOne(threeParameter.getTouserid());
            if (touser == null) {
                result.setMessage("发送用户不存在!");
            } else {
                List<Message> messageList = messageRepository.findByUserOrTouser(user, touser);
                messageList.forEach(e -> {
                    e.setRead(1);
                    messageRepository.save(e);
                });
                result.setStatus(1);
                result.setData(messageList);
            }
        }
        return result;
    }
}
