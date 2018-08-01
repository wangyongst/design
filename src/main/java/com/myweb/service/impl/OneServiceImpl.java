package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.*;
import com.myweb.service.OneService;
import com.myweb.utils.QiniuUtil;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.utils.Result;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
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
    private SearchingRepository searchingRepository;

    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private StudyRepository studyRepository;

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
        if (userRepository.findByEmail(oneParameter.getEmail()).size() > 0) {
            result.setMessage("邮箱已经已经被注册!");
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
                createSysNotice(refer, null, "恭喜你，成功邀请好友注册，获得平台永久发布功能。", 5, 2);
            }
        }
        user.setUsername(oneParameter.getUsername());
        user.setPassword(oneParameter.getPassword());
        user.setEmail(oneParameter.getEmail());
        user.setAvatar("http://pas99p7vd.bkt.clouddn.com/eM1jGVzQ");
        user.setNickname(oneParameter.getNickname());
        user.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        userRepository.save(user);
        createSysNotice(user, null, "邀请 1 个好友成功注册，你就可以获得平台免费永久发布想学，立即邀请", 5, 3);
        createSysNotice(user, null, "恭喜你，成功注册热点设计，你已获得 1 次免费使用发布功能，邀请一个好友注册成功，获得平台永久发布效果功能", 5, 4);
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
            result.setStatus(9);
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
    public Result destroy(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || StringUtils.isBlank(oneParameter.getPassword()) || StringUtils.isBlank(oneParameter.getText())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            if (!user.getPassword().equals(oneParameter.getPassword())) {
                result.setMessage("密码不正确!");
            } else {
                helpRepository.removeAllByUser(user);
                studyRepository.removeAllByUser(user);
                followRepository.removeAllByUserOrTouser(user, user);
                messageRepository.removeAllByUserOrTouser(user, user);
                noticeRepository.removeAllByUser(user);
                searchingRepository.removeAllByUser(user);
                tokenRepository.removeAllByUser(user);
                userRepository.delete(user);
                result.setStatus(1);
            }
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
            result.setStatus(9);
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
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
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
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else if (StringUtils.equals(user.getPassword(), oneParameter.getPassword())) {
            user.setPassword(oneParameter.getPassword2());
            userRepository.save(user);
            result.setStatus(1);
            result.setData(user);
        } else {
            result.setMessage("旧密码不正确！");
        }
        createSysNotice(user, null, "您的登录密码重置成功！\n为了保障您的账户安全，请保管好并定期更改登录及支付密码。", 5, 5);
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
        if (userRepository.findByEmail(oneParameter.getEmail()).size() > 0) {
            result.setMessage("邮箱已经已经被注册!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
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
            result.setStatus(9);
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
                createNotice(touser, user, null, "关注", 2);
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
            result.setStatus(9);
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
        if (user == null) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            Page<Follow> follows = null;
            if (oneParameter.getTouserid() == null || oneParameter.getTouserid() == 0) {
                follows = followRepository.findByUser(user, pageable);
            } else {
                User user1 = userRepository.findOne(oneParameter.getTouserid());
                follows = followRepository.findByUser(user1, pageable);
            }
            follows.forEach(e -> {
                List<Follow> follows2 = followRepository.findByUserAndTouser(user, e.getUser());
                if (follows2.size() > 0) {
                    e.setIsFollow(1);
                } else {
                    e.setIsFollow(0);
                }
            });
            result.setStatus(1);
            result.setData(follows);
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
        if (user == null) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            Page<Follow> follows = null;
            if (oneParameter.getTouserid() == null || oneParameter.getTouserid() == 0) {
                follows = followRepository.findByTouser(user, pageable);
            } else {
                User user1 = userRepository.findOne(oneParameter.getTouserid());
                follows = followRepository.findByTouser(user1, pageable);
            }
            follows.forEach(e -> {
                List<Follow> follows2 = followRepository.findByUserAndTouser(user, e.getUser());
                if (follows2.size() > 0) {
                    e.setIsFollow(1);
                } else {
                    e.setIsFollow(0);
                }
            });
            result.setStatus(1);
            result.setData(follows);
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result search(OneParameter oneParameter, Pageable pageable) {
        Result result = new Result();
        if (oneParameter.getKeyword() == null) {
            Page<User> users = userRepository.findAllByIdNot(1, pageable);
            if (oneParameter.getUserid() != null && oneParameter.getUserid() != 0) {
                User user = userRepository.findOne(oneParameter.getUserid());
                if (user == null || isNotLogin(user)) {
                    result.setStatus(9);
                    result.setMessage("当前用户不存在或未登录!");
                    return result;
                }
                users.forEach(e -> {
                    List<Follow> follows2 = followRepository.findByUserAndTouser(user, e);
                    if (follows2.size() > 0) {
                        e.setIsFollow(1);
                    } else {
                        e.setIsFollow(0);
                    }
                });
            }
            result.setStatus(1);
            result.setData(users);
        } else {
            Page<User> users = userRepository.findAllByUsernameContainsOrNicknameContainsOrSexContainsOrJobsContains(oneParameter.getKeyword(), oneParameter.getKeyword(), oneParameter.getKeyword(), oneParameter.getKeyword(), pageable);
            if (oneParameter.getUserid() != null && oneParameter.getUserid() != 0) {
                User user = userRepository.findOne(oneParameter.getUserid());
                if (user == null || isNotLogin(user)) {
                    result.setStatus(9);
                    result.setMessage("当前用户不存在或未登录!");
                    return result;
                }
                users.forEach(e -> {
                    List<Follow> follows2 = followRepository.findByUserAndTouser(user, e);
                    if (follows2.size() > 0) {
                        e.setIsFollow(1);
                    } else {
                        e.setIsFollow(0);
                    }
                });
                Searching searching = new Searching();
                searching.setKeyword(oneParameter.getKeyword());
                searching.setUser(user);
                searching.setIsclear(0);
                searching.setType(2);
                searching.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                searchingRepository.save(searching);
                result.setStatus(1);
                result.setData(users);
            }
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
            result.setStatus(9);
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
            result.setStatus(9);
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

    @Override
    public Result studiedIs(OneParameter oneParameter) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0 || oneParameter.getHelpid() == null || oneParameter.getHelpid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            Help help = helpRepository.findOne(oneParameter.getHelpid());
            if (help == null) {
                result.setMessage("要检查的求助不存在!");
            } else {
                List<Study> studies = studyRepository.findAllByUserAndHelp(user, help);
                if (studies.size() > 0) {
                    result.setStatus(1);
                } else {
                    result.setMessage("你未想学此求助");
                }
            }
        }
        return result;
    }

    @Override
    public Result uploadImage(MultipartFile multipartFile) {
        Result result = new Result();
        if (multipartFile == null || multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        //处理图片
        try {
            FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
            return QiniuUtil.upload(fileInputStream, RandomStringUtils.randomAlphanumeric(8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isNotLogin(User user) {
        Token token = tokenRepository.findTop1ByUserOrderByCreatetimeDesc(user);
        if (token != null && token.getExpiretime() > new Date().getTime() && token.getOuttime() == null) return false;
        return true;
    }

    public void createNotice(User user, User fromuser, Help help, String message, Integer type) {
        if (user.getId() == fromuser.getId()) return;
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

    public void createSysNotice(User user, Help help, String message, Integer type, Integer mtype) {
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
        if (help != null) me.setHelp(help);
        me.setType(mtype);
        me.setMessage(message);
        me.setTouser(user);
        me.setUser(userRepository.findOne(1));
        me.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        messageRepository.save(me);
    }
}
