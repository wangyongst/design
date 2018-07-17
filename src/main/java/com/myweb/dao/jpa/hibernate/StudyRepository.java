package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Help;
import com.myweb.pojo.Study;
import com.myweb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Study.class, idClass = Integer.class)
public interface StudyRepository extends JpaRepository<Study, Integer> {

    public void removeAllByUser(User user);

    public int deleteAllByHelp(Help help);

    public int deleteAllByHelpAndUser(Help help, User user);

    public List<Study> findAllByUserAndHelp(User user, Help help);

    public Page<Study> findAllByUser(User user, Pageable pageable);
}