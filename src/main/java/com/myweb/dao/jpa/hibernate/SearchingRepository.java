package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Searching;
import com.myweb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Searching.class, idClass = Integer.class)
public interface SearchingRepository extends JpaRepository<Searching, Integer> {
    public void removeAllByUser(User user);

    public Page<Searching> findDistinctByUserAndTypeAndIsclearNot(User user, int type, int isclear, Pageable pageable);

    public Page<Searching> findDistinctByUserAndIsclearNot(User user, int isclear, Pageable pageable);

    public List<Searching> findDistinctByUserAndTypeAndIsclearNot(User user, int type, int isclear);

    public List<Searching> findDistinctByUserAndIsclearNot(User user, int isclear);
}