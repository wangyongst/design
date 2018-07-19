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

    public void removeAllByUser(User user);

    public Page<Help> findByUser(User user, Pageable pageable);

    public Page<Help> findByUserAndAudienceNot(User user, int audience, Pageable pageable);

    public List<Help> findByUser(User user);

    public Page<Help> findByDraft(int draft, Pageable pageable);

    public Page<Help> findByDraftAndAudienceNot(int draft, int audience, Pageable pageable);

    public List<Help> findByDraft(int draft);

    public Page<Help> findByUserAndAudienceNotAndDraftAndTagContains(User user, int audience, int draft, String tag, Pageable pageable);

    public Page<Help> findByDesignAndAudienceNotAndDraft(String design, int audience, int draft, Pageable pageable);

    public Page<Help> findByDraftAndAudienceNotAndTagContains(int draft, int audience, String tag, Pageable pageable);

    public Page<Help> findByUserAndDraftAndAudienceNot(User user, int draft, int audience, Pageable pageable);

    @Query("select sum(help.studied) from Help help")
    public Long sumStudied();

    public Integer countAllByUser(User user);
}