package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Captcha;
import com.myweb.pojo.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Captcha.class, idClass = Integer.class)
public interface CaptchaRepository extends JpaRepository<Captcha, Integer> {

    public List<Setting> findByMobileAndCode(String mobile, String code);

}