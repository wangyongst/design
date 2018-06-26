package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.AdminLog;
import com.myweb.pojo.AdminUser;
import com.myweb.pojo.Help;
import com.myweb.service.AdminOneService;
import com.myweb.vo.OneParameter;
import com.myweb.vo.ThreeParameter;
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

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("AdminOneService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
public class AdminOneServiceImpl implements AdminOneService {

    private static final Logger logger = LogManager.getLogger(AdminOneServiceImpl.class);

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminLogRepository adminLogRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private ClickRepository clickRepository;

    @Autowired
    private HelpRepository helpRepository;

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
    public Result logout(OneParameter oneParameter, HttpSession httpSession) {
        Result result = new Result();
        createLog("登出", httpSession);
        httpSession.removeAttribute("user");
        result.setStatus(1);
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
    public Result studyCountHelp(ThreeParameter threeParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        if (threeParameter.getHelpid() == null || threeParameter.getHelpid() == 0) return result;
        Help help = helpRepository.findOne(threeParameter.getHelpid());
        if (help == null) return result;
        result.setData(studyRepository.countAllByHelp(help));
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
    public Result clickCountHelp(ThreeParameter threeParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        if (threeParameter.getHelpid() == null || threeParameter.getHelpid() == 0) return result;
        Help help = helpRepository.findOne(threeParameter.getHelpid());
        if (help == null) return result;
        result.setData(clickRepository.countAllByHelp(help));
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result helpRefer(ThreeParameter threeParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        if (threeParameter.getHelpid() == null || threeParameter.getHelpid() == 0) return result;
        Help help = helpRepository.findOne(threeParameter.getHelpid());
        if (help == null) return result;
        help.setRefertime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        helpRepository.save(help);
        createLog("设置id为" + help.getId() + "的求助为强制推荐", httpSession);
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

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public void createLog(String message, HttpSession httpSession) {
        AdminLog adminLog = new AdminLog();
        adminLog.setAdminUser(((AdminUser) httpSession.getAttribute("user")));
        adminLog.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        adminLog.setMessage(message);
        adminLogRepository.save(adminLog);
    }
}
