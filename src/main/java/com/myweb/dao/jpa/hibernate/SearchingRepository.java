package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Searching;
import com.myweb.pojo.User;
import com.myweb.pojo.User2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Searching.class, idClass = Integer.class)
public interface SearchingRepository extends JpaRepository<Searching, Integer> {
    public void removeAllByUser(User user);

    public Page<Searching> findDistinctByUserAndType(User user,int type, Pageable pageable);
}