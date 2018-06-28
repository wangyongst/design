package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.*;
import com.myweb.service.AdminOneService;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.OneParameter;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("AdminOneService")
@SuppressWarnings("All")
public class AdminOneServiceImpl implements AdminOneService {

    private static final Logger logger = LogManager.getLogger(AdminOneServiceImpl.class);

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminLogRepository adminLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private ClickRepository clickRepository;

    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public Result login(OneParameter oneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (StringUtils.isNotBlank(oneParameter.getUsername()) || StringUtils.isNotBlank(oneParameter.getPassword())) {
            List<AdminUser> userList = adminUserRepository.findByUsernameAndPassword(oneParameter.getUsername(), oneParameter.getPassword());
            if (userList.size() == 1) {
                httpSession.setAttribute("user", userList.get(0));
                createLog("登录", httpSession);
                result.setStatus(1);
                result.setData(userList.get(0));
                return result;
            } else if (userList.size() != 1) {
                result.setMessage("用户不存在或密码错误！");
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
    public Result logout(HttpSession httpSession) {
        Result result = new Result();
        createLog("登出", httpSession);
        httpSession.removeAttribute("user");
        result.setStatus(1);
        return result;
    }

    @Override
    public Result userList(OneParameter oneParameter, Pageable pageable, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        User user = new User();
        if (oneParameter.getUserid() != null && oneParameter.getUserid() != 0) user.setId(oneParameter.getUserid());
        if (StringUtils.isNotBlank(oneParameter.getUsername())) user.setUsername(oneParameter.getUsername());
        if (StringUtils.isNotBlank(oneParameter.getNickname())) user.setNickname(oneParameter.getNickname());
        if (StringUtils.isNotBlank(oneParameter.getEmail())) user.setEmail(oneParameter.getEmail());
        if (StringUtils.isNotBlank(oneParameter.getMobile())) user.setMobile(oneParameter.getMobile());
        if (StringUtils.isNotBlank(oneParameter.getJobs())) user.setJobs(oneParameter.getJobs());
        if (StringUtils.isNotBlank(oneParameter.getSex())) user.setSex(oneParameter.getSex());
        if (oneParameter.getRefer() != null && oneParameter.getRefer() != 0) user.setRefer(oneParameter.getRefer());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("nickname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id").withIgnoreNullValues();
        if (user.getId() == null || user.getId() == 0) result.setData(userRepository.findAll(Example.of(user, matcher), pageable));
        else result.setData(userRepository.findOne(user.getId()));
        return result;
    }

    @Override
    public Result countToken(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(tokenRepository.countAllByExpiretimeGreaterThanAndOuttimeIsNull(new Date().getTime()));
        return result;
    }

    @Override
    public Result userToken(Pageable pageable, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(tokenRepository.findAll(pageable));
        return result;
    }

    @Override
    public Result user(OneParameter oneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (oneParameter.getUserid() == null || oneParameter.getUserid() == 0) return result;
        result.setStatus(1);
        if (oneParameter.getType() != null && oneParameter.getType() == 2) {
            userRepository.delete(oneParameter.getUserid());
        } else {
            User user = userRepository.findOne(oneParameter.getUserid());
            if (StringUtils.isNotBlank(oneParameter.getEmail())) user.setEmail(oneParameter.getEmail());
            result.setData(userRepository.save(user));
        }
        return result;
    }

    @Override
    public Result studyCount(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(studyRepository.count());
        return result;
    }

    @Override
    public Result clickCount(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(clickRepository.count());
        return result;
    }

    @Override
    public Result countUser(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(userRepository.count());
        return result;
    }

    @Override
    public Result countMessage(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(messageRepository.count());
        return result;
    }

    @Override
    public Result countMessageUser(OneParameter oneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (oneParameter.getType() == null && oneParameter.getType() == 0) return result;
        if (oneParameter.getUserid() == null && oneParameter.getUserid() == 0) return result;
        User user = userRepository.findOne(oneParameter.getUserid());
        if (oneParameter.getType() == 1) {
            result.setStatus(1);
            result.setData(messageRepository.countAllByUser(user));
        } else if (oneParameter.getType() == 2) {
            result.setStatus(1);
            result.setData(messageRepository.countAllByTouser(user));
        } else if (oneParameter.getType() == 3 && oneParameter.getTouserid() != null && oneParameter.getTouserid() != 0) {
            User touser = userRepository.findOne(oneParameter.getTouserid());
            result.setStatus(1);
            result.setData(messageRepository.countAllByUserAndTouser(user, touser));
        }
        return result;
    }

    @Override
    public Result help(TwoParameter twoParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        if (twoParameter.getHelpid() == null || twoParameter.getHelpid() == 0) return result;
        Help help = helpRepository.findOne(twoParameter.getHelpid());
        result.setData(help);
        return result;
    }

    @Override
    public Result postHelp(TwoParameter twoParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        if (twoParameter.getHelpid() == null || twoParameter.getHelpid() == 0) return result;
        Help help = helpRepository.findOne(twoParameter.getHelpid());
        if (help == null) return result;
        if (twoParameter.getType() != null && twoParameter.getType() == 2) {
            helpRepository.delete(help);
        }
        return result;
    }

    @Override
    public Result clickMost(Pageable pageable, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(helpRepository.findAll(pageable));
        return result;
    }

    @Override
    public Result helpRefer(TwoParameter twoParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        if (twoParameter.getHelpid() == null || twoParameter.getHelpid() == 0) return result;
        Help help = helpRepository.findOne(twoParameter.getHelpid());
        if (help == null) return result;
        help.setRefertime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        helpRepository.save(help);
        createLog("设置id为" + help.getId() + "的求助为强制推荐", httpSession);
        return result;
    }

    @Override
    public Result setting(AdminOneParameter adminOneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (adminOneParameter.getType() == null || adminOneParameter.getType() == 0 || adminOneParameter.getOperation() == null || adminOneParameter.getOperation() == 0) return result;
        Setting setting = new Setting();
        if (adminOneParameter.getOperation() == 1) {
            if (StringUtils.isNotBlank(adminOneParameter.getName())) setting.setName(adminOneParameter.getName());
            if (StringUtils.isNotBlank(adminOneParameter.getContent())) setting.setContent(adminOneParameter.getContent());
            result.setData(settingRepository.save(setting));
        } else if (adminOneParameter.getOperation() == 2 && adminOneParameter.getSettingid() != null || adminOneParameter.getSettingid() != 0) {
            setting = settingRepository.findOne(adminOneParameter.getSettingid());
            if (setting == null) return result;
            result.setStatus(1);
            if (StringUtils.isNotBlank(adminOneParameter.getName())) setting.setName(adminOneParameter.getName());
            if (StringUtils.isNotBlank(adminOneParameter.getContent())) setting.setContent(adminOneParameter.getContent());
            result.setData(settingRepository.save(setting));
        }
        return result;
    }

    @Override
    public Result getSetting(AdminOneParameter adminOneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (adminOneParameter.getType() == null || adminOneParameter.getType() == 0) return result;
        result.setStatus(1);
        result.setData(settingRepository.findAllByType(adminOneParameter.getType()));
        return result;
    }

    @Override
    public Result countFollow(OneParameter oneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (oneParameter.getUserid() == null && oneParameter.getUserid() == 0) return result;
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null) return result;
        result.setStatus(1);
        result.setData(followRepository.countAllByTouser(user));
        return result;
    }

    @Override
    public Result follow(OneParameter oneParameter, Pageable pageable, HttpSession httpSession) {
        Result result = new Result();
        if (oneParameter.getUserid() == null && oneParameter.getUserid() == 0) return result;
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null) return result;
        result.setStatus(1);
        result.setData(followRepository.findByTouser(user, pageable));
        return result;
    }

    @Override
    public Result showlog(Pageable pageable, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(adminLogRepository.findAll(pageable));
        createLog("查看操作日志", httpSession);
        return result;
    }

    public void createLog(String message, HttpSession httpSession) {
        AdminLog adminLog = new AdminLog();
        AdminUser adminUser = (AdminUser) httpSession.getAttribute("user");
        adminLog.setAdminUser(adminUserRepository.findOne(adminUser.getId()));
        adminLog.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        adminLog.setMessage(message);
        adminLogRepository.saveAndFlush(adminLog);
    }
}
