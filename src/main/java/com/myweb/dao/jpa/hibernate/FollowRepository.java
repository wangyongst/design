package com.myweb.dao.jpa.hibernate;

import com.myweb.pojo.Follow;
import com.myweb.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Follow.class, idClass = Integer.class)
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    public List<Follow> findByUserAndTouser(User user, User touser);

    public Page<Follow> findByUser(User user, Pageable pageable);

    public Page<Follow> findByTouser(User touser, Pageable pageable);

    public int countAllByTouser(User touser);

    public void removeAllByUserOrTouser(User user,User touser);
}