package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Click;
import com.myweb.pojo.Help;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Click.class, idClass = Integer.class)
public interface ClickRepository extends JpaRepository<Click, Integer> {

    public int countAllByHelp(Help help);

    public int deleteAllByHelp(Help help);
}