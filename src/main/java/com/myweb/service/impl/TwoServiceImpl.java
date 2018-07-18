package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.pojo.*;
import com.myweb.service.TwoService;
import com.myweb.vo.TwoParameter;
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
import java.util.Random;


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

    @Autowired
    private StudyRepository studyRepository;


    @Autowired
    private SearchingRepository searchingRepository;

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
            if (helpRepository.findByUser(user).size() > 0 && userRepository.findByRefer(user.getId()).size() < 1) {
                result.setStatus(2);
                result.setMessage("未邀请过好友注册，不能发布");
                return result;
            }
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
            help.setSetting(twoParameter.getSetting());
            help.setDraft(twoParameter.getDraft());
            help.setIndexpic(twoParameter.getIndexpic());
            help.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
            help.setStudied(0);
            help.setReaded(0);
            help.setClicked(0);
            help.setFans(0);
            help.setForwarded(0);
            help.setRecommend(new Random().nextInt(700) + 300);
            helpRepository.save(help);
            result.setStatus(1);
            result.setData(help);
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
                result.setData(helpRepository.findByUserAndDraftAndAudienceNot(user, twoParameter.getDraft(), 3, pageable));
            }
        }
        return result;
    }

    @Override
    public Result index(TwoParameter twoParameter, Pageable pageable) {
        Result result = new Result();
        result.setStatus(1);
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            if (twoParameter.getDesign() == null) {
                result.setData(helpRepository.findByDraft(4, pageable));
            } else {
                result.setData(helpRepository.findByDesignAndAudienceNotAndDraft(twoParameter.getDesign(), 3, 4, pageable));
            }
        } else {
            User user = userRepository.findOne(twoParameter.getUserid());
            if (user == null || isNotLogin(user)) {
                result.setStatus(9);
                result.setMessage("当前用户不存在或未登录!");
                return result;
            }
            if (twoParameter.getDesign() == null) {
                Page<Help> helps = helpRepository.findByDraft(4, pageable);
                helps.forEach(e -> {
                    List<Study> studies = studyRepository.findAllByUserAndHelp(user, helpRepository.findOne(e.getId()));
                    if (studies.size() > 0) {
                        e.setIsStudied(1);
                    } else {
                        e.setIsStudied(0);
                    }
                });
                result.setStatus(1);
                result.setData(helps);
            } else {
                Page<Help> helps = helpRepository.findByDesignAndAudienceNotAndDraft(twoParameter.getDesign(), 3, 4, pageable);
                helps.forEach(e -> {
                    List<Study> studies = studyRepository.findAllByUserAndHelp(user, helpRepository.findOne(e.getId()));
                    if (studies.size() > 0) {
                        e.setIsStudied(1);
                    } else {
                        e.setIsStudied(0);
                    }
                });
                result.setStatus(1);
                result.setData(helps);
            }
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
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            if (twoParameter.getTag() == null) {
                result.setData(helpRepository.findByDraft(4, pageable));
            } else {
                result.setData(helpRepository.findByDraftAndAudienceNotAndTagContains(4, 3, twoParameter.getTag(), pageable));
            }
        } else {
            User user = userRepository.findOne(twoParameter.getUserid());
            if (user == null || isNotLogin(user)) {
                result.setStatus(9);
                result.setMessage("当前用户不存在或未登录!");
                return result;
            }
            if (twoParameter.getTag() == null) {
                Page<Help> helps = helpRepository.findByDraft(4, pageable);
                helps.forEach(e -> {
                    List<Study> studies = studyRepository.findAllByUserAndHelp(user, helpRepository.findOne(e.getId()));
                    if (studies.size() > 0) {
                        e.setIsStudied(1);
                    } else {
                        e.setIsStudied(0);
                    }
                });
                result.setData(helps);
            } else {
                Page<Help> helps = helpRepository.findByDraftAndAudienceNotAndTagContains(4, 3, twoParameter.getTag(), pageable);
                helps.forEach(e -> {
                    List<Study> studies = studyRepository.findAllByUserAndHelp(user, helpRepository.findOne(e.getId()));
                    if (studies.size() > 0) {
                        e.setIsStudied(1);
                    } else {
                        e.setIsStudied(0);
                    }
                });
                result.setData(helps);
                Searching searching = new Searching();
                searching.setKeyword(twoParameter.getTag());
                searching.setType(1);
                searching.setUser(user);
                searching.setCreatetime(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                searchingRepository.save(searching);
            }
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
        if (user == null) {
            result.setStatus(0);
            result.setMessage("当前用户不存在0!");
        } else {
            if (twoParameter.getTag() == null && twoParameter.getType() == null || twoParameter.getType() == 0) {
                result.setData(helpRepository.findByUserAndDraftAndAudienceNot(user, 4, 3, pageable));
            } else if (twoParameter.getTag() != null && (twoParameter.getType() == null || twoParameter.getType() == 0)) {
                result.setData(helpRepository.findByUserAndAudienceNotAndDraftAndTagContains(user, 3, 4, twoParameter.getTag(), pageable));
            } else if (twoParameter.getTag() == null && twoParameter.getType() != null && twoParameter.getType() != 0) {
                result.setData(studyRepository.findAllByUser(user, pageable));
            }else{
                ///
            }
            result.setStatus(1);
        }
        return result;
    }


    @Override
    public Result searchingUser(TwoParameter twoParameter, Pageable pageable) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(twoParameter.getUserid());
        if (user == null) {
            result.setStatus(0);
            result.setMessage("当前用户不存在0!");
        } else {
            result.setStatus(1);
            result.setData(searchingRepository.findDistinctByUserAndType(user, 1, pageable));
        }
        return result;
    }

    @Override
    public Result info(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(twoParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            result.setStatus(1);
            result.setData(user);
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
            result.setStatus(9);
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


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public Result hidden(TwoParameter twoParameter) {
        Result result = new Result();
        if (twoParameter.getUserid() == null || twoParameter.getUserid() == 0 || twoParameter.getHelpid() == null || twoParameter.getHelpid() == 0 || twoParameter.getType() == null || twoParameter.getType() == 0) {
            result.setMessage("必须的参数不能为空!");
            return result;
        }
        User user = userRepository.findOne(twoParameter.getUserid());
        if (user == null || isNotLogin(user)) {
            result.setStatus(9);
            result.setMessage("当前用户不存在或未登录!");
        } else {
            Help help = helpRepository.findOne(twoParameter.getHelpid());
            if (help == null) {
                result.setMessage("选择的求助不存在!");
            } else if (twoParameter.getType() == 1) {
                help.setDraft(5);
                helpRepository.save(help);
                result.setStatus(1);
            } else if (twoParameter.getType() == 2) {
                help.setDraft(4);
                helpRepository.save(help);
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
}
