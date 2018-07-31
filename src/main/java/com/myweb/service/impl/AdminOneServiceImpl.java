package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.*;
import com.myweb.service.AdminOneService;
import com.myweb.vo.AdminOneParameter;
import com.myweb.vo.FourParameter;
import com.myweb.vo.OneParameter;
import com.myweb.vo.TwoParameter;
import com.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private User2Repository user2Repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private AdminMenuRepository adminMenuRepository;

    @Autowired
    private AdminPrivilegeRepository adminPrivilegeRepository;

    @Autowired
    private AdminRoleRepository adminRoleRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private SettingRepository settingRepository;

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
    public Result userMe(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(httpSession.getAttribute("user"));
        return result;
    }

    @Override
    public Result userAdminList(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(adminUserRepository.findAll());
        return result;
    }

    @Override
    public Result userAdmin(FourParameter fourParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(adminUserRepository.findOne(fourParameter.getUserid()));
        return result;
    }

    @Override
    public Result postUserAdmin(FourParameter fourParameter, HttpSession httpSession) {
        Result result = new Result();
        if (fourParameter.getType() == 1) {
            if (fourParameter.getUserid() == null || fourParameter.getUserid() == 0) {
                AdminUser adminUser = new AdminUser();
                adminUser.setUsername(fourParameter.getUsername());
                adminUser.setPassword(fourParameter.getPassword());
                adminUser.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                adminUser.setAdminRole(adminRoleRepository.findOne(fourParameter.getRoleid()));
                adminUserRepository.save(adminUser);
                createLog("新建后台用户:" + adminUser.getUsername(), httpSession);
                result.setStatus(1);
            } else {
                AdminUser adminUser = adminUserRepository.findOne(fourParameter.getUserid());
                adminUser.setUsername(fourParameter.getUsername());
                adminUser.setPassword(fourParameter.getPassword());
                adminUser.setAdminRole(adminRoleRepository.findOne(fourParameter.getRoleid()));
                adminUserRepository.save(adminUser);
                createLog("修改后台用户:" + adminUser.getUsername(), httpSession);
                result.setStatus(1);
            }
        } else if (fourParameter.getType() == 2) {
            AdminUser adminUser = adminUserRepository.findOne(fourParameter.getUserid());
            adminUserRepository.delete(adminUser);
            createLog("删除后台用户:" + adminUser.getUsername(), httpSession);
            result.setStatus(1);
        }
        return result;
    }


    @Override
    public Result postUserRole(FourParameter fourParameter, HttpSession httpSession) {
        Result result = new Result();
        if (fourParameter.getType() == 1) {
            if (fourParameter.getRoleid() == null || fourParameter.getRoleid() == 0) {
                AdminRole adminRole = new AdminRole();
                adminRole.setName(fourParameter.getName());
                adminRole.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                adminRoleRepository.save(adminRole);
                createLog("新建后台角色:" + adminRole.getName(), httpSession);
                result.setStatus(1);
            } else {
                AdminRole adminRole = adminRoleRepository.findOne(fourParameter.getRoleid());
                adminRole.setName(fourParameter.getName());
                adminRoleRepository.save(adminRole);
                createLog("修改后台角色:" + adminRole.getName(), httpSession);
                result.setStatus(1);
            }
        } else if (fourParameter.getType() == 2) {
            AdminRole adminRole = adminRoleRepository.findOne(fourParameter.getRoleid());
            adminRoleRepository.delete(adminRole);
            createLog("删除后台角色:" + adminRole.getName(), httpSession);
            result.setStatus(1);
        }
        return result;
    }


    @Override
    public Result postUserPrivilege(FourParameter fourParameter, HttpSession httpSession) {
        Result result = new Result();
        String[] ids = fourParameter.getMenuids().split(",");
        AdminRole adminRole = adminRoleRepository.findOne(fourParameter.getRoleid());
        adminRole.getAdminPrivileges().forEach(e -> {
            adminPrivilegeRepository.delete(e);
        });
        for (String id : ids) {
            AdminPrivilege adminPrivilege = new AdminPrivilege();
            adminPrivilege.setAdminRole(adminRole);
            adminPrivilege.setAdminMenu(adminMenuRepository.findOne(Integer.parseInt(id)));
            adminPrivilege.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
            adminPrivilegeRepository.save(adminPrivilege);
        }
        createLog("修改后台权限，角色:" + adminRole.getName(), httpSession);
        return result;
    }

    @Override
    public Result userRoleList(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(adminRoleRepository.findAll());
        return result;
    }

    @Override
    public Result userPrivilege(FourParameter fourParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        AdminRole adminRole = adminRoleRepository.findOne(fourParameter.getRoleid());
        result.setData(adminPrivilegeRepository.findAllByAdminRole(adminRole));
        return result;
    }

    @Override
    public Result userRole(FourParameter fourParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(adminRoleRepository.findOne(fourParameter.getRoleid()));
        return result;
    }

    @Override
    public Result userMenuList(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(adminMenuRepository.findAll());
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
    public Result userList(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(user2Repository.findAllByIdNot(1));
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
            createLog("删除ID为" + oneParameter.getUserid() + "的用户", httpSession);
            userRepository.delete(oneParameter.getUserid());
        } else {
            User user = userRepository.findOne(oneParameter.getUserid());
            if (StringUtils.isNotBlank(oneParameter.getEmail())) {
                createLog("修改ID为" + user.getId() + "的用户的邮箱", httpSession);
                user.setEmail(oneParameter.getEmail());
            }
            result.setData(userRepository.save(user));
        }
        return result;
    }

    @Override
    public Result studyCount(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(helpRepository.sumStudied());
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
        if (twoParameter.getHelpid() == null || twoParameter.getHelpid() == 0) return result;
        result.setStatus(1);
        Help help = helpRepository.findOne(twoParameter.getHelpid());
        result.setData(help);
        return result;
    }

    @Override
    public Result advert(TwoParameter twoParameter, HttpSession httpSession) {
        Result result = new Result();
        if (twoParameter.getAdvertid() == null || twoParameter.getAdvertid() == 0) return result;
        result.setStatus(1);
        result.setData(advertRepository.findOne(twoParameter.getAdvertid()));
        return result;
    }

    @Override
    public Result helpList(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(helpRepository.findAll());
        return result;
    }

    @Override
    public Result advertList(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        List<Advert> adverts = advertRepository.findAll();
        adverts.forEach(e->{
            e.setRate((float)e.getClicked()/e.getExposure());
        });
        result.setData(adverts);
        return result;
    }

    @Override
    public Result postHelp(TwoParameter twoParameter, HttpSession httpSession) {
        Result result = new Result();
        if (twoParameter.getHelpid() == null || twoParameter.getHelpid() == 0) return result;
        Help help = helpRepository.findOne(twoParameter.getHelpid());
        if (help == null) return result;
        result.setStatus(1);
        if (twoParameter.getType() != null && twoParameter.getType() == 2) {
            createLog("删除ID为" + twoParameter.getHelpid() + "的求助", httpSession);
            helpRepository.delete(help);
            studyRepository.deleteAllByHelp(help);
        } else if (twoParameter.getType() != null && twoParameter.getType() == 1 && twoParameter.getDraft() != null && twoParameter.getDraft() != 0) {
            createLog("审核ID为" + twoParameter.getHelpid() + "的求助", httpSession);
            help.setDraft(twoParameter.getDraft());
            helpRepository.save(help);
        }
        return result;
    }

    @Override
    public Result helpDraft(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(helpRepository.findByDraft(2));
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
        createLog("设置ID为" + help.getId() + "的求助为强制推荐", httpSession);
        return result;
    }

    @Override
    public Result advertRefer(TwoParameter twoParameter, HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        if (twoParameter.getAdvertid() == null || twoParameter.getAdvertid() == 0) return result;
        Advert advert = advertRepository.findOne(twoParameter.getAdvertid());
        if (advert == null) return result;
        advert.setRefer(1);
        advertRepository.save(advert);
        createLog("设置ID为" + advert.getId() + "的广告为强制推荐", httpSession);
        return result;
    }

    @Override
    public Result setting(AdminOneParameter adminOneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (adminOneParameter.getOperation() == 1) {
            if (adminOneParameter.getSettingid() == null || adminOneParameter.getSettingid() == 0) {
                Setting setting = new Setting();
                setting.setName(adminOneParameter.getName());
                setting.setContent(adminOneParameter.getContent());
                setting.setType(adminOneParameter.getType());
                setting.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                createLog("增加网站设置", httpSession);
                result.setStatus(1);
                result.setData(settingRepository.save(setting));
            } else {
                Setting setting = settingRepository.findOne(adminOneParameter.getSettingid());
                result.setStatus(1);
                createLog("更改网站设置", httpSession);
                setting.setName(adminOneParameter.getName());
                setting.setContent(adminOneParameter.getContent());
                setting.setType(adminOneParameter.getType());
                result.setData(settingRepository.save(setting));
                result.setStatus(1);
            }
        } else if (adminOneParameter.getOperation() == 2) {
            settingRepository.delete(adminOneParameter.getSettingid());
            result.setStatus(1);
        }
        return result;
    }

    @Override
    public Result getSetting(AdminOneParameter adminOneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (adminOneParameter.getSettingid() == null || adminOneParameter.getSettingid() == 0) return result;
        result.setStatus(1);
        result.setData(settingRepository.findOne(adminOneParameter.getSettingid()));
        return result;
    }


    @Override
    public Result settingList(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(settingRepository.findAll());
        return result;
    }

    @Override
    public Result postAdvert(AdminOneParameter adminOneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (adminOneParameter.getOperation() == null || adminOneParameter.getOperation() == 0) return result;
        Advert advert = new Advert();
        if (adminOneParameter.getOperation() == 1) {
            AdminUser adminUser = (AdminUser) httpSession.getAttribute("user");
            advert.setAdminuser(adminUserRepository.findOne(adminUser.getId()));
            advert.setTitle(adminOneParameter.getTitle());
            advert.setImage(adminOneParameter.getImage());
            advert.setUrl(adminOneParameter.getUrl());
            advert.setType(adminOneParameter.getType());
            advert.setExposure(0);
            advert.setClicked(0);
            advert.setBuy(0);
            advert.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
            try{
            advert.setOuttime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(adminOneParameter.getOuttime())));
            }catch (Exception e){
                e.printStackTrace();
            }
            createLog("上传广告", httpSession);
            result.setStatus(1);
            result.setData(advertRepository.save(advert));
        } else if (adminOneParameter.getOperation() == 2) {
            advert = advertRepository.findOne(adminOneParameter.getAdvertid());
            if (advert == null) return result;
            result.setStatus(1);
            createLog("更改广告", httpSession);
            advert.setTitle(adminOneParameter.getTitle());
            advert.setImage(adminOneParameter.getImage());
            advert.setUrl(adminOneParameter.getUrl());
            advert.setType(adminOneParameter.getType());
            advert.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
            result.setData(advertRepository.save(advert));
        } else if (adminOneParameter.getOperation() == 3) {
            advert = advertRepository.findOne(adminOneParameter.getAdvertid());
            if (advert == null) return result;
            advertRepository.delete(advert);
            result.setStatus(1);
        }
        return result;
    }

    @Override
    public Result countFollow(OneParameter oneParameter, HttpSession httpSession) {
        Result result = new Result();
        if (oneParameter.getUserid() == null && oneParameter.getUserid() == 0) return result;
        User user = userRepository.findOne(oneParameter.getUserid());
        if (user == null) return result;
        createLog("查询ID为" + user.getId() + "的用户的被关注总数", httpSession);
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
        createLog("查询ID为" + user.getId() + "的用户的被详情", httpSession);
        result.setStatus(1);
        result.setData(followRepository.findByTouser(user, pageable));
        return result;
    }

    @Override
    public Result showlog(HttpSession httpSession) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(adminLogRepository.findAll(new Sort(Sort.Direction.DESC, "createtime")));
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
