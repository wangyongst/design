package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Study;
import com.myweb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Study.class, idClass = Integer.class)
public interface StudyRepository extends JpaRepository<Study, Integer> {

}