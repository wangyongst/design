package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Help;
import com.myweb.pojo.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Study.class, idClass = Integer.class)
public interface StudyRepository extends JpaRepository<Study, Integer> {
}