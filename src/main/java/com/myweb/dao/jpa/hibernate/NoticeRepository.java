package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Notice;
import com.myweb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Notice.class, idClass = Integer.class)
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    public void removeAllByUser(User user);

    public Page<Notice> findAllByIsreadNot(int isread, Pageable pageable);
}