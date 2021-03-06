package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Setting.class, idClass = Integer.class)
public interface SettingRepository extends JpaRepository<Setting, Integer> {

    public List<Setting> findAllByType(int type);

}