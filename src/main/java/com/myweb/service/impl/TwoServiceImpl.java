package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.AdvertRepository;
import com.myweb.dao.jpa.hibernate.HelpRepository;
import com.myweb.dao.jpa.hibernate.TokenRepository;
import com.myweb.dao.jpa.hibernate.UserRepository;
import com.myweb.pojo.Help;
import com.myweb.pojo.Token;
import com.myweb.pojo.User;
import com.myweb.service.TwoService;
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


@Service("TwoService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
public class TwoServiceImpl implements TwoService {

    private static final Logger logger = LogManager.getLogger(TwoServiceImpl.class);

    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertRepository advertRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result seek(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0 || twoParameter.getAudience() == null || twoParameter.getAudience() == 0 || twoParameter.getDraft() == null || twoParameter.getDraft() == 0 || StringUtils.isBlank(twoParameter.getImage()) || StringUtils.isBlank(twoParameter.getTag()) || StringUtils.isBlank(twoParameter.getDesign())) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(twoParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            Help help = new Help();
            help.setUser(user);
            help.setAudience(twoParameter.getAudience());
            help.setBackground(twoParameter.getBackground());
            help.setTitle(twoParameter.getTitle());
            help.setImage(twoParameter.getImage());
            help.setDescription(twoParameter.getDescription());
            help.setVideo(twoParameter.getVideo());
            help.setTag(twoParameter.getTag());
            help.setDesign(twoParameter.getDesign());
            help.setBackground(twoParameter.getBackground());
            help.setSet(twoParameter.getSet());
            help.setDraft(twoParameter.getDraft());
            help.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
            helpRepository.save(help);
            result.setStatus(1);
        }
        return result;
    }


    @Override
    public Result user(TwoParameter twoParameter, Pageable pageable) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(twoParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            if (twoParameter.getDraft() == null || twoParameter.getDraft() == 0) {
                result.setStatus(1);
                result.setData(helpRepository.findByUser(user, pageable));
            } else {
                result.setStatus(1);
                result.setData(helpRepository.findByUserAndDraft(user, twoParameter.getDraft(), pageable));
            }
        }
        return result;
    }

    @Override
    public Result index(TwoParameter twoParameter, Pageable pageable) {
        Result result = new Result();
        result.setStatus(1);
        if (twoParameter.getDesign() == null) {
            result.setData(helpRepository.findByDraft(4, pageable));
        } else {
            result.setData(helpRepository.findByDesignAndDraft(twoParameter.getDesign(), 4, pageable));
        }
        return result;
    }

    @Override
    public Result advert(Pageable pageable) {
        Result result = new Result();
        result.setStatus(1);
        result.setData(advertRepository.findAllByRefer(1, pageable));
        return result;
    }

    @Override
    public Result search(TwoParameter twoParameter, Pageable pageable) {
        Result result = new Result();
        result.setStatus(1);
        if (twoParameter.getTag() == null) {
            result.setData(helpRepository.findByDraft(4, pageable));
        } else {
            result.setData(helpRepository.findByDraftAndTagContains(4, twoParameter.getDesign(), pageable));
        }
        return result;
    }

    @Override
    public Result mine(TwoParameter twoParameter, Pageable pageable) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(twoParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在!");
        } else {
            if (twoParameter.getTag() == null && twoParameter.getType() == null || twoParameter.getType() == 0) {
                result.setData(helpRepository.findByUserAndDraft(user, 4, pageable));
            } else if (twoParameter.getTag() != null && (twoParameter.getType() == null || twoParameter.getType() == 0)) {
                result.setData(helpRepository.findByUserAndDraftAndTagContains(user, 4, twoParameter.getTag(), pageable));
            } else if (twoParameter.getTag() != null && twoParameter.getType() != null && twoParameter.getType() != 0) {
                //result.setData(helpRepository.findByUserAndTagContains(user, twoParameter.getTag(), pageable));
            } else {
                //result.setData(helpRepository.findByTagContains(twoParameter.getDesign(), pageable));
            }
        }
        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result delete(TwoParameter twoParameter, String helpids) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0 || StringUtils.isBlank(helpids)) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(twoParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setMessage("当前用户不存在或未登录!");
        } else {
            String[] ids = helpids.split(",");
            for (String id : ids) {
                if (StringUtils.isNotBlank(id)) {
                    helpRepository.delete(Integer.parseInt(id));
                }
            }
            result.setStatus(1);
        }
        return result;
    }

    public boolean isNotLogin(User user) {
        Token token = tokenRepository.findTop1ByUserOrderByCreatetimeDesc(user);
        if (token != null && token.getExpiretime() > new Date().getTime() && token.getOuttime() == null) return false;
        return true;
    }
}
