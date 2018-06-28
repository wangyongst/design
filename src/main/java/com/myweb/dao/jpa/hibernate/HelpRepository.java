package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Help;
import com.myweb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Help.class, idClass = Integer.class)
public interface HelpRepository extends JpaRepository<Help, Integer> {

    public Page<Help> findByUser(User user, Pageable pageable);

    public Page<Help> findByDraft(int draft, Pageable pageable);

    public Page<Help> findByUserAndDraftAndTagContains(User user, int draft, String tag, Pageable pageable);

    public Page<Help> findByDesignAndDraft(String design, int draft, Pageable pageable);

    public Page<Help> findByDraftAndTagContains(int draft, String tag, Pageable pageable);

    public Page<Help> findByUserAndDraft(User user, int draft, Pageable pageable);
}